## Ajax与JSON

Ajax技术的核心是XMLHttpRequest对象

### 1.XHR对象

#### 创建XHR对象

​	var xhr = new XMLHttpRequest();

![1570437625422](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\1570437625422.png)

##### 兼容性问题

ie5/6中的兼容问题

var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP')

#### XHR的用法

![1570438792086](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\1570438792086.png)

open()：要发送的请求的模型即，get，post这些方法

​			   请求的URL

​			   和表示是否异步发送请求的布尔值

启动了一个备发送的请求，还未真正发送请求

send();

​			    向服务器分派请求，需要传入一个参数，作为请求主体发送的数据，不需要就填null；



在接收到响应之后，响应的数据会自动填充XHR对象的属性

responseText 作为响应主题被返回的文本

responseXML 如果响应的内容类型是xml这个属性将包含着响应数据的XML DOM文档

status  响应HTTP状态

statusText HTTP状态的说明



接收到响应之后

![1570439517748](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\1570439517748.png)

检查状态码确认状态。通过这个来决定下一步的操作

HTTP头部信息

通过setRequestHeader()方法设置自定义的请求头信息

参数 头部字段名，头部字段值

通过getResponseHeader()

参数 传入头部字段名

取得相应的头部字段信息

可以通过getAllResponseHeaders（） 

获得包含所有头部信息的长字符串

![1570440840281](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\1570440840281.png)

![1570440844350](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\1570440844350.png)

发送异步请求时，可以检测XHR对象的readyState属性，该属性表示请求/响应过程的当前活动阶段

0 未初始化 尚未使用open()方法

1 启动 已经调用open() 方法 但尚未使用send()方法

2 发送，已经调用send()方法 但尚未接收到响应

3 接收 ，已经接收到部分响应数据。

4 完成，已经接收到全部响应数据，而且已经可以在客户端使用了

readState 属性变化时，会触发一次readyStatechange事件

所以可以通过这个事件来检测每次状态变化后readyState的值

即4

![1570443122803](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\1570443122803.png)



GET请求 

 xxxxxx?a=1&b=2

查询字符串中每个参数的名称和值必须使用encodeURIComponent()进行编码

可以用过一个方法来添加参数

![1570441874743](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\1570441874743.png)



POST请求

服务器对POST请求和提交web表单不会一视同仁，可以通过XHR标房表单提交 

首先设置Content-Type 表单提交的内容类型

然后设置适当格式的字符串，通过send发送

#### 超时设定

ie8为XHR对象添加了一个timeout属性，表示请求在等待多少毫秒之后停止

#### 加载事件load

firefox浏览器通过引入load事件，只在响应接收完毕后触发该事件，即readyState = 4 时触发，这样就代替了readystatechange事件。

### 跨域的概念

从一个资源请求另一个资源，二者所在的请求地址不同，域名不同，端口号不同，请求协议不同

![1570446176215](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\1570446176215.png)

localhost 与 127.0.0.1 也算是跨域

浏览器允许的跨域请求

IMG LINK SCRIPT IFRAME

浏览器禁止跨域请求的情形

XHR -- 浏览器处于安全考虑，禁用了XHR的跨域请求



### JSON

​	XML存在严重的跨浏览器问题，所以被JSON数据格式替代

jsp对象的属性不要求加双引号，但是JSON必须给属性加上双引号

对json求值的通用方法

var obj = eval("("+jsonText+")"),obj为一个数组

可以通过obj[index].属性来访问数据





##### 在Ajax中使用JSON

有一个全局对象JSON 有两个方法

##### parse()

接受两个参数，第一个参数是JSON文本

第二个是个可选的过滤函数

返回的可能是一个传入数据的对象表示，或者对象数组表示

##### stringfy()

通常用于send方法中的参数转化为json格式使用

接受三个参数 

要序列化的对象，

可选的替换函数，

用来替换未受支持的值和可选的缩进说明符，默认返回未缩进，增加缩进后可以增加可读性

函数和undefined值无法通过json表示，包含它们的键会被默认删除

![1570445532614](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\1570445532614.png)

可以通过传入替换参数

![1570445503325](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\1570445503325.png)

第二个参数换为["t1","t2"]; 则结果会被过滤为只包含数组内的属性

![1570454798763](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\1570454798763.png)

安全方面

eval函数可以执行任何的jsp代码，所以在json结构的数据被传入之前，保存通过适当的分析和验证



封装Ajax请求

```
function ajax(method,url,params,done) {
        //统一转换为大写，便于判断
        method = method.toUpperCase();

        //格式化get方法时候?后面的参数
        var pairs = [];
        for (var key in params){
            pairs.push(key+"="+params[key]);
        }
        var queryString = pairs.join("& ");

        //兼容性问题
        var xhr = window.XMLHttpRequest ? new XMLHttpRequest(): new ActiveXObject('Microsoft.XMLHTTP');
        
        //监听器
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                        if ((xhr.status >= 200 && xhr.status < 300) || xhr.status == 304) {  //一般来说200作为请求成功的标志 304表示请求的资源并没有被修改，直接使用缓存
                            alert(xhr.statusText);
                            //尝试用JSON格式解析
                            try {       done(JSON.parse(this.responseText))     } catch (e) {       done(this.responseText)     }
                        } else {
                            alert(
                                "Request was unsuccessful:" +
                                xhr.status
                            );
                        }
                    }
        }

        //GET
        if (method === "GET"){
            url + = '?' + queryString;
        }
        xhr.open(method,url);

        //POST
        var data = null;
        if (method === "POST"){
            xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");//表单提交时的内容类型
        }
        xhr.send(data); 
    }
```



图像ping

img标签从远端服务器通过src属性从远端服务器加载图片资源

JSONP

​	JSON with Padding  是一种借助于script 标签 发送跨域请求的技巧

   通过script标签，src属性，向服务器发送跨域请求，返回

![1570451040699](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\1570451040699.png)

![1570451055995](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\1570451055995.png)

也可以参数通过传入函数对数据进行使用

CORS

![1570448262172](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\1570448262172.png)

这种方案无需客户端作出任何变化（客户端不用改代码），只是在被请求的服务端响应的时候添加一个 AccessControl-Allow-Origin 的响应头，值为* 表示跨域资源共享