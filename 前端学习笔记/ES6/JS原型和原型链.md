## JS原型和原型链

- 通过new Function()创建的是函数对象，其他是普通对象

- person1和person2都是构造函数的实例，实例的构造函数属性指向构造函数

- 函数对象的prototype属性指向实例对象

  ```
  Person.prototype.name = 'Zaxlct';
  Person.prototype.age  = 28;
  Person.prototype.job  = 'Software Engineer';
  Person.prototype.sayName = function() {
    alert(this.name);
  }
  即
  Person.prototype = {
     name:  'Zaxlct',
     age: 28,
     job: 'Software Engineer',
     sayName: function() {
       alert(this.name);
     }
  }
  ```

  除了表面的四个属性之外，还有默认的属性constructor

  ，指向prototype属性所在的函数Person

  ```
  person.constructor == Person
  Person.prototype.constructor == Person
  ```

  

- 每个对象都有

  ```
  _proto_
  ```

  属性，只有函数对象才有prototype属性

- ```
  person._proto_ == Person.prototype;
  ```

- Object是构造函数

  ```
  Object._proto_ == Function.prototype;
  Object.constructor ==Function
  Object.prototype._proto_ = null 因为null是原型链的顶端
  ```

  ```
  
  
  ```

- 总结测试

  ```
   function A(){
  
      }
      function B() {
  
      }
      function C() {
  
      }
  
      B.prototype = new A();
      C.prototype = new B();
  
      let c = new  C();
  
      // c是C的实例 C.prototype是B的实例 B.prototype是A的实例   实例.__proto__ == 构造函数.prototype
      console.log(c.__proto__ == C.prototype);
      console.log(c.__proto__.__proto__ == B.prototype);
      console.log(B.prototype.__proto__ == A.prototype);
  
      //一般函数，Function，Object都是函数对象  是Function的实例
      console.log(C.__proto__ == Function.prototype);
      console.log(B.__proto__ == Function.prototype);
      console.log(A.__proto__ == Function.prototype);
      console.log(Object.__proto__ == Function.prototype);
      console.log(Function.__proto__ == Function.prototype);
  
      //原型链
      console.log(C.prototype.__proto__ == B.prototype);
      console.log(B.prototype.__proto__ == A.prototype);
      console.log(A.prototype.__proto__ ==Object.prototype);
      //原型链的顶端是null
      console.log(Object.prototype.__proto__ == null);
          console.log(A.prototype instanceof Object);
  ```

- 总结

  ```
      实例.__proto__ = 构造函数.prototype
      对象分为普通对象和函数对象
      Object Function 函数 都是函数对象
      函数名.__proto__ = Function.prototype
      Object.__proto__ = Function.prototype
      Function.__proto__ = Function.prototype
     
      普通对象.__proto__ = Object.prototype;(如原型链对象)
      A.prototype instanceof Object  true
      Object.prototype.__proto__ = null
      
  ```

- 创建对象四种方法

  - var  obj = new Object();

    obj.xxx = xxxx'

  - var obj = {};

    var obj = {

    ​    a:xxxx,

    };

  - 工厂式

    function A(){

    var  obj = new Object();

    obj.xxx = xxxx'

    return obj

    }

  - 构造函数，原型创建对象方式  可以组合使用

    function A(){

    ​     this.xxx=  xxx;

    }

    A.prototype.xx =xxl;

    var a = new A();