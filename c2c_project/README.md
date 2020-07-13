# MobileCrowdsourcingUI
C2C移动众包平台移动端UI

框架：vue.js ^2.6.11

UI框架：vant ^2.8.5

运行环境：nodejs

启动页：./src/main.js



运行方式：

1）先用webstorm随便创建一个vue项目，所有选项默认，然后等待配置完成，如果有提示更换npm路径等一律yes。这一步骤是为了自动下载npm serve相关内容，如果之前下载过或者想手动下载则跳过该步骤。

2）pull项目。

3）Add Configuration（三角形按键run的左边）->➕->npm serve，package.json选src目录下的同名文件，Command选run，Scripts选serve，Node interpreter和Package Manager默认。

4）打开package.json，鼠标指到涂黄的警告上，会有npm install之类的提示，安装好后就可以run了。



注意事项：

1）编程时直接在项目文件夹进行。有的人可能觉得直接改项目怕改砸了，喜欢备份到另一个文件夹编程，改好后再复制过来覆盖原项目。这样反而会使git出错。

2）当git使用中遇到问题，千万别用网上说的强制上传或下载方法！！！

3）每完成一块儿编程任务记得后git commit。万一编程过程中出现了奇奇怪怪不会解决的问题可以回退版本。

4）每次push前一定要确保自己能run，以免给别人带来编译麻烦。

5）所有第三方包尽量用package.json来管理，不要手动导入，也不要直接用命令行下载。否则一方面不方便管理，另一方面会使git上下载时包体大很多。

6）页面跳转路由用name和params，不要用path和query。（vue.router已导入）



已引入的主要第三方库：见package.json



其他说明：

vant已经配置好了自动按需引入。详情见官网 https://youzan.github.io/vant/#/zh-CN/home

各种全局变量在./src/style/*.scss中设置

Vue似乎无法实现移动App中的页面跳转（Vue的页面跳转默认是把原界面销毁的）。keep-alive可以实现缓存，但它的效果其实类似于Android的静态页面。我用keep-alive配合Vue.mixin进行了模拟（由https://segmentfault.com/a/1190000015845117中的代码修改）。使用方法：当由A跳转到B，再由B跳转到C，如果希望对B缓存，则需要再B到C跳转时加上一条param cache:true，同时给A的<router-view>外层套上<keep-alive> 。参考App跳转到ReleaseActivity再跳转到ChooseAddressActivity时对ReleaseActivity的缓存。但对于一些更复杂的页面跳转逻辑可能依然会出现问题。

如果遇到"core-js/es6/map in ./node_modules/vue-amap/src/lib/polyfills.js"报错，手动去./node_modules/vue-amap/src/lib/polyfills.js把import "core-js/es6/map"的es6改成es。这个bug可能是vue-amap官方使用的core-js版本不同导致的。

webstorm有时好像会莫名其妙报Unknown html tag ****等警告，但对实际使用无影响。可能是因为ide自身对vant等第三方框架的解析不够完善。
