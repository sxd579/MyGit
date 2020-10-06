### Keep-alive标签的用法

`keep-alive` 是 Vue 内置的一个组件，可以使被包含的组件保留状态，或避免重新渲染。

所以当使用keep-alive标签时，已经创建的页面在重新go or push是不会触发

​    created 和mounted等钩子函数的

​    这时候如果需要钩子函数可以使用activated钩子函数

使用keep-alive标签则所有router-view都会被缓存，

如果只需要某个router-view被缓存可以调用

router.meta属性

```
 {
    path: '/',
    name: 'home',
    component: Home,
    meta: {
      keepAlive: true // 需要被缓存
    }
  },
```

