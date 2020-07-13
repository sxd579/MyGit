## ES6

### let命令

```
 ////////let命令
    // let a = 10;
    // {
    //     let  a = 10;
    //     var  b = 10;
    //     alert(a);
    // }
    // alert(b);  //var 在块级作用域之外也有效
    // alert(a);  //let 只在所在块级作用域内有效，不向上，也不向下兼容

    //let与var在for循环中的区别
    // for (var i = 0; i<10;i++){
        //...
    // }
    // alert(i);
    // for (let j = 0;j<10;j++){
    //
    // }
    // alert(j);    //同上  块级作用域的差别

    // var a = [];
    // for (var i = 0;i<10;i++){
    //     a[i] = function () {
    //         alert(i);
    //     }
    // }
    // a[6](); //输出10  var类型的i在全局有效，所以随着循环改变为了10
    //
    // var b = [];
    // for (let j = 0;j<10;j++){
    //     b[j] = function () {
    //         alert(j);
    //     }
    // }
    // b[6](); //输出6 let类型的j只在当前块级作用域有效，即每一轮循环的j都是一个新的变量，j只在本轮循环有效，jsp会记住上一轮的j值

    //不存在变量提升，var类型的变量，声明会提前，而let类型并不会，直到声明它之后才可以赋值，和使用，否则会报错
    // alert(foo);
    // var foo = 10;   //  undefined
    // alert(bar);
    // let bar = 10;  //报错

    //暂时性死区  TDZ
    // var temp = 10;
    // if (true){
    //     temp =100;
    //     报错，如果块级作用域内用let声明了一个全局变量，在let声明之前，对这个变量进行赋值等操作会报错 typeof也不可以 let x = x也不行
    //     给参数赋值另一个还没有声明的参数 也会出现死区
        // let temp;
    // }
    //let不允许在相同作用域内重复声明一个变量
```

### 使用块级作用域的原因

1. 内层变量可能覆盖外层变量
2. 用来计数循环的变量泄露为全局变量

ES6中块级作用域允许任意嵌套

每一层都是一个单独的块级作用域

无法使用其他块级作用域的值

可以声明其他块级作用域的同名变量

ES6为了兼容问题

​	1.允许在块级作用域内声明函数

​	2.函数声明类似于var 会提升到全局作用域或函数作用域的头部

​	3.函数声明还会提升到所在块级作用域的头部

但是因为与es5的结果差异还是过大

应该尽量避免在块级作用域内声明函数，如果确实需要则写成函数表达式用let变量保存，则使用方式就与let变量以用

使用块级作用域必须使用{},

且原则上来说let的变量和函数都应该声明在ddan当前作用域的顶层

```
//在块级作用域声明函数
    // {
    //     let  a = 'secret';
    //     f();  //报错
    //     let f = function () {
    //       return a;
    //     };
    //     alert(f()); //secret
    // }
    // f();  // 报错
```



### const命令

声明一个只读的常量，不可以改变

与let相同，只在声明所在的块级作用域内有效

声明不能提升

声明必须赋值

不可以重复声明

实质

​		变量指向的内存地址保存的数据不能改动

​		对于简单类型来说就相当于常量

​		对于符合类型，只能保证指针是固定的，指向的数据结构可不可变不能控制

​		如:

```
{
        const  obj = {};
        obj.prop = '123'; //可以给常量对象添加属性

        const foo = [];
        foo.push("hello");  //可以给常量数组写入数据

    }
```

如果需要 冻结应该使用object.freeze方法



### 顶层对象的属性

var 和function 声明的全局变量，依然是顶层对象的属性

而let命令，const命令， class命令声明的全局变量，不属于顶层对象的属性



### globalThis对象

还未引入





### 数组解构赋值

Set结构，或者某种数据结构具有Iterator接口，都可以用数组，解构赋值

