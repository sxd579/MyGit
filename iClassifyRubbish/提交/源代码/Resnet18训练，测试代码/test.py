from PIL import Image
import torchvision as tv
import torchvision.transforms as transforms
from torch import nn
import torch as t
from torch import optim
from torch.nn import functional as F
import os
import torch.utils.data
from torch.utils.data import Dataset,DataLoader,TensorDataset
from torch.nn import functional as F
import traceback

t.set_num_threads(8)

from torch.autograd import Variable

#创建自己的数据集
class MyDataset(torch.utils.data.Dataset):
    def __init__(self,root,datatxt,transform=None,target_transform=None):
        super(MyDataset,self).__init__()
        file=open(root+datatxt,'r')
        imgs=[]
        for line in file:
            line=line.rstrip()
            words=line.split(', ')
            imgs.append((words[0],int(words[1])))


        self.imgs=imgs
        self.root=root
        self.transform=transform
        self.target_transform=target_transform

    def __getitem__(self, index):

        try:
            file,label=self.imgs[index]
            img=Image.open(self.root+file).convert('RGB')
            #img=img.resize((32,32))#之前为224且被注释掉的
            if self.transform is not None:
                img=self.transform(img)
            return img,label
        except Exception:
            # traceback.print_exc()
            file, label = self.imgs[0]
            img = Image.open(self.root + 'img_1.jpg').convert('RGB')
            if self.transform is not None:
                img=self.transform(img)
            return img,label

    def __len__(self):
        return len(self.imgs)




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


def getData():  # 定义对数据的预处理

    root = 'test_data/'
    transform = transforms.Compose([
        transforms.Resize((40,40)),#之前为40
        transforms.RandomHorizontalFlip(),
        transforms.RandomCrop(32),#取图片中的32X32部分
        transforms.ToTensor(),
        #transforms.Normalize((0.4914, 0.4822, 0.4465), (0.2023, 0.1994, 0.2010))
        transforms.Normalize(mean = [0.54425671,0.50611579,0.46031923],std = [0.20592152,0.21116357,0.21871794])
        ])

    test_transform=transforms.Compose([
        transforms.Resize((32,32)),
        transforms.ToTensor(),
        transforms.Normalize(mean = [0.54425671,0.50611579,0.46031923],std = [0.20592152,0.21116357,0.21871794])
    ])



    train_data = MyDataset(root=root, datatxt='trainNewOrigin.txt', transform=transform)
    trainloader = DataLoader(dataset=train_data, batch_size=32, shuffle=True)#batch_size之前为4

    testset=MyDataset(root=root,datatxt='test.txt',transform=test_transform)
    testloader=DataLoader(dataset=testset,batch_size=32,shuffle=True)



#'1', '2', '3', '4', '5', '6', '7', '8', '9', '10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40'
    #classes = ( '1', '2', '3', '4', '5', '6', '7', '8', '9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40')
    classes=('0','1','2','3')
    return trainloader, testloader, classes


def trainModel():  # 训练模型
    trainloader, testloader, _ = getData()  # 获取数据
    net = ResNet()
    print(net)
    net=net.cuda()
    criterion = nn.CrossEntropyLoss()  # 交叉熵损失函数
    optimizer = optim.SGD(net.parameters(), lr=0.01, momentum=0.9)  # 定义优化器

    #for name in net.state_dict():
    #   print(name)
    for epoch in range(30):
        for step, (tx, ty) in enumerate(trainloader, 0):
            optimizer.zero_grad()  # 梯度清零
            tx=tx.cuda()
            ty=ty.cuda()
            py = net(tx)  # forward + backward
            loss = criterion(py, ty)
            loss.backward()
            optimizer.step()  # 更新参数
            if step % 10 == 9:  # 每2000个batch打印一下训练状态
                acc = testNet(net, testloader)
                print('Epoch:', epoch, '|Step:', step, '|train loss:%.4f' % loss.item(), '|test accuracy:%.4f' % acc)
            state = {'model': net.state_dict(), 'optimizer': optimizer.state_dict(), 'epoch': epoch}
            torch.save(state, 'Model/modelTest01.pth')
                #print("layer1.0.left.0.weight:")
                #print(net.state_dict()['layer1.0.left.0.weight'])
                #print("layer1.0.left.1.weight:")
                #print(net.state_dict()['layer1.0.left.1.weight'])
                #print("layer1.0.left.3.weight:")
                #print(net.state_dict()['layer1.0.left.3.weight'])
                #print("layer1.0.left.4.weight:")
                #print(net.state_dict()['layer1.0.left.4.weight'])
                #print("layer1.0.right.0.weight:")
                #print(net.state_dict()['layer1.0.right.0.weight'])
                #print("layer1.0.right.1.weight:")
                #print(net.state_dict()['layer1.0.right.1.weight'])
                #print("layer1.1.left.0.weight:")
                #print(net.state_dict()['layer1.1.left.0.weight'])
                #print("layer1.1.left.1.weight:")
                #print(net.state_dict()['layer1.1.left.1.weight'])
                #print("layer1.1.left.3.weight:")
                #print(net.state_dict()['layer1.1.left.3.weight'])
                #print("layer1.1.left.4.weight:")
                #print(net.state_dict()['layer1.1.left.4.weight'])
                #print("layer1.2.left.0.weight:")
                #print(net.state_dict()['layer1.2.left.0.weight'])
                #print("layer1.2.left.1.weight:")
                #print(net.state_dict()['layer1.2.left.1.weight'])

    print('Finished Training')
    return net


def testNet(net, testloader):  # 获取在测试集上的准确率
    correct, total = .0, .0
    for x, y in testloader:
        x=x.cuda()
        y=y.cuda()
        net.eval()
        py = net(x)
        _, predicted = t.max(py, 1)  # 获取分类结果
        total += y.size(0)  # 记录总个数
        #print('y:'+str(y))
        #print('p:'+str(predicted))
        correct += (predicted == y).sum()  # 记录分类正确的个数
    return float(correct) / total


if __name__ == '__main__':
    trainModel()

    #torch.save(trainModel(), 'model/model.pth')  # 直接保存模型
    #model = torch.load('src/model.pth')  # 直接加载模型
