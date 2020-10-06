### Vue+Axios 拦截器的使用

 axios使用拦截器
 在请求或响应被 then 或 catch 处理前拦截它们。

##### 请求拦截器

```
http request拦截器
// 添加请求拦截器
axios.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });
```





##### 响应拦截器

```

http respones拦截器
// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    return response;
  }, function (error) {
    // 对响应错误做点什么
    return Promise.reject(error);
  });
```





##### 移除拦截器

```
var myInterceptor = axios.interceptors.request.use(function () {/*...*/});
axios.interceptors.request.eject(myInterceptor);

```



##### 自定义axios添加拦截器

```
var instance = axios.create();
instance.interceptors.request.use(function () {/*...*/});
```

