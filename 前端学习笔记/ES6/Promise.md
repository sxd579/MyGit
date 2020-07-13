## Promise

Promise对象有两个特点

​	1.对象的状态不受外界的影响。Promise对象代表一个异步操作，

有三种状态pending(进行中),fulfilled(已成功),rejected(已失败)。只有异步操作的结果，可以决定当前是哪一种状态，任何其他操作都无法改变这个状态

​	2.一旦状态改变，就不会再变



Promise构造函数接收一个函数作为参数，该函数的两个参数为

resolve  pending->resolved  将异步操作的结果作为参数传递出去

reject	pending->rejected	将异步操作的错误作为参数传递处

这两个函数jsp已经部署

```
const promise = new Promise(function (resolve,reject) {
        let success = true;
        if (success){
            resolve("result");
        }else {
            reject("error");
        }
    })
```

Promise实例生成以后，可以使用then方法分别指定resolved状态和rejected状态的回调函数

```
promise.then(function (value) {
        //success
    },function (error) {
        //failure
    })
```

方法.then()表示之前的方法执行完后再执行then()内部的程序

每个promise对象都有一个then函数，可以接受两个参数，一个success处理程序，一个error处理程序，then函数中指定回调函数

setTimeout函数传参

​				第一个参数是 func

​				第二个参数是 定时器

​				第三个参数及以后的参数可以作为func函数的参数

​			   第三个参数也可以嵌套定时器

```
//实例
    // function timeout(ms) {
    //     return new Promise((resolve,reject)=>{
    //         setTimeout(resolve,ms,'done');
    //     })
    // }
    //
    // timeout(1000).then((value)=>{
    //     console.log("Everything is",value);
    // })
```

```
//新建Promise对象后立即执行
    // let promise = new Promise((resolve,reject)=>{
    //     console.log('promise');
    //     resolve();
    // });
    //then 方法指定回调函数，即promise所有同步脚本执行完才会执行
    // promise.then(()=>{
    //     console.log("resolved");
    // })
    // console.log("hello");

    //异步加载图片的例子
    // function loadImage(url) {
    //     return new Promise((resolve,reject) =>{
    //         const img = new Image();
    //         img.onload = function () {
    //             resolve(img);
    //         }
    //         img.onerror = function () {
    //             reject(new Error('Could not load Img from'+url));
    //         }
    //         img.src = url;
    //     } )
    // }
    // loadImage("xxxxx").then((img)=>{
    //     加载成功
    // },(error)=>{
    //     加载失败
    // })
```

调用resolve 或reject并不会终结Promise的参数函数的执行，但是一般来说这两个函数之后，Promise的使命就完成了，多余的操作应该放到then方法里面， 而不应该直接卸载resolve 或 reject里面，最好在调用这个函数时，之前加上return，这样就更加合适

在p2中调用resolve方法返回的是p1，由于p2返回的是另一个Promise，导致p2自己的状态无效，由p1的状态决定p2的状态，之后的then和catch则变为针对p1





#### then方法的第一个参数时resolved状态的回调函数，第二个函数时可选的，时rejected状态的回调函数

then可以采用链式写法

前一个回调函数的结果(resolved or reject)作为参数传入下一个回调函数



#### catch方法时相当于then方法第二个函数的别名，用于指定发生错误时的hi掉函数

可以p.then(resolved)

​		.catch(rejected);

如果promise 中 不是调用reject

​		而是throw一个错误，状态没变成resolved时有效，可以被catch

​		而当状态变为resolved之后再抛出错误，就无效了，因为Promise的状态一旦改变就永久保持该状态，不再改变了

​		Promise对象的错误具有冒泡性质，会一直向后传递，直到被捕获为止，即错误总是会被下一个catch语句捕获

##### 		尽量使用catch定义回调函数，不要在then方法中定义Reject状态的回调函数	

​		与传统try，catch不同， 如果没有使用catch方法指定回调函数，Promise对象抛出的错误不会传递到外层代码，即不会有反应，会打印错误提示，不会退出进程，终止脚本执行。

​		Node中的unhandledRejection事件，用来监听未捕获的reject错误



#### finally方法用于指定Promise对象最后状态如何都会执行的操作

如用于服务器请求后关闭服务器

不接受任何参数，意味着无法直到Promise对象的状态

所以finally方法内的操作应该与状态无关

```
 // finally catch方法
    function test() {
        return new Promise((resolve,reject)=>{
            // resolve('done');
            throw new Error("说你错，你就错")
        })
    }
    test()
        .then((value)=>{
        console.log(value);
    })
        .catch((error)=>{
        console.log(error);
    })
        .finally(()=>{
        console.log("哈哈，哪里都有我");
    })
```

#### Promise.all方法用于将多个Promise实例，包装成一个新的Promise实例

一次性执行多个异步方法，在所有异步操作执行完后回调

参数可以是数组，也可以是具有Iterator接口的其他数据结构

返回的状态，全真则真，有假则假

如果Pi reject状态 且自身有catch方法，则执行自己的catch方法，执行完后状态就变成了resolved，Promise.all()这是接受的实例的状态则就是resolved，

如果Pi没定义catch方法， 则会调用Promise.all()的catch方法



#### Promise.race方法执行

执行多个异步操作，只要有一个异步操作执行完毕就立即回调，只保留第一个执行完成异步操作的结果，其他没有执行完毕的会继续执行



#### Promise.allSettled()接收一组Promise实例作为参数

只有等到这些参数实例都返回结果，不管状态，包装实例才会结束，

一旦结束返回的包装的Promise对象，状态总是fulfilled，此时Promise的监听函数接收到的参数是一个数组，每个成员对应一个传入Promise.allSettled() 的Promise实例



#### Promise.any()方法

有一个fulfilled状态，返回的新实例则为fulfilled状态，

如果全是rejected状态，包装实例会变成rejected状态，await命令会抛出错误，错误是一个AggregateError实例数组，每个成员对应一个被rejected的操作所抛出的错误



#### Promise.resolve()方法

用于将现有对象转为Promise对象

##### 参数是一个Promise实例，直接返回这个实例

##### 参数是一个thenable对象，具有then方法的对象，将这个对象转化为Promise对象，然后立即执行该对象的then方法

##### 参数不是具有then方法的对象，或者不是对象，则返回一个新的Promise对象，状态为resolved

##### 不带参数，直接返回一个resolved状态的Promise对象



#### Promise.reject(reason)方法

返回一个新的Promise实例，参数会原封不动地作为reject的理由，变成后续方法的参数



#### Promise.try()

​	统一用promise.catch()捕获所有同步异步的异常

##### //之后修改

```
//try 需要导入库 
    // const  p = new Promise(
    //     (resolve,reject)=>{
    //         resolve('ok');
    //     }
    // )
    //
    // p.try(()=>{
    //     throw new Error('同步错误');
    // }).then((value)=>{
    //     console.log(value)
    // }).catch((error)=>{
    //     console.log(error)
    // })
```

