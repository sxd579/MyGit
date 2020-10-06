## Cookie

  绑定在特定的域名下，当设定一个cookie后，再给它的域名发送请求时都会包含这个cookie，这个限制确保了储存在cookie中的信息只能让批准的接收者访问，而无法被其他域访问

cookie的构成 

必须

name   

value

非必须

域   有效域

路径   指定域中的指定路径才会向服务器发送cookie

失效时间   表示cookie何时应该被删除的时间戳

安全标志  指定后 只有在使用SSL链接时 才能发送cookie



document.cookie返回当前页面可用的cookie信息的一些列用分号隔开的键值对

键值对 都用URL编码的，所以必须用decodeURIComponent()来解码

创建cookie

document.cookie = "n = m" 表示创建一个叫n的cookie 值为m

设置cookie时 应该用encoudeURIComponnet("xxx") 进行编码

封装Cookie

​      

```
var CookieUtil = {

        get: function (name){
            var cookieName = encodeURIComponent(name) + "=",
                cookieStart = document.cookie.indexOf(cookieName),
                cookieValue = null;

            if (cookieStart > -1){
                var cookieEnd = document.cookie.indexOf(";", cookieStart);
                if (cookieEnd == -1){
                    cookieEnd = document.cookie.length;
                }
                cookieValue = decodeURIComponent(document.cookie.substring(
                    cookieStart + cookieName.length, cookieEnd));
            }

            return cookieValue;     },

        set: function (name, value, expires, path, domain, secure) {
            var cookieText = encodeURIComponent(name) + "=" +
                encodeURIComponent(value);


            if (expires instanceof Date) {
                cookieText += "; expires=" + expires.toGMTString();
            }

            if (path) {
                cookieText += "; path=" + path;
            }
            if (domain) {
                cookieText += "; domain=" + domain;
            }

            if (secure) {
                cookieText += "; secure";
            }

            document.cookie = cookieText;
            },

        unset: function (name, path, domain, secure){
            this.set(name, "", new Date(0), path, domain, secure);
        }

        };
    //创建cookie
    // CookieUtil.set("name","sxd");
    //查看cookie信息
    // alert(CookieUtil.get(name));
    //删除cookie
    // CookieUtil.unset("name");
    CookieUtil.set("name", "Nicholas", "/books/projs/", "www.wrox.com",
    new Date("January 1, 2010"));
    //删除刚刚设置的 cookie 
    CookieUtil.unset("name", "/books/projs/", "www.wrox.com");
    //设置安全的 cookie 
    CookieUtil.set("name", "Nicholas", null, null, null, true); 
```



### Storage类型(只存储字符串类型的数据)

数据被严格控制在客户端上，无须持续将数据发送回服务器

clear（） 删除所有值

getItem（name） 根据指定的名字name获取对应的值

key(index)  获得index位置处的值的名字

removeItem(name)   删除由name指定的名值对

setItem(name,value)  为指定的name 设置一个对应的值，没有则创建

#### sessionStorage对象是Storage类型的一个对象

只保存到浏览器关闭

针对会话的小段数据的存储，如果需要跨越会话

globalStorage 或者localStorage更为合适

```
//用方法存储数据
    sessionStorage.setItem("name","sxd");
    //使用属性存储数据
    sessionStorage.book = "jsp 666";
    //方法获取数据
    alert(sessionStorage.getItem("name"));
    //使用属性获取数据
    alert(sessionStorage.book);
    //删除
    // sessionStorage.removeItem("name");
    // sessionStorage.removeItem("book");
    // for (var i = 0 ;i<sessionStorage.length;i++){
    //     var key = sessionStorage.key(i);
    //     var value = sessionStorage.getItem(key);
    //     alert(key+"="+value);
    //
    // }
    //也可以用for-in
    for (var key in sessionStorage){
        var value = sessionStorage.getItem(key);
        alert(key+"="+value);
    }
```



#### localStorage

访问同一个localStorage对象，页面必须来自同一个域名（子域名无效）相当于globalStorage[location.host]

数据保留到JavaScript删除或者用户清除浏览器缓存

```
//用方法存储数据
    localStorage.setItem("name","sxd");
    //使用属性存储数据
    localStorage.book = "jsp 666";
    //方法获取数据
    alert(localStorage.getItem("name"));
    //使用属性获取数据
    alert(localStorage.book);
    // 删除
    localStorage.removeItem("name");
    localStorage.removeItem("book");
    for (var i = 0 ;i<localStorage.length;i++){
        var key = localStorage.key(i);
        var value = localStorage.getItem(key);
        alert(key+"="+value);

    }
```



有storage事件

```
 onstorage = new function () {
        //监听storage变化的事件
    }
```

