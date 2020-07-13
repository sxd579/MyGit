## Generator 函数的语法

Generator函数是ES提供的一种异步编程解决方案

Generator函数是一个状态机，封装了多个内部状态

执行Generator函数会返回一个遍历器对象，也就是说，Generator函数除了状态机，还是一个遍历器对象生成函数，依次遍历Generator函数内部的每一个状态。

特征

function关键字与函数名之间有一个星号

函数体内部使用yield表达式，定义不同的内部状态

```
 function* helloWorldGenerator() {
        yield  'hello';
        yield  'world';
        return 'ending';
    }
    let hw = helloWorldGenerator();
```

Generator函数的调用方法与普通函数一样，也是函数名后面加上一对圆括号。

但是返回的不是函数的运行结果，函数也不执行，

##### 返回的是一个指向内部状态的指针对象 返回一个遍历器对象

```
function* gen(){
  // some code
}

var g = gen();

g[Symbol.iterator]() === g
// true
```

必须调用遍历器对象的next方法，使得指针移向下一个状态

每次调用next方法，内部指针就从函数头部或上一次停下来的地方开始执行，直到遇到下一个yield表达式或者return语句

​		yield 是暂停执行的标记，next方法可以恢复执行

​		每个指针指向两个值value 指针的值，done boolean 循环是否结束

```
 function* helloWorldGenerator() {
        yield  'hello';
        yield  'world';
        return 'ending';
    }
    let hw = helloWorldGenerator();
    console.log(hw.next().value);
    console.log(hw.next().done);
```

yield表达式  紧跟在yield后面的那个表达式的值，作为返回的对象的value属性值

##### yield表达式只可以用在Generator函数中，其他地方会报错，不能嵌套在任何非Generator函数中

##### 可以不用yield表达式，Generator函数就变成了一个单纯的暂缓执行的函数

##### yield表达式如果用在另一个表达式中，必须放在圆括号中

```
console.log('Hello' + (yield)); // OK
console.log('Hello' + (yield 123)); // OK
```



Generator函数就是遍历器生成函数，可以把Generator赋值给对象的

Symbol.iterator属性，从而使得该对象具有Iterator接口



```
//Generator 赋值给对象的Symbol.iterator属性 使得不具备Iterator接口的对象加上遍历器接口
    var myIterable = {};
    myIterable[Symbol.iterator] = function* () {
        yield 1;
        yield 2;
        yield 3;
    }
    alert([...myIterable]);
```

next()方法传参

```
//next()方法传入参数，会被作为上一个yield的返回值，例如
    function* f() {
        for (let i = 0; true ; i++) {
            var reset = yield i;
            if (reset) {
                i = -1;
            }
        }
    }
    //如果next方法没有参数时，yield表达式并没有返回值，即reset一直是undefined
    let g =  f();
    var step_1 =  g.next();
    console.log(step_1.value); //1
    console.log(step_1.done);
    var step_2 =  g.next();
    console.log(step_2.value); //1
    console.log(step_2.done);
    //即上一轮 结束后，返回值 使得reset true i=-1
    //next方法传参，会先作为上一个 yield表达式的返回值，返回再执行正常的next步骤
    var step_3 =  g.next(true);   // i = -1  ++  ->  - =0
    console.log(step_3.value); //0
    console.log(step_3.done);
```

##### 是作为yield的返回值，而非变量接受到的值，如果还有运算要记得运算

```
//另一种使用
    function* f() {
        console.log(`This is 1 test ${yield}`);
        console.log(`This is 2 test ${yield}`);
        console.log(`This is 3 test ${yield}`);
        console.log(`This is 4 test ${yield}`);
        console.log(`This is 5 test ${yield}`);
    }
    let g = f();
    //注意传参是上一个的返回，上一个，上一个，上一个
    g.next()
    g.next('1');
    g.next('2');
    g.next('3');
    g.next('4');
    alert(g.next('5').done);
```



for...of

```
//因为Generator函数运行时，生成的是Iterator对象，这样不需要再调用next方法，可以用for...of方法
    function* f() {
       yield "This is a test";
       yield "This is a test";
       yield "This is a test";
       yield "This is a test";
       yield "This is a test";
    }
    for (let one of f()){
        console.log(one);
    }
```

