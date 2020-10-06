## this总结

- 函数被当作全局函数来调用，this指向window对象

  ```
       var x = 1;
       function test() {
           alert(this.x);
       }
       test();
       //输出 1
  ```

  ```
  对象的函数复制出来
  / let和const不会自动挂载到全局对象上 var会挂载到全局对象上
  // getName函数被复制到a上 a()被作为全局函数调用，this指向window，所以输出out
  var name ="out";
  let person = {
      name:'in',
      getName:function () {
          console.log(this.name);
      }
  }
  var a = person.getName;
  a();
  // out
  ```

- 对象方法的调用，this指向调用的对象

  ```
      //对象方法的调用  this 指向调用的对象
       function test() {
           alert(this.x)
       }
       var o = new Object();
       o.x =1;
       o.m = test;
       o.m();
       // 1
  ```

- 作为构造函数中的方法，this指向构造函数的实例对象

  ```
    function test() {
      this.x = 1;
      this.getX = function () {
          return this.x;
      }
  }
  
  var t = new test();
  console.log(t.getX())
       //1
  ```

- apply,call,bind调用，this指向传入的第一个参数

  ```
  function testx() {
      console.log(this.x);
  }
  let obj0 = new Object();
  obj0.x = 1;
  testx.call(obj0);
  //1
  ```

- 匿名函数的this指向

  ```
  var name = 'out'
  var px = {
      name:'in',
      getName:function () {
          console.log(this.name);
          let that =this;
          return function () {
              //px调用getName时，返回一个匿名函数，作为全局函数，this指向window
              console.log(this.name);
              console.log(that.name);
          }
      }
  }
  px.getName()();
  //in out in
  ```

- 箭头函数的this指向它的父级作用域

  ```
  var name = 'out'
  var px = {
      name:'in',
      getName:function () {
          console.log(this.name);
          return ()=>{
              //箭头函数的this指向它的父级作用域
              console.log(this.name);
              return ()=>{
                  console.log(this.name);
              }
          }
      }
  }
  px.getName()()();
  // in in in
  ```

- ES6中子类constructor在调用super()之前调用this会报错，

  子类没有this绑定，调用super会产生this才能调用this

      class Animal{
          constructor(){
              this.name = 'animal'
          }
      }
      class Cat extends Animal {
          constructor(){
              this.name ='cat' //报错 
              // 子类调用super之前调用this 会报错 派生类的构造函数没有         this，调用super()会产生this绑定才能调用this
              super();
              this.name ='cat'
      }
        }
      let cat = new  Cat();
  
-  严格模式下

  函数指定的this不再被封装为对象，如果没有给this指定的值的话它是undefined
  
- ```
  obj.addEventListener(){
           console.log(this)  // this指向obj
  }
  ```

  

