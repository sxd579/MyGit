## Async函数

require浏览器不能识别，要之后用webpack把其打包成浏览器可以识别

async函数就是将Generator函数的星号，变成async 放到function命令前面，

yield替换成await

```
//async 就是Generator的语法糖
    // const gen = function* () {
    //     const f1 = yield readFile('/etc/fstab');
    //     const f2 = yield readFile('/etc/shells');
    //     console.log(f1.toString());
    //     console.log(f2.toString());
    // };
    // 等价于
    const asyncReadFile = async function(){
        const f1 = await fs.readFile('/etc/fstab');
        const f2 = await fs.readFile('/etc/shells');
        console.log(f1.toString());
        console.log(f2.toString());
    }
```

async函数对Generator函数的改进

1. Generator函数的执行必须靠执行器，所以才有了co模块，而async函数自带执行器，所以说async函数的执行，与普通函数一模一样，只要一行

   asyncReadFile();

   2.async和await 比起星号和yield更好理解

​	3.更广的实用性，co模块 yield后面只能是Thunk函数或者Promise对象

​		async函数的await命令后面，可以是Promise对象和原始类型的值

​	4.返回值是Promise

​		这样比Generator函数的返回值是Iterator对象方便多了，可以使用then方法指定下一步操作

async函数可以说是多个异步操作包装成的Promise对象，await命令就是内部then命令的语法糖



### 基本用法

#### 	返回Promise对象

```
//返回Promise对象
    async function hello() {
        return "Hello world";
    }
    hello().then(v=>{
        console.log(v);
    })
```



#### 	Promise对象的状态变化

​	async函数返回的Promise对象，必须等到内部所有await命令后面的Promise对象执行完，才会发生状态改变，除非遇到**return语句或者抛出错误（throw,or reject）**，否则，只有async函数内部的异步操作执行完，才会执行then 方法指定的回调函数

##### 	任何一个`await`语句后面的 Promise 对象变为`reject`状态，那么整个`async`函数都会中断执行。

##### 	await后面的异步操作出错，那么等同于async函数返回的Promise对象被reject

​	如果希望前一个异步操作失败，也不要中断后面的异步操作

- 可以将await放在try...catch结构里面，这样抛出的异常将会再async函数内部被捕获处理，这样不管这个异步操作是否成功，下一个await都会执行

  但是这样reject就不会被外部Promise对象的catch()捕获

  且多个放在try模块的时候，如果前面的await 返回reject状态，后面的也会导致不会执行

  ```
   async function f() {
          try{
              await Promise.reject("你错啦！！");
             .............多个await
          }catch (e) {
              
          }
          return  "It doesn't hurt to be a bad guy";
      }
      f().then(v=>{
          console.log(v);
      }).catch(e=>{
          console.log(e);
      })
  ```

  - 另一种处理方式就是在await后面的Promise对象加上catch()方法，这样也可以避免影响后面的异步操作

    ```
    async function f() {
            try{
                await Promise.reject("你错啦！！").catch(
                    e=>{
                        console.log(e);
                    }
                );
            }catch (e) {
                
            }
            return  "It doesn't hurt to be a bad guy";
        }
        f().then(v=>{
            console.log(v);
        }).catch(e=>{
            console.log(e);
        })
    ```

    

  

### 	await命令

​	await命令后面是一个Promise对象，返回该对象的结果，否则直接返回对应的值，定义then方法的对象，也和Promise对象同样处理

```
//js休眠实例
    function sleep(interval) {
        return new Promise((resolve, reject) => {
            setTimeout(reject,interval);
        })
    }

    //usage
    async function sleepRound() {
        for (let i = 0; i <5 ; i++) {
            console.log(i);
            await sleep(1000);
        }
    }
    sleepRound().then(()=>{
        console.log("resolved");
    })
        .catch(()=>{
            console.log("rejected");
        });
```

### 使用注意点

- await命令后面的Promise对象，运行结果可能是rejected，所以最好把他放在try...catch代码块中

- 多个await命令后面的异步操作，如果不存在继发关系，最好让他们同时触发

  ```
  //异步操作时候，不是继发关系
      //下面两种方法都可以同时触发
      // async function f() {
      //     // 写法一
      //     // let [foo, bar] = await Promise.all([getFoo(), getBar()]);
      //     // 写法二
      //     // let fooPromise = getFoo();
      //     // let barPromise = getBar();
      //     // let foo = await fooPromise;
      //     // let bar = await barPromise;
      // }
  ```

- await只可以用在async函数中，普通函数报错，如果将`forEach`方法的参数改成`async`函数，也有问题。

- async函数可以保留运行堆栈

  ```
  const a = () => {
    b().then(() => c());
  };
  //b或c报错，错误堆栈将不包括a()
  ```

  ```
  const a = async () => {
    await b();
    c();
  };
  b运行的时候a()是暂停执行，因为没有直接跳过Promise,a没有运行结束后删除上下文环境，上下文环境都保存着，一旦b()c()报错，错误堆栈将包括a()
  ```

  





//Promise理解的一个修正

```
 Promise.resolve().then(()=>{
            setTimeout(function () {
                console.log("不管之后的执行");
            },5000);
        });
        console.log("不用等");
```

Promise.then();是否执行完，外部都直接进行下一步操作，并行







##### async 函数的实现原理，就是将 Generator 函数和自动执行器，包装在一个函数里。



```
//异步方法的比较
    //假定某个 DOM 元素上面，部署了一系列的动画，前一个动画结束，才能开始后一个。如果当中有一个动画出错，就不再往下执行，返回上一个成功执行的动画的返回值。
    // Promise
    // function chainAnimationsPromise(elem, animations) {
    //
    //     // 变量ret用来保存上一个动画的返回值
    //     let ret = null;
    //
    //     // 新建一个空的Promise
    //     let p = Promise.resolve();
    //
    //     // 使用then方法，添加所有动画
    //     for(let anim of animations) {
    //         p = p.then(function(val) {
    //             ret = val;
    //             return anim(elem);
    //         });
    //     }
    //
    //     // 返回一个部署了错误捕捉机制的Promise
    //     return p.catch(function(e) {
    //         /* 忽略错误，继续执行 */
    //     }).then(function() {
    //         return ret;
    //     });
    //
    // }

        // Generator
    // function chainAnimationsPromise(elem, animations) {
    //
    //     // 变量ret用来保存上一个动画的返回值
    //     let ret = null;
    //
    //     // 新建一个空的Promise
    //     let p = Promise.resolve();
    //
    //     // 使用then方法，添加所有动画
    //     for(let anim of animations) {
    //         p = p.then(function(val) {
    //             ret = val;
    //             return anim(elem);
    //         });
    //     }
    //
    //     // 返回一个部署了错误捕捉机制的Promise
    //     return p.catch(function(e) {
    //         /* 忽略错误，继续执行 */
    //     }).then(function() {
    //         return ret;
    //     });
    //
    // }

    //async函数
    // async function chainAnimationsAsync(elem, animations) {
    //     let ret = null;
    //     try {
    //         for(let anim of animations) {
    //             ret = await anim(elem);
    //         }
    //     } catch(e) {
    //         /* 忽略错误，继续执行 */
    //     }
    //     return ret;
    // }
```