```
//数组的解构赋值
    // let[a,b,c] = [1,2,3]; // 相当于原来的逐个赋值  右边需要是数组，否则严格模式下会报错
    // alert(a+b+c);
    //逐个对应，多余的undefined，也可以通过 , ,跳过某个进行赋值，变量数目少于参数数目，剩下的参数会作为数组赋值给最后一个变量
```

解构赋值允许指定默认值

只有当一个数组成员严格等于undefined时，默认值才会生效

默认值是表达式，则惰性求值，需要时才会运算

```
let [x,y='b'] =  ['a']     //x=a y=b
let [x,y='b'] = ['a',undefined]; //x=a y=b
```

### 对象的解构赋值

let{}={};

变量必须要与属性同名，才可以取到值

解构失败则为undefined

赋值可以给变量赋值，要求同名

也可以给模式赋值，要求模式同名，赋值给模式中的变量

如

```
let{foo:baz} = {foo:'aaa'};
baz//"aaa"
```

这里值赋给变量baz 而非模式foo

模式foo用于匹配

一样可以设置默认值，要求对象对应的属性值，严格等于undefined

变量声明语句的模式部分，赋值语句的模式部分，函数参数不可以使用圆括号

只有赋值语句的非模式部分可以使用圆括号



### 常用用途:

#### 交换变量的值

```
//交换变量的值
    {
        let x = 1;
        let y = 2;
        [x,y] = [y,x];
        console.log("x = " +x);
        console.log("y = " +y);
    }
```

#### 从函数返回多个值

```
//从函数返回多个值
    //返回一个数组
    function example() {
        return [1,2,3];
    }
    let [a,b,c] =example();
    alert("a="+a+" b="+b+" c="+c);
    //返回一个对象
    function example2() {
        return{
            foo:1,
            bar:2
        };
    }
    let {foo,bar} = example2();
    alert("foo="+foo+" bar="+bar);
```

#### 函数参数的定义

```
 //函数参数的定义
    //参数是一组有次序的值
    function f([a,b,c]) {
    }
    f([1,2,3]);
    //参数是一组无次序的值
    function f1({a,b,c}) {
    }
    f1({a:1,b:2,c:3});
```

#### 快速提取JSON对象中的数据

```
let  jsonData = {
        id : 42,
        status:"ok",
        data:[100,86]
    };
    let{id,status,data} = jsonData;
    alert(""+id+" "+status+" "+data.join('-'));
```

#### 函数参数默认值

```
function f(url,{
        a =true,
        b = true,
        c =2,
        d = 4
    }) {
        alert(a);
    }
    f("aaa",{});
```

#### 遍历Map结构

任何部署了Iterator接口的对象，都可以用for...of循环遍历

```
 //遍历Map结构
    const map  = new Map();
    map.set("a","1");
    map.set('b',"2");
    //key and value
    for (let[key,value] of map){
        console.log(key+"  is  " +value);
    }
    //only key
    for (let[key] of map){
        console.log(key);
    }
    //only value
    for (let[,value] of map){
        console.log(value);
    }
```

#### for in 与for of 的区别

```
var jsonText = {
        "a" : "1",
        "b" : "2"
    };
    for(let index in jsonText){
        console.log(index);
    }
    const map  = new Map();
    map.set("a","1");
    map.set('b',"2");
    for (let [key,value] of map){
        console.log(key+" is " +value);
    }
```

for in 得到对象的key，或者数组和字符串的下标

​		值通过变量[key]

for of  用于部署了iterator接口i的对象，如可以遍历Map和Set的key与value



//输入模块或者对象的指定方法

```
//输入模块的指定方法
    const {log} = console;
    log("console .log");
```

#### 字符串的遍历器接口 

还可以识别大于0xffff的码点，传统for循环无法识别

```
//字符串的遍历器接口
    for (let consolePoint of 'hello'){
        console.log(consolePoint);
    }
```



因为JSON格式允许字符串直接使用行分隔符，和段分隔符

