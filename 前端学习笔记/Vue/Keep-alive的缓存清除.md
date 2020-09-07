Keep-alive的缓存清除

```
    beforeRouteLeave(to,from,next){
            console.log(to.path);
          if (to.path=='/login'){
              console.log(this.$vnode.parent.componentInstance.cache);
              console.log(this.$vnode.parent.componentInstance.keys);
              this.$vnode.parent.componentInstance.cache = {};
              this.$vnode.parent.componentInstance.keys = [];
          }
          next();//跳转
        },
```

