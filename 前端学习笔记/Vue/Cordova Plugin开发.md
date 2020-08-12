### Cordova Plugin开发

-   安装plugman插件

     

  ```
  npm install plugman -g
  ```

- 进入目录plugins下构建myPlugin插件

  ```
  plugman create --name MyPlugin --plugin_id p01 --plugin_version 0.0.1
  ```

- 进入插件文件夹，添加平台支持

```
plugman platform add --platform_name android
```

- 插件初始化

  ```
  npm init	
  ```

- 安装插件

  ```
  plugman install -d --platform android --project platforms/android --plugin [这个插件的全路径]
  ```

- 卸载插件

- 每次更新插件都要先卸载插件，再安装插件

- 添加平台 

  ```
  cordova platform add android
  ```

- Cordova项目添加插件