用 \\\转义斜杆，包含在json数据中时，

JSON.parse(json)可能报错 通过eval(json)则不会报错



### ES6引入模板字符串，解决输出模板繁琐的问题

#### 通过``反引号esc下面 包括 ${变量名} 来引入 

大括号中也可以放入任意的js表达式，可以进行运算，也可以引用对象的属性，也可以调用函数，不是字符串则自动调用toString方法转化为字符串

```
//模板字符串
    let key = 123;
    let value = 456;
    console.log(`The key is ${key} and value is ${value}`);
```

可以嵌套使用



```
//es6新增判断包含的方法
    let s = 'Hello world!';
    s.startsWith('Hello') // true
    s.endsWith('!') // true
    s.includes('o') // true
```



### 正则表达式对象

```
 //正则构造函数
    var regExp = new RegExp('xyz','i');
    //等价于
    regExp = /xyz/i;
    var regExp0 = new RegExp(/xyz/i);
    //等价于
    regExp0 = /xyz/i;
    //es6修饰符替换
    var regExp1 = new RegExp(/xyz/i,'g');
    //等价于
    regExp1 = /xyz/g;
```

修饰词+u才能识别大括号，unicode码



### 函数扩展

参数中存在对象时，要有对象={} 类似的默认值才可以不写这个参数

否则需要传递

```
//函数参数默认值
    function f(url,{a = 0,b =1, c = 2}) {
        
    }
    f("aaa",{});
    function f1(url,{a = 0,b =1, c = 2}={}) {
        
    }
    f1("Aaa");
```



函数.length 返回没有指定默认值的参数的个数



函数进行声明初始化的时候，如果设置了参数的默认值，参数会形成一个单独的作用域，等到初始化结束，即，如果有与全局变量同名的参数作为默认值，又作为前面的参数，这值为前面的参数，作为之后的参数则会报错，死区问题



### rest参数（...变量名）

则这个变量名指向一个存了不定数目个参数的数组，代替了aruguments

function.name  返回该函数的函数名

### 箭头函数

```
//箭头函数
     var f = v =>v;
     //等价于
     var f = function (v) {
         return v;
     }
     //当箭头函数不需要参数或者需要多个参数时通过圆括号代表参数部分
     var f = () => 5;
     //等价于
     var f = function () {
         return 5;
     }
     var f = (num1,num2) => num1+num2;
     //等价于
    var f = function (num1,num2) {
        return num1+num2;
    }
    //带有代码块的
    // var f = (a) => {
    //     alert(a);
    // }
    // f(1);
    //返回一个对象的时候需要在对象外面加上括号，否则会被认为是代码块，或者报错
    // var f = ()=> ({a:1});
    // alert(f().a);
    
    //箭头函数可以与变量解构结合使用
    const full = ({first,last}) => `${first} and ${last}`;
    //等同于
    // function full(person) {
    //     return person.first + '' + person.last;
    // }
    alert(full({first:"sxd",last:"lsw"}));
    
     //与rest参数结合使用
    const  number = (...nums) => nums;
    alert(number(1,2,3,4,5,6,888,999));
```

箭头函数可以简化代码

箭头函数有几个使用的注意点

​		函数体内的this对象，就是定义时所在的对象，而不是使用所在的对象

```
//this问题
    function foo() {
        setTimeout(()=>{
            console.log('id',this.id); //输出的是42 this总是指向函数定义生效时所在的对象
        },100);
    }
    var id = 21;
    foo.call({id:42});
    //函数.call(对象) 等价于 对象.函数; 但是可以跨作用域
    //函数引用.call(调用者,参数1,参数2,参数3) 
    
//与匿名函数this相比，匿名函数是一个没有指针的全局变量，this指向window对象
function Timer() {
        this.s1 = 0;
        this.s2 = 0;
        //箭头函数
        //this指向生效时所在的对象
        setInterval(()=>this.s1++,1000);
        //普通函数
        //this 指向window对象
        setInterval(function () {
            this.s2++;
        },1000)
    }
    var timer = new Timer();
    setTimeout(()=>console.log('s1',timer.s1),3100);
    setTimeout(()=>console.log('s2',timer.s2),3100);
    //这样来看箭头函数的优势在于可以指向固定化
    //原因是它没有自己的this
    //导致内部的this就是外层代码块的this 也是因此没有构造函数
```

