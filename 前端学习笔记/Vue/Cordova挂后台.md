### Cordova挂后台

  cordova-plugin-background-mode 插件 使得程序挂后台，

  仿照qq在主界面按

 后退键，挂起，其他界面正常后退

 home见也是挂起

App.vue 全局

```


holdUpApp(){
                window.cordova.plugins.backgroundMode.moveToBackground() // 后台运行APP
            }
            
            
             document.addEventListener("homebutton", this.holdUpApp, false)
             
             
```

主界面

```
 onBackKeyDown() {
                console.log(this.$route.path == '/order')
                if (this.$route.path == '/order'){
                    window.cordova.plugins.backgroundMode.moveToBackground()
                }
                else {
                    console.log("返回")
                    this.$router.go(-1);
                }
                
                
                 document.addEventListener("backbutton", this.onBackKeyDown, false)
                
                

```

​                

