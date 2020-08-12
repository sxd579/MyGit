#### ES5构造函数和ES6构造函数的对比

​    做美团2020的前端面试题碰到的 好好看了一下

​     例子

​      ES5

```
function F(name){
       this.name = name;
}
F.prototype.a(){
      //原型上的方法
}
F.b(){
      //静态属性
}
let f = new F('xxx');
f.a();
f.b();
```

​       ES6

```
class F{
       constructor(name){
             this.name = name;
       }
       a(){
          //原型上的方法
       }
       static b(){
          //静态属性
       }
}

let f = new F('xxx');

```



ES5的call继承(继承父类的私有属性)

```
function F(name){
       this.name = name;
}
F.prototype.a = function (){
       //
}
function S(age,name){
      this.age = 20;
      F.call(this,name); // this指的是S的实例
}
  S.prototype = Object.create(F.prototype);
    //实现S类是F类的子类，S类就能调用F类的私有属性
    let s = new S(20,'lucy');
    console.log(s.name);
```



ES6继承(继承私有属性，共有属性和静态属性)

```
 class F{
     constructor(name){
         this.name = name;//私有属性
     }
     writeCss(){
         console.log('css');//公有属性
     }
     static fn(){
         console.log('fn');//静态属性
     }
 }
 class S extends F{  //S类继承于F类
     constructor(age,name){
         super(name);   
         //若写了extends 则constructor中必须写super(),相当于F.call(name)
         this.age = age;
     }
     writeJS(){
         console.log('js');
     }
 }
 let s = new S(20,'lucy');
 console.log(s.name);
 s.writeCss();
 S.fn();
```



再回到美团前端面试题的这道题

```
请写出下面ES6代码编译后所生成的ES5代码；
class Person {
     constructor (name) {
          this.name = name;
     }
     greet () {
          console.log(`Hi, my name is     ${this.name}`);
     }
     greetDelay (time) {
          setTimeout(() => {
               console.log(`Hi, my name is ${this.name}`);
          }, time);
     }
}
```

一个构造函数 两个公有属性

ES5代码应该如下

```
function Person(name){
       this.name = name;  //构造函数
}
Person.prototype.greet(){
    console.log(`Hi, my name is     ${this.name}`);
    //公有属性
}
Person.prototype.greetDelay(time){
    setTimeout(() => {
               console.log(`Hi, my name is ${this.name}`);
          }, time);
          //公有属性
}

```

