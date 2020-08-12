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