​		不可以当作构造函数，不可以使用new命令，否则会抛出错误

​		不可以使用arguments对象，该对象在函数体内不存在，如果要用，可以使用rest参数代替

​		不可以使用yield命令，因此箭头函数不能用作于，Generator函数



#### 不适用的参合

##### 定义对象的方法的时候，且该方法内部包括this

箭头函数的this不会指向该对象，

而会指向window对象， 因为对象不构成单独的作用域，

导致函数的箭头函数定义的时候的作用域就是全局作用域

###### 需要动态this的时候

比如监听函数是一个箭头函数，

导致里面的this是全局对象，

如果改成普通函数，

this就会动态指向被点击的按钮对象

##### 函数体复杂不推荐使用，可以增加代码的可读性

箭头函数也可以嵌套，且可以通过管道机制，前一个函数的返回值作为下一个函数的参数传入



#### 尾调用

由于是函数的最后一步操作，不需要保留外层函数的调用帧

只需要直接用内层函数的调用帧，去期待外层函数的调用帧即可

即，可以释放外层的调用帧

递归函数时候要尽量使用尾递归，

##### 即函数尾调用自身

##### 这样可以避免栈溢出错误

函数柯里化 ，将多参数的函数转换成单参数的形式

结合ES6的函数默认值，可以更容易的做到

```
 //es5，尾递归 函数柯里化
    function currying(fn,n) {
          return function (m) {
              return fn.call(this,m,n);
          };
    }
    function tailFactorial(n,total) {
        if ( n===1) return total;
        return  tailFactorial(n-1,n*total);
    }
    const  factorial =  currying(tailFactorial(,1));
    factorial(5);
    
    //等价于es6  通过默认值解决
    function tailFactorial(n,total=1) {
        if ( n===1) return total;
        return  tailFactorial(n-1,n*total);
    }
    factorial(5);
```

##### es6 的尾调用优化 只在严格模式下开启，正常模式下是无效的

##### 尾调用优化发生时，函数的调用栈会改写，因此

##### 函数的内部的变量arguments  参数

#####                             caller   带哦用当前函数的那个函数

#####                            会失真



#### 正常模式下的尾递归优化应该是

```
//正常的递归函数
function sum(x,y){
 	if(xxxx){
 	    return sum(x..,y..);
 	}
 	else{
 		retrun  x ;
 	}
}
//这样是函数里面调用函数，会导致超出调用栈的最大次数

function terapoline(f){
	while(f&&f instanceof Function){
	    f  = f();
	}
	return f;
}

//这样是返回一个函数，然后执行该函数，不是函数里面调用函数， 
这样就可以避免递归执行，从而消除了调用栈过大的问题
```





#### 函数参数的尾逗号

最后一个参数可以和之前的参数以用加逗号



#### 对象.toString  会把原始函数的所有代码都返回



#### catch命令的参数可以省略，可以改为

```
try{
//...
}catch{
//...
}
```





### 数组的扩展

#### 扩展运算符

... 就算是rest参数的逆运算，将一个数组转为用逗号分隔的参数序列

只有函数调用时，扩展运算符才可以放在圆括号中，否则会报错

可以利用扩展运算符替代

apply解决的将数组转为函数参数的作用，在无关传入对象(this)的时候

