## Class的基本语法

​	es6的class可以看作语法糖，大部分功能es5的构造函数也能做到，更像面向对象变成

​	对比改造:

```
//es5
    // function Point(x, y) {
    //     this.x = x;
    //     this.y = y;
    // }
    //
    // Point.prototype.toString = function () {
    //     return '(' + this.x + ', ' + this.y + ')';
    // };
    //
    // var p = new Point(1, 2);

    //es6
    class Point{
        constructor(x,y) {
            this.x = x;
            this.y = y;
        }
        toString(){
            return `(${this.x},${this.y})`;
        }

    }

    console.log(typeof Point);//function   即class的数据类型是function，类本身指向构造函数
    let point = new Point(1,2);//ES5的构造函数 等价于 ES6类的构造函数  使用的时候也是用new命令，一致
    console.log(point.toString());
```

ES6类内定义的所有方法，都相当于在原型prototype上定义的,即类的实例上调用方法，其实就是调用原型上的方法

添加方法还是可以像之前一样，添加在原型对象上

用Object.assgin(原型对象，几个函数);

```
class Point {
  constructor(){
    // ...
  }
}

Object.assign(Point.prototype, {
  toString(){},
  toValue(){}
});
```



与ES5不同的是，类的内部所有定义的方法都是不可枚举的

```
Object.keys(Point.prototype)
// []
Object.getOwnPropertyNames(Point.prototype)
// ["constructor","toString"]
```

### constructor方法

类的默认方法，构造函数，没有就会默认添加一个空的	默认返回this对象，也可以写return指定返回另外一个对象

##### 与普通函数的一个主要区别：  必须要new才能调用

定义在this上的属性，都是对象自身的属性

不是this上的属性则是原型对象的属性

##### 类的所有实例共享一个原型对象，即\_\_proto\_\_ 属性是相等的    

可以通过实例的\_\_proto\_\_属性为类添加方法，但是不推荐使用这个属性，这样会对环境产生依赖，推荐使用

##### Object.getPrototypeOf (实例)方法来获取对象的原型然后再来给原型添加方法和属性



#### Getter 和 Setter

在类的内部可以使用get和set关键字，对某个属性设置存值函数和取值函数，避免直接对属性的存取修改行为

存值和取值函数是设置在属性Descriptor对象上的

```
class Point{
        constructor(x,y) {
            this.x = x;
            this.y = y;
        }
        get prop(){
            return "getter";
        }
        set setProp(prop){
            return "setter";
        }
        toString(){
            return `(${this.x},${this.y})`;
        }

    }
```

#### 属性表达式，类的属性名可以用表达式，即返回值



#### Class表达式

与函数一样，类也可以使用表达式的形式定义

```
const myClass = class Me {
        getClassName(){
            return Me.name;
        }
    };
    let inst = new myClass();
    inst.getClassName();//内部可以访问到Me，表示当前类
    Me.name// Me 没定义，因为类定义在表达式内，所以在外部访问应该用储存他的变量，即myClass
```



可以写出立即执行的Class

```
//立即执行的Class
    let person = new class{
        constructor(name){
            this.name = name;
        }
        sayName (){
            console.log(this.name);
        }
    }('李白');
    person.sayName();
```



### 注意点

- 严格模式，类和模块内部默认是严格模式

- 类不存在变量提升，即类只能在声明之后才能使用，保证子类继承的时候必须在父类之后定义

- 类名.name  返回的是class关键字后面的类名，如果类赋值给某个变量，这变量的name属性返回的是赋值的class的name 而非 变量的name

- 方法前面加上* 表示该方法时一个Generator函数

- this的指向，类的方法内部如果含有this，默认指向类的实例，如果将this使用的方法单独使用，this会指向该方法运行时所在的环境，从而导致找不到属性而报错

  ```
  class Logger {
    printName(name = 'there') {
      this.print(`Hello ${name}`);
    }
  
    print(text) {
      console.log(text);
    }
  }
  
  const logger = new Logger();
  const { printName } = logger;
  printName(); // TypeError: Cannot read property 'print' of undefined
  ```

  解决法1

  在构造方法中绑定this，这样就不会找不到方法

  ```
  class Logger {
    constructor() {
      this.printName = this.printName.bind(this);
    }
  
    // ...
  }
  ```

  解决法2

  使用箭头函数，因为使用的时候this指向的就是其父作用域的this

  箭头函数内部的this总是指向定义所在的对象

  ```
  class Obj {
    constructor() {
      this.getThis = () => this;
    }
  }
  
  const myObj = new Obj();
  myObj.getThis() === myObj // true
  ```

  解决法3

  使用Proxy

### 静态方法

#### 	static关键字

​	如果在类中定义的方法，都会被实例继承，如果在一个方法前，加上static关键字，<u>***就表示该方法不会被实例继承***</u>，而是直接通过类来调用，这就成为"静态方法"

```
class testStatic{
        static test(){
            console.log("Static");
        }
    }
    testStatic.test();
```

##### 实例调用静态方法，会抛出错误

##### 静态方法可以与非静态方法重名，静态方法中的this指的是这个类

##### 父类的静态方法，可以被子类继承，也可以在子类上用super对象上调用

```
//父类的静态方法可以被子类继承,也可以在子类用super对象上调用
    class Foo{
        static classMethod(){
            console.log("Hello Static");
        }
    }
    class Bar extends Foo{
    }
    Bar.classMethod();
    class Bar0 extends Foo{
        static classMethod(){
            console.log("Super",super.classMethod());
        }
    }
    Bar0.classMethod();
```



实例属性的新写法

```
//实例属性的新写法
    class IncreasingCounter{
        constructor(){
            this._count = 0;
        }
        increment(){
            console.log(1)
        }
    }
    class IncreasingCounterOther{
          //放在头部并不会写入原型对象中   定义在头部的属性在ie浏览器中又问题
        _count = 0;
        increment(){
            console.log(2)
        }
    }

    IncreasingCounter.prototype.increment();
    IncreasingCounterOther.prototype.increment();
    console.log(IncreasingCounter.prototype._count);
```

##### 实例属性更直观

个人觉得还是写在构造函数里面比较好



### 静态属性

​	静态属性指的时Class本身的属性，即Class.propName,而不是对象上的属性

​	目前还不可以类内用static关键字定义

​	需要在类外定义

```
//报错
    // class testStaticV{
    //     static a = 0;
    // }
    // console.log(testStaticV.a);
    //外部添加才可以
    class testStaticVV{

    }
    testStaticVV.a = 1;
    console.log(testStaticVV.a);
```



### 私有方法和私有属性

法1 命令上区别，在前面加_

法2  将私有方法移出模块，让后在内部调用call方法，将其变为当前模块的私有方法

```
class Widget {
  foo (baz) {
    bar.call(this, baz);
  }

  // ...
}

function bar(baz) {
  return this.snaf = baz;
}
```

法3 利用Symbol值的唯一性，将私有方法命名在一个Symbol值





### new.target 引入一个new.target 属性 返回命令作用于的那个构造函数

可以利用其写出不能独立使用，必须继承后才能用的类