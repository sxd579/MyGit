### Vue播放mp3格式音频

  audio标签需要点过以后才能自动播放，

 在登入界面必须要点击，所以在全局注册audio标签得音频，	

设置 muted 是为了静音 避免首次异常的播放出声音



在App.Vue中写

```
<audio  id="newOrder" controls="controls" style="display: none" muted="muted">
      Your browser does not support the audio tag.
      <source src="./components/order.mp3" type="audio/mp3">
    </audio>
```

