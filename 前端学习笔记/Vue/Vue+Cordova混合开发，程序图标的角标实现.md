### Vue+Cordova混合开发，程序图标的角标实现

使用插件，在Cordova项目的主目录下

```
cordova plugin add cordova-plugin-badge
```

在Vue项目中的主目录下的index.html文件中引入

不用管找不到cordova.js，打包成apk就可以找到了

```
    <script src="cordova.js"></script>
```



然后就可以在Activity中调用插件的方法了

设定角标数字

```
cordova.plugins.notification.badge.set(10);
```

+1

```
cordova.plugins.notification.badge.increase(1, function (badge) {
    // badge is now 11 (10 + 1)
});
```

-2

```
cordova.plugins.notification.badge.decrease(2, function (badge) {
    // badge is now 9 (11 - 2)
});
```

清除

```
cordova.plugins.notification.badge.decrease(2, function (badge) {
    // badge is now 9 (11 - 2)
});
```

打开程序自动清楚

```
cordova.plugins.notification.badge.configure({ autoClear: true });s
```

浏览器调试的要记得注释掉这些，否则会报错