注意点 yield 后面可以跟console.log等函数

one为yield的值，某些没有返回值的函数，会导致yield的值变为undefined

还可以用 ...



每个Generator函数返回的对象都有一个throw方法，可以在函数体外抛出错误，然后在Generator函数体内捕获，一个catch只会catch一次异常，如果函数体内部catch过异常了，那么下一个异常会被抛出函数体，被外部的catch捕获

```
var  g  =  function* () {
        try {
            yield;
        }catch (e) {
            console.log("内部捕获",e);
        }
    }
    var i = g();
    i.next();
    try {
        //i.throw('a');
        //i.throw('b');
        throw new Error('a');
        throw new Error('b');
    }catch (e) {
        console.log("外部捕获" ,e);
    }
```

该函数返回对象的throw方法才能被内部捕获，用throw命令的话只能被外部捕获，

##### 抛出异常没被捕获会报错

##### 被***内部捕获***了不会影响下一次遍历的执行

##### 如果没有调用过next方法的对象，不会再内部抛出异常，会直接抛到外部

##### throw方法，并且会自动执行一次next方法，



#### return方法，

会结束Generator函数的遍历,done true

会导致代码立刻进入finally模块，执行完后函数结束



##### 总结

next就是将 yield的表达式替换成一个值

throw就是 替换成一个throw语句

return 就是替换成一个return语句



#### 在一个Generator函数中调用另一个Generator函数时，

本需要在前者的函数体内部完成遍历，

可以前者调用函数前使用yield* 这样就可以在外部一步步调用，并且value会是正确的value

如果没有return语句可以用forof代替yield* ，否则需要用后者取到return的值

yield 后面加*表示返回的是遍历器对象，对数组也同样适用



#### Generator作为属性时可以简写

```
*name(){}
```

#### Generator返回的是遍历器对象，不是this 对象，所以无法用遍历器对象，访问函数内部的this 的属性值

```
function* g() {
  this.a = 11;
}

let obj = g();
obj.next();
obj.a // undefined
```

##### 不是构造函数，不能用new命令







#### Generator实现状态机

```
 //状态机
    function* status() {
        while (true){
            console.log("Status is running");
            yield;

        }
    }
    var a = status();
    a.next();
    a.next();
```



可以用于异步操作的同步化表达，把异步操作写在yield表达式里面，等到调用next方法再往后执行，即不需要写回调函数了，

如把好几个loading放在yield表达式内，这样就可以清晰的异步加载



##### 写控制流

```
原始
step1(function (value1) {
  step2(value1, function(value2) {
    step3(value2, function(value3) {
      step4(value3, function(value4) {
        // Do something with value4
      });
    });
  });
});
Promise
Promise.resolve(step1)
  .then(step2)
  .then(step3)
  .then(step4)
  .then(function (value4) {
    // Do something with value4
  }, function (error) {
    // Handle any error from step1 through step4
  })
  .done();
  
  Generator 结构清晰
  function* longRunningTask(value1) {
  try {
    var value2 = yield step1(value1);
    var value3 = yield step2(value2);
    var value4 = yield step3(value3);
    var value5 = yield step4(value4);
    // Do something with value4
  } catch (e) {
    // Handle any error from step1 through step4
  }
scheduler(longRunningTask(initialValue));

function scheduler(task) {
  var taskObj = task.next(task.value);
  // 如果Generator函数未结束，就继续调用
  if (!taskObj.done) {
    task.value = taskObj.value
    scheduler(task);
  }
}

```

##### 注意，上面这种做法，只适合同步操作，即所有的`task`都必须是同步的，不能有异步操作

可以用for ...of 写控制流





部署Iterator接口

```
function* iterEntries(obj) {
  let keys = Object.keys(obj);
  for (let i=0; i < keys.length; i++) {
    let key = keys[i];
    yield [key, obj[key]];
  }
}
obj[Symbol.iterator] = iterEntries();
```



可以看作数据结构数组使用