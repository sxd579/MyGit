#!/usr/bin/python
# -*- coding: UTF-8 -*-

import torchvision as tv
import torchvision.transforms as transforms
import torch as t
from PIL import Image


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

t.set_num_threads(8)


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
        file,label=self.imgs[index]
        img=Image.open(self.root+file).convert('RGB')
        #img=img.resize((32,32))#之前为224且被注释掉的


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


def pridict():
    device = t.device("cuda")

    net = ResNet()
    net = net.cuda()

    checkpoint = torch.load('Model/modelTest01.pth')
    net.load_state_dict(checkpoint['model'])

    #model =  t.load('model/modelTest.pth',map_location='cpu')
    #model = model.to(device)
    net.eval()  # 预测模式
    #model.eval()
    # 获取测试图片，并行相应的处理
    img = Image.open('test_data/img_19725.jpg')
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


if __name__ == '__main__':
    pridict()
