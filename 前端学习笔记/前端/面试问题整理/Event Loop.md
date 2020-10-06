### Event Loop

  JavaScript 是一种单线程语言

  主线程负责程序本身的运行

  另一个负责主线程与其他进程的通信 即 Event Loop线程

  又称为消息线程

   

#####   即程序在运行过程中，每当遇到io操作，主线程就让Evnet Loop线程

##### 去通知响应的I/O程序，然后主线程继续往后运行，不存在等待时间，等到I/O程序完成操作， Event  Loop线程把结果返回给主线程

##### 主线程则用实现约定好的回调函数，完成整个任务



##### 宏队列  macrotask

- setTimeout
- setInterval
- setImmediate(Node独有)
- requestAnimationFrame(浏览器独有)
- I/O
- UI rendering(浏览器独有)

##### 微队列 microtask  (jobs)

- process.nextTick(Node独有)
- Promise
- Object.observe
- MutationObserver

执行流程

1. 执行Script同步代码
2. 执行完毕以后，调用栈清空
3. 从微队列microtask queue中取出队首的回调任务，放入调用栈中执行，然后长度减一
4. 继续执行microtask中的任务，直到microtask queue中的所有任务都执行完毕，**如果执行microtask的过程中产生的microtask会被加入到队列的末尾，也会在这个周期被调用执行**
5. microtask queue中的所有任务执行完毕以后，取出macrotask queue中位于队首的任务
6. 之后一致重复3-5步骤

##### 注意点

- 宏队列macrotask一次只从队列中取出一个任务执行，执行完就跳转去执行微队列microtask queue中的任务
- microtask queue中的任务一旦执行，就会执行直到microtask queue 为空
- UI rendering 的节点是浏览器判断的，它的节点是在执行完所有microtask 在执行macrotask之前执行的





​	

```js
//  async/await  和promise优先级一样，谁先进micro queue谁先执行  优先级不如nextTick
async function a() {
    console.log("await1 start")
    await b();  // 直接执行到b(); 等待b执行完毕后 下面的执行语句才加入microqueue
    console.log("await1 end")
}
async function b() {
    console.log("await2 start")   
    await c()  // 直接执行到b(); 等待b执行完毕后 下面的执行语句才加入microqueue
    console.log("await2 end")
}
function c() {
    console.log("c")
}
a();
setImmediate(function () {
    console.log("immediate")   // 加入宏队列 在io中setImmediatw优先级高 外面看程序性能，如果setTimeout时间长就先setimmetdiate
})
const p1 = new Promise(function(resolve,reject){
    console.log(1)       
    setTimeout(() => {
        console.log("setTimeout")
        resolve('success1')
    }, 0)
    //promise 在resolve()之前的语句是同步的直接执行，setTimeout计入macroqueue
});

const p2 = new Promise(function(resolve,reject){
    console.log(2)
    resolve('success3');  // promise 从pending 变成 fulfilled或者reject状态之后状态不会再发生改变
    console.log(3);
    reject('reject');
});
p1.then(function(value){
    console.log(value); // success1
});
p2.then(function(value){
    console.log(value); // success3
})
process.nextTick(function () {
    console.log("nextTick")
})
console.log("script end")
	
```

step1: 执行同步代码  输出 

- script start

- await1 start 

- await2 start 

- c  

- (await2后面的代码加入microqueue

  setImmediate加入macroqueue)

- 1

- setTimeout加入macroqueue 优先级比setImmediate高，放到其前面 

- 2

- resolve(''success3")加入microqueue

- 3

- promise状态已经改变不会再修改其状态，不执行reject();

- process.nextTick()优先级比promise和async/await高，放到microqueue他们的前面

- script end

- 此时microqueue队列中是[nextTick,await2 end ,success3]其中再执行完毕await2时，await剩余操作被加入microqueue，即队列是按

  [nextTick,await2 end ,success3,await1]来执行的

- 顺序输出microqueue后 await1 end加入microqueue队列

- 检查macroqueue里面[settimeout, settiimediate]e

- settimeout 先执行 输出settimeout

- resolve 加入microqueue

- 然后先检查microqueue,输出success1

- 最后检查macroqueue 输出setimediate  

- 整体输出顺序为

  - script start
  - await1 start
  - await2 start
  - c
  - 1
  - 2
  - 3
  - script end
  - nextTick
  - await2 end
  - success3
  - await1 end 
  - settimeout
  - success 1
  - immediate