```
// ... 扩展运算符 将一个数组转化为用逗号分隔的参数序列，作为参数传入函数
    // alert(...[1,2,3]); //只显示1 因为alert只接受一个参数
    // console.log(...[1,2,3]) // 1 2 3
    // function add(x,y) {
    //     return x+y ;
    // }
    // const number = [4,38,166];
    // console.log(add(...number)); //42  数组过长也不会报错，只会传入所需的
    
    //替代apply的例子
    // var arr1 = [0,1,2];
    // var arr2 = [3,4,5];
    //es5
    // Array.prototype.push.apply(arr1,arr2);
    //es6
    // arr1.push(...arr2)
    //复制数组的例子
    // const a1 = [1,2];
    // const a2 = a1.concat(); //es5克隆原数组，从而a2不会影响a1
    // const a2 = [...a1]; // es6通过扩展运算符，克隆数组
  
    //合并数组的例子
    //两种方式都是浅拷贝，原数组发生改变会反映到新数组
    const arr1 = [1];
    const arr2 = [2];
    const arr3 = [3];
    //es5 
    arr1.concat(arr2,arr3);
    //es6
    const arr4 = [...arr1,...arr2,...arr3];
    
   
    //用于生成数组，但是扩展运算符只能放在参数的最后一位，否则会报错
    // const [first, ...rest] = [1,2,3,4,5,6];
    // console.log(first);
    // console.log(rest);
```



#### 字符串转化为数组

[...str]

#### 实现了Iterator接口的对象都可以使用...转化为数组，同上

如map和set，generator函数



#### Array.from 可以将两类对象转化为真正的数组

array-like object  如DOM操作返回的NodeList集合，以及函数内部的arguments对象，任何有length属性的对象

还有具有Iterator接口的，string，map，set等

##### 第二个参数可以传入对每个元素进行行处理的函数

##### 第三个参数可以传入绑定this的对象

#### Array.of() 将传入的参数转化为数组

数组实例有

find（）参数是要给回调函数，找出一个返回值为true的成员的值，没有则undefined  回调参数可以接收三个参数value,index,arr 值，位置，原数组

findIndex()方法  类似find 返回的是成员的index

fill() 以某元素覆盖

entries() 返回遍历器 index-value

keys()  index

values() value

includes()是否包含

flat() 拉平为一维数组 可传参表示拉平的层数，空位会被删掉

flatMap() 对每个成员拉平一层

sort() 传入排序方法， 默认是稳定的排序算法



### 对象的扩展

#### 属性简写

{变量名}  属性名为变量名，属性值为变量值

#### 方法简写 

 a : function(){}   ---> a(){}

#### setter 和 getter 也是这种写法

get  a(){return ...}

set b(){}



方法的name属性返回函数名

如果对象的方法使用了取值函数，和存值函数，则name不再在方法上，而是描述在对象的get和set上

```
getset     
obj.get.name
obj.set.name
原来
obj,name
func.name
```

bind 方法创造的函数，name属性返回bound加上原函数的名字

Function构造函数创造的函数，name属性返回annoymous

如果对象的方法是一个Symbol值，name返回Symbol的描述



for in 循环遍历对象自身和继承的可枚举属性

obj，keys()返回一个数组，包括对象自身的所有（无继承）可枚举属性

es6 super指向当前对象的原型对象

扩展运算符...的解构赋值不能复制继承自原型对象的属性



#### 对象新增方法

Object.is(a,b)  比较a和b是否严格相等

Object.assign(mainObj,obj1,obj2)  合并对象 浅拷贝，同名属性会被替换

​				-	用于为对象添加属性和方法，克隆对象，合并对象，为属性指定默认值  无法拷贝get属性和set属性

因为__prop__ 属性值在浏览器中有，所以尽量不要直接调用，会有兼容问题

而是使用Object.setPrototypeOf(obj,prototype)写操作

​			    		  getPrototypeOf(obj)读操作

​						  create(someOtherObj) 生成操作 创建的对象的原型为参数对象

​						来代替

entries() 返回遍历器 key-value

keys()  index

values() value