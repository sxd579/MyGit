import re
import requests
import os
l1 = ['塑料瓶','食品罐头','玻璃瓶','易拉罐','报纸','旧书包','旧手提包','旧鞋子',
      '牛奶盒','旧塑料篮子','旧玩偶','玻璃壶','旧铁锅','垃圾桶',
      '塑料梳子','旧帽子','旧夹子','废锁头','篮球','旧纸袋','纸盒',
      '旧玩具','木质梳子','香水瓶','煤气罐']
l2 = ['油漆桶','电池','油漆','过期的胶囊药物','含汞温度计','过期药片','荧光灯',
      '蓄电池','杀虫剂']
l3 = ['鸭骨头','鹅骨头','虾壳','蛋糕','面包','草莓','西红柿','梨',
      '蟹壳','香蕉皮','辣椒','巧克力','茄子','豌豆皮','苹果','盆栽树叶','花生壳']
l4 = ['卫生纸','旧镜子','桃核','陶瓷碗','一次性筷子',
      '西梅核','坏的花盆','赃物衣服','烟蒂','湿垃圾袋','扫把','牙刷','过期化妆品',
      '牙膏皮','水彩笔','调色板','打火机','荧光棒','医用手套','医用纱布','医用棉签',
      '创口贴','注射器']
# l5 = ['剩饭剩菜','大骨头','水果果皮','水果果肉','茶叶渣','菜叶菜根','蛋壳','鱼骨']
l5 = ['菜叶菜根','蛋壳','鱼骨']
iii=590
list1 = [20,40,60,80,100,120,140,160,180,200]
for item in l5:
    # os.mkdir('/home/lmk/桌面/images4/' + item)
    for limit in list1:
        url = 'http://image.baidu.com/search/flip?tn=baiduimage&ie=utf-8&word='+item+'&pn='+str(limit)+'&gsm=64&ct=&ic=0&lm=-1&width=0&height=0'
        headers = {
            'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.87 Safari/537.36'}
        html = requests.get(url, headers=headers,stream=True).text
        pic_url = re.findall('"objURL":"(.*?)",', html, re.S)
        print(pic_url)
        for i, each in enumerate(pic_url):
            print(1)
            print(each)
            try:
                pic = requests.get(each)
            except requests.exceptions.ConnectionError:
                print('当前图片无法下载')
                continue
            dir = 'D:/PySoftware/img1/'+item +'/'+ str(iii)+'.jpg'
            iii = iii + 1
            fp = open(dir, 'wb')
            fp.write(pic.content)
            fp.close()
