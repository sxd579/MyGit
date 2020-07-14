### Cordova 打包Vue-Cli项目为APK

修改Vue项目的Config文件夹index.js下的build

```
    assetsSubDirectory: '',
    assetsPublicPath: '',
```



避免打包apk程序出现白屏问题，需要在main.js

```
import VueCorvova from 'vue-cordova'
Vue.use(VueCorvova);
```



安装配置Cordova后，

```
npm install -g cordova
```

配置要求 jdk1.8

android sdk 通过去官网下载as

​             设置ANDROID_HOME

​              和ANDROID_SDK_ROOT

​              为安卓SDK的安装路径 ，我的为

C:\Users\86156\AppData\Local\Android\Sdk

并且在path添加

```
%ANDROID_HOME%\platform-tools
%ANDROID_HOME%\tools
```

下载Gradle

配置环境变量GRADKE_HOME

path添加

```
%GRADLE_HOME%\bin
```

创建Cordova项目

```
cordova create myapp
```

添加平台支持，删除add改rm，不时之需

```
cordova platform add ios
cordova platform add android
cordova platform add browser
```

测试

```
cordova run android
```

生成apk

```
cordova build android
```

