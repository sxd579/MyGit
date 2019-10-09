```
  //纯粹全局函数调用  指向window对象
    // var x = 1;
    // function test() {
    //     alert(this.x);
    // }
    // test();

    //对象方法的调用  this 指向调用的对象
    // function test() {
    //     alert(this.x)
    // }
    // var o = new Object();
    // o.x =1;
    // o.m = test;
    // o.m();

    //作为构造函数调用  定义函数中的this指向该构造的函数的实例对象
    // function test() {
    //     this.x = 1;
    //     this.getX = function() {
    //         alert(this.x);
    //     }
    // }
    // var t = new test();
    // alert(t.getX());

    //apply  第一个参数是this指向，第二个参数是参数数组
    //call   第一个参数是this指向，后面的参数是参数

    // var x = 1;
    // function test() {
    //     alert(this.x);
    // }
    // var o = new Object();
    // o.x =2;
    // test.call();
    // test.call(o);
    // test.apply();
    // test.apply(o);

    // //监听器中的this  this指向的是调用该监听器的对象
    // var bElement = document.getElementById("bid");
    // bElement.addEventListener("click",function () {
    //     alert(this); //调用这个函数的对象是bElement 所以this对象指向
    // })
    //
    //
    // // 匿名函数
    // var name = 1
    // var person = {
    //     name : 2,
    //     getName:function () {
    //         console.log(this.name);  // person.getName() 调用这个匿名函数的是person this 即person对象
    //         var that = this;
    //         return function () {
    //             console.log(this.name); //.....调用这个匿名函数的是person.getName()在全局环境下生成了return后面的函数，全局函数的this指向window对象
    //             console.log(that.name); //获取外层函数的this
    //         }
    //     }
    // };
    // person.getName()();

    //箭头函数的this 对象和父作用域的this一样
```

