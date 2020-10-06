## CVTE面试问题复习

1.transparent

 css绘制三角形

width=0;

height=0;

这样div变成只有边框撑起来的形状

由border属性划分 transparent就是透明属性

![image-20200824202137074](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\image-20200824202137074.png)

通过将左边设置为透明，展示位三角形的形状

```
<div style="  width: 0;
    height: 0;
    border-left: 50px solid transparent;
    border-right: 50px solid transparent;
    border-top: 100px solid red;">
```

2.原型链问题

每个函数都有一个prototype属性，这个属性指向函数的原型对象。

这是每个对象(除null外)都会有的属性，叫做__proto__，这个属性会指向该对象的原型。

```
var person = new Person();
console.log(person.__proto__ === Person.prototype); // true
```

每个原型都有一个constructor属性，指向该关联的构造函数。

```
function Person() {

}
console.log(Person===Person.prototype.constructor)  //true
// 顺便学习一个ES5的方法,可以获得对象的原型
console.log(Object.getPrototypeOf(person) === Person.prototype) // true
var person = new Person();
console.log(person.constructor === Person); // true
```

 当读取实例的属性时，如果找不到，就会查找与对象关联的原型中的属性，如果还查不到，就去找原型的原型，一直找到最顶层为止。

```
var obj = new Object();
obj.name = 'Kevin'
console.log(obj.name) // Kevin
```

![image-20200828135701598](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\image-20200828135701598.png)

```
function A(a) {
    this.a = a;

}
function B(a) {
   this.a = a;
}
function C(a) {

}
A.prototype.a =1;
B.prototype.a =1;
C.prototype = new B(2);
console.log(new A().a);
console.log(new B().a);
console.log(new C().a);
输出为undefined undefined 2;
```

3.浏览器同源

- 协议相同
- 域名相同
- 端口相同



4.{0:first , [arr.length-1]:last} = array;

5.slice与splice

  slice(start,end)； 不改变原数组，方法可从已有数组中返回选定的元素，返回一个新数组包含从start到end的数组

splice(start,length,items)  该方法向或者从数组中添加或者删除项目，返回被删除的项目，改变原数组，items可选的替换

**拓展**

​    **改变原数组的：**

1. **shift：将第一个元素删除并且返回删除元素，空即为undefined**
2. **unshift：向数组开头添加元素，并返回新的长度**
3. **pop：删除最后一个并返回删除的元素**
4. **push：向数组末尾添加元素，并返回新的长度**
5. **reverse：颠倒数组顺序**
6. **sort：对数组排序**
7. **splice:splice(start,length,item)删，增，替换数组元素，返回被删除数组，无删除则不返回**

**不改变原数组的：**

1. **concat：连接多个数组，返回新的数组**
2. **join：将数组中所有元素以参数作为分隔符放入一个字符**
3. **slice：slice(start,end)，返回选定元素**
4. **map,filter,forEach,some,every等不改变原数组**

6.flex:auto  

​      会使得盒子的宽度由盒子内容决定，要想使得宽度一致，并且当主轴方向存在剩余空间就统一扩大，空间不足则缩小，应该改变其值为：

  等同于     

```
    flex-grow: 1;     项目按比例扩大   限定大小
    flex-shrink: 1;   项目按比例收缩   限定大小时
    flex-basis: auto;  设定值则为项目的初始长度  auto 不限定大小
```

   flex:1

  等同于

```
    flex-grow: 1;
    flex-shrink: 1;
    flex-basis: 0%;
```

**flex-basis **

**如果设置为 auto, 那么这三个项目就会按照自己内容的多少来等比例的放大和缩小，会出现因为内容不一样多而项目大小不一致的情况**,

**如果随便设置一个其他带有长度单位的数字呢, 那么他就不会按项目本身来计算, 所以它不关心内容, 只是把空间等比收缩和放大，不论内容多少都项目大小都一样**

7.async await yield

8.跨域问题

规避策略

cookie：设置`document.domain`共享cookie。

DOM获取：（父子页面通信）H5引入了一个API，这个API为windows对象新增了一个`window.postMessage`方法，允许跨窗口通信，无论这两个窗口是否同源。

content是消息的具体内容，origin是`协议 + 域名 + 端口`

```
window.opener.postMessage(content,origin)
```

AJAX：

- JSONP  只支持get请求

