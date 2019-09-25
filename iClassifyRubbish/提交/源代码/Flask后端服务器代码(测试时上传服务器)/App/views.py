# coding=utf-8，一定要在最顶行添加
from flask import Blueprint, url_for, request, jsonify, render_template
from App.ext import db, bootstrap
from App.models import Questions
from PIL import Image
from io import StringIO

blue = Blueprint('first_blue', __name__, template_folder='../templates')


def init_blue(app):
    app.register_blueprint(blueprint=blue)


@blue.route('/')
def index():
    return 'HelloFlask'


@blue.route('/get_icon/', methods=['GET'])
def get_icon():
    filename = request.get_data()
    img = Image.open('/var/test/static/imges/' + filename)
    return img


@blue.route('/test_photo/', methods=['GET', 'POST'])
def test_photo():
    # 此时得到的内容是bytes数组
    # 获取图片文件 name = rubbish
    img = request.files.get('rubbish')
    print(type(img))
    print(img)
    print('Image name is :' + img.filename)
    imgName = img.filename
    path = "/var/test/static/img/"
    print(path)
    file_path = path + imgName
    img.save(file_path)
    typeRubbish = pridict(file_path)
    content = ""
    if str(typeRubbish).__eq__('0'):
        content = "其他垃圾"
    if str(typeRubbish).__eq__('1'):
        content = "厨余垃圾"
    if str(typeRubbish).__eq__('2'):
        content = "可回收物"
    if str(typeRubbish).__eq__('3'):
        content = "有害垃圾"

    return jsonify({"type": content})


@blue.route('/createdb/')
def create_db():
    db.create_all()
    return 'Create Success'


@blue.route('/addQuestions/')
def add_questions():
    return render_template('addQuestions.html')


@blue.route('/addQuestion/', methods=['POST'])
def add_question():
    if request.method == 'POST':
        question = Questions()
        question.q_A = False
        question.q_B = False
        question.q_C = False
        question.q_D = False
        question.q_content = request.form.get("Question")
        question.q_answer = request.form.get("Answer")
        db.session.add(question)
        db.session.commit()
    return 'Create Question Success'


@blue.route('/getQuestions/')
def get_questions():
    jsonData_list = []
    questions = Questions.query.all()
    for question in questions:
        q = {}
        q['id'] = question.id
        q['content'] = question.q_content
        q['A'] = question.q_A
        q['B'] = question.q_B
        q['C'] = question.q_C
        q['D'] = question.q_D
        q['answer'] = question.q_answer
        q['selection'] = question.q_selection
        jsonData_list.append(q)
    print(jsonData_list)
    return jsonify({'space' : jsonData_list})


# 模型检测

from PIL import Image
import torchvision.transforms as transforms
from torch import nn
import torch as t
import torch.utils.data
from torch.nn import functional as F
import traceback

t.set_num_threads(8)



def pridict(file_path):
    device = t.device("cpu")

    net = ResNet()


    checkpoint = torch.load('Model/modelTest01.pth', map_location='cpu')
    net.load_state_dict(checkpoint['model'])

    #model =  t.load('model/modelTest.pth',map_location='cpu')
    #model = model.to(device)
    net.eval()  # 预测模式
    #model.eval()
    try:
    # 获取测试图片，并行相应的处理
        img = Image.open(file_path)
        transform = transforms.Compose([transforms.Resize((32,32)),
            transforms.ToTensor(),
            transforms.Normalize(mean = [0.54425671,0.50611579,0.46031923],std = [0.20592152,0.21116357,0.21871794])
                                        ])
        img = img.convert("RGB")  # 如果是标准的RGB格式，则可以不加
        img = transform(img)
        img = img.unsqueeze(0)
        img = img.to(device)

        py = net(img)
        _, predicted = t.max(py, 1)  # 获取分类结果
        classIndex_ = predicted[0]
        print('预测结果', classIndex_.item())
        return classIndex_.item()
    except Exception:
        traceback.print_exc()


class ResNet(nn.Module):

    # 实现主module：ResNet34
    # ResNet34 包含多个layer，每个layer又包含多个residual block
    # 用子module来实现residual block，用_make_layer函数来实现layer

    def __init__(self, num_classes=4):
        super(ResNet, self).__init__()
        # 前几层图像转换
        self.pre = nn.Sequential(
            nn.Conv2d(3, 16, 3, 1, 1, bias=False),
            nn.BatchNorm2d(16),
            nn.ReLU(inplace=True),
            nn.MaxPool2d(3, 2, 1))
        # 重复的layer，分别有3，4，6，3个residual block
        self.layer1 = self._make_layer(16, 16, 3)
        self.layer2 = self._make_layer(16, 32, 4, stride=1)
        self.layer3 = self._make_layer(32, 64, 6, stride=1)
        self.layer4 = self._make_layer(64, 64, 3, stride=1)
        self.fc = nn.Linear(256, num_classes)  # 分类用的全连接

    def _make_layer(self, inchannel, outchannel, block_num, stride=1):
        # 构建layer,包含多个residual block
        shortcut = nn.Sequential(nn.Conv2d(inchannel, outchannel, 1, stride, bias=False), nn.BatchNorm2d(outchannel))
        layers = []
        layers.append(ResidualBlock(inchannel, outchannel, stride, shortcut))
        for i in range(1, block_num):
            layers.append(ResidualBlock(outchannel, outchannel))
        return nn.Sequential(*layers)

    def forward(self, x):
        x = self.pre(x)
        x = self.layer1(x)
        x = self.layer2(x)
        x = self.layer3(x)
        x = self.layer4(x)
        x = F.avg_pool2d(x, 7)
        x = x.view(x.size(0), -1)
        return self.fc(x)

class ResidualBlock(nn.Module):

    # 实现子module: Residual Block

    def __init__(self, inchannel, outchannel, stride=1, shortcut=None):
        super(ResidualBlock, self).__init__()
        self.left = nn.Sequential(
            nn.Conv2d(inchannel, outchannel, 3, stride, 1, bias=False),
            nn.BatchNorm2d(outchannel),
            nn.ReLU(inplace=True),
            nn.Conv2d(outchannel, outchannel, 3, 1, 1, bias=False),
            nn.BatchNorm2d(outchannel))
        self.right = shortcut

    def forward(self, x):
        out = self.left(x)
        residual = x if self.right is None else self.right(x)
        out += residual
        return F.relu(out)
