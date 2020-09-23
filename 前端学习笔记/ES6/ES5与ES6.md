

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





三种方法

```
   /**
     *  总结  静态方法  可以被类即构造函数直接调用
     *       原型方法  可以被实例或类.prototype 调用
     *       实例方法  只能被实例调用
     *
     */
    function Animal(name) {
        this.name =  name||'Animal';
        //实例方法
        this.eat = function (food) {
            console.log("eat..."+food);
        }
    }
    //原型方法
    Animal.prototype.play = function(activity){
        console.log("play..."+activity);
    }
    //静态方法
    Animal.test = function(){
        console.log("test");
    }
    Animal.test();
    Animal.prototype.play("basketball");
    new Animal().eat("fish");
    new Animal().play("pingpong");

```



es5与es6继承比较

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ES5继承</title>
</head>
<body>

</body>
<script>
    // es5创建类
    // function Animal(name) {
    //     this.name = name||'Animal';
    //     //实例方法
    //     this.eat = function (food) {
    //         console.log(this.name+"eat..."+food);
    //     }
    // }
    // //原型方法
    // Animal.prototype.play = function(activity){
    //     console.log("play..."+activity);
    // }
    //原型链继承  无法多继承
    // function Cat() {
    //
    // }
    //
    // Cat.prototype = new Animal();  // 成为Animal的实例，既可以调用Animal的实例方法也可以调用其原型方法
    // // Cat.prototype = Animal.prototype; 只能调用原型方法
    // let cat = new  Cat();
    // cat.eat("fish");
    // cat.play("fishes");

    //构造继承  只能继承实例方法，不能继承原型方法  可以实现多继承
    // function Cat(name) {
    //     Animal.call(this);
    //     this.name = name||'Cat'
    // }
    // let cat = new Cat()
    // // cat.play("basketball"); //error
    // cat.eat("fish");

    //组合继承  缺点 会创建两个实例  结合 构造继承和原型链继承，既可以调用原型方法，又可以多继承
    // function Cat(name) {
    //         Animal.call(this);
    //         this.name = name||'Cat'
    //
    // Cat.prototype = new Animal();
    // Cat.prototype.constructor = Cat;
    // let cat = new Cat();
    // console.log(cat.name)
    // cat.play("basketball");
    // cat.eat("fish");

    //寄生组合继承  虽然调用了两次构造函数，但是不会创建两个实例
    // function Cat(name) {
    //     Animal.call(this);
    //     this.name = name || 'Cat'
    // }
    //
    //     Cat.prototype = Object.create(Animal.prototype);  //  与Cat.prototype = Animal.prototype;  Cat新添方法会导致Animal也有
    //     Cat.prototype.constructor = Cat;
    //     let cat = new Cat();
    //     console.log(cat.name);
    //     cat.play("basketball");
    //     cat.eat("fish");

    // 多继承
    // function Son() {
    //     Father.call(this);        //实例多继承
    //     OhterClass1.call(this);
    //     OhterClass2.call(this);
    // }
    //
    // Son.prototype = Object.create(Father.prototype);
    // Object.assign(Son.prototype, OhterClass1.prototype, OhterClass2.prototype);  //原型链多继承
    // Son.prototype.constructor = Son;


    //es6
    class Animal{
        constructor(name){
            this.name = name||'Animal';
            this.test = function () {
                console.log("test");   //相当于es5 里面的 this.eat = funciton(){...}.
            }
        }
        static play(){
            console.log("play static");
        }
        eat(food){                             //相当于es5的 Animal.prototype.eat = function(){.......}
            console.log(this.name+"eat..."+food);
        }
    }
    Animal.play();
    let animal = new Animal();
    animal.eat("fish");

    class Cat extends Animal{
        constructor(name){
            super(name);
            this.name = name||'Cat';
        }
    }
    let cat = new Cat();
    cat.eat("shitttttt");
    cat.test();


</script>
</html>

```