- WebSocket：[WebSocket](https://link.jianshu.com/?t=https%3A%2F%2Fdeveloper.mozilla.org%2Fzh-CN%2Fdocs%2FWeb%2FAPI%2FWebSocket)是一种通信协议。使用`ws://`（非加密）和`wss://`（加密）作为协议前缀。该协议不实行同源政策，只要服务器支持，就可以通过它进行跨源通信。

- CORS 跨域资源共享（corss-origin resource sharing）

- 分为两种请求

- 简单请求

  就是**在Header中增加一个`Origin`字段**。如果浏览器发现跨源AJAX请求是简单请求，就自动在头信息之中，添加一个`Origin`字段。

  服务器根据`Origin`的值决定是否同意这次请求。

  > 如果`Origin`指定的源在不在后端的许可白名单范围内，服务器会返回一个正常的http回应。浏览器接收后发现，这个`response`的Header没有包含`Access-Control-Allow-Origin`字段，就知道出错了，从而抛出一个错误，被`XMLHttpRequest`的`onerror`回调函数捕获。**这种错误无法通过状态码识别，因此HTTP response的状态码有可能是200。**

  > 如果`Origin`指定的域名在许可的范围内，则服务器返回的相应中，会多出几个头信息字段

  `Access-Control-Allow-Origin`：（必须字段）它的值要么是请求时`Origin`的值，要么是`*`，表示接受任意域名的请求。

  `Access-Control-Allow-Credentials`：（可选字段）它是一个bool值，表示是否允许发送Cookie。

9.点击劫持

​    UI-覆盖攻击，虽然受害者点击的是他所看到的网页，但其实他所点击的是被黑客精心构建的另一个置于原网页上面的透明页面。这种攻击利用了HTML中<iframe>标签的透明属性。

10  .event loop32

-    setImmediate  宏队列 Node独有

- ​    setInterval    宏队列  还有I/O       UI rendering和requesxtAnimationFrame 这两个是浏览器独有的

- ​    process.NextTick   微队列  Node独有  还有Promise的回调函数，new Promise中运行的函数属于同步代码， 和observe

- ​    setTimeout        宏队列

  执行流程

  1. 执行Script同步代码
  2. 执行完毕以后，调用栈清空
  3. 从微队列microtask queue中取出队首的回调任务，放入调用栈中执行，然后长度减一
  4. 继续执行microtask中的任务，直到microtask queue中的所有任务都执行完毕，**如果执行microtask的过程中产生的microtask会被加入到队列的末尾，也会在这个周期被调用执行**
  5. microtask queue中的所有任务执行完毕以后，取出macrotask queue中位于队首的任务
  6. 之后一致重复3-5步骤

  ##### 注意点

  - 宏队列macrotask一次只从队列中取出一个任务执行，执行完就跳转去执行微队列microtask queue中的任务
  - microtask queue中的任务一旦执行，就会执行直到microtask queue 为空
  - UI rendering 的节点是浏览器判断的，它的节点是在执行完所有microtask 在执行macrotask之前执行的

11.块级 

- 独占一行

- 通过css设置宽高，内外边距

- 宽度默认随浏览器自适应

  ```
  
  h、p、div、hr、pre、html、body
  blockquote、address、form
  ul、ol
  dl、dd、dt
  fieldset、legend
  optgroup、option
  ```

  行级

  - 内容决定标签所占位置

  - 不可通过css设置宽高，上下边距

    ```
    span、a、img、input、select、strong、b
    em、i、s、mark、small、big
    dfn、abbr、cite、q
    br、wbr、sup、sub、ins、del
    ruby、rt、rp
    code、kbd、samp、tt、var
    u、nobr、label
    ```

    行级块标签

    - 内容决定标签所占位置
    - 可以通过css设置宽高和内外边距
    - 任何标签设置了position:absolute或float:left/right后会自动转换为行级块标签。

  1、块级标签转换为行内标签：display:inline;

  2、行内标签转换为块级标签：display:block;

  3、转换为行内块标签：display：inline-block;

12.JS变量作用域

   在js中，变量的定义并**不是以代码块作为作用域**的，而是**以函数作为作用域**。

  如果变量是在某个函数中定义的，那么，它在函数以外的地方是不可见的。但是，如果该变量是定义在if或者for这样的代码块中，它在代码块之外是可见的。

-    函数内可以访问函数外变量，函数外不可访问函数内变量

- 如果声明一个变量没有加var 默认为全局变量，不过是在调用时才会创建

- ![image-20200828134730679](C:\Users\86156\AppData\Roaming\Typora\typora-user-images\image-20200828134730679.png)

  undefined  1     

  是因为**函数域始终优先于全局域**，所以全局变量a会覆盖掉所有与它同名的全局变量，尽管在alert()第一次被调用时，a还没有被证实定义（即，该值是undefined），但该变量本身已经存在于本地空间了。这种特殊的现象叫做**提升**。

  js执行函数的时候，所有声明会被拉到最上面

  

13.二叉树

14.LRU

15.

- push() 在数组末尾添加一个或多个元素
- pop()   删除数组末尾的元素
- unshift() 在数组的头部添加一个或多个元素
- shift()  方法把数组中的第一个元素删除

 