## 避免多次请求的方法

应用在比如Vant UI 的list多次加载的情况

```
if(!this.timer){
                    this.timer = setTimeout(() => {
                        this.getGoods(type)       //请求商品数据的方法
                        this.timer = null;
                    }, 1000)
                }
```

