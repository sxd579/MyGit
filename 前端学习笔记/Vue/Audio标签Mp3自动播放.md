Audio标签Mp3自动播放

由于在移动端不能自动播放，需要用户与界面发生交互才能自动播放，所以，在登入界面触发这一条件

```
在App.vue先全局注册Audio标签，设置muted 静音，避免开始点击的异常播放
<audio  id="newOrder" controls="controls" style="display: none" muted="muted">
      Your browser does not support the audio tag.
      <source src="./components/order.mp3" type="audio/mp3">
    </audio>
```

然后再登入界面的mounted钩子函数下

```
   document.body.addEventListener('click', function() {
                // const temporary = setTimeout(() =>{
                document.querySelector('audio').pause();
                //     clearTimeout(temporary);
                // }, 0);
            });
```

然后在 需要调用的地方正常调用

```
   document.querySelector('audio').muted = '';
                            document.querySelector('audio').play();
```

