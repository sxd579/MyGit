## Generator函数的异步应用

### 同步异步的概念

同步 函数1调用了函数2，要等函数2执行完毕，函数1才继续往下执行

异步 函数1调用了函数2，函数1继续自己的下一步操作，不受2的影响



### 之前用其他方式的异步

#### 	回调函数callback

​	例如readFile的第三个参数就是回调函数，即readfile结束以后调用的函数

```
fs.readFile('/etc/passwd', 'utf-8', function (err, data) {
  if (err) throw err;
  console.log(data);
});
```

### Promise

异步加载图片

```
 //Promise
    function loadImage(src){
        return new Promise((resolve, reject) => {
            const img = new Image();
            img.onload = function () {

            };
            img.onerror = function () {

            };
            img.src = src;
        })
    }
    loadImage('xxxx').then()
        .catch()
        .finally();
```



### Generator函数的异步

协程

​	多个线程互相协作，完成异步任务

1. 协程A开始执行
2. 协程A执行到一半，进入暂停，执行权转移到协程B
3. 一段时间后协程B交还执行权
4. 协程A恢复执行

例子

```
function* asyncJob() {
  // ...其他代码
  var f = yield readFile(fileA);
  // ...其他代码
}
```

协程遇到yieldm命令就暂停，等到执行权返回，再从暂停的地方继续执行。





#### Thunk函数

传名调用的实现

```
function f(m) {
  return m * 2;
}

f(x + 5);

// 等同于

var thunk = function () {
  return x + 5;
};

function f(thunk) {
  return thunk() * 2;
}
```

JS的Thunk函数

```
// 正常版本的readFile（多参数版本）
fs.readFile(fileName, callback);

// Thunk版本的readFile（单参数版本）
var Thunk = function (fileName) {
  return function (callback) {
    return fs.readFile(fileName, callback);
  };
};

var readFileThunk = Thunk(fileName);
readFileThunk(callback);
```

#### Generator函数的流程管理

Thunk函数可以用于Generator函数的自动化管理

自动执行完所有操作

```
function* gen() {
  // ...
}

var g = gen();
var res = g.next();

while(!res.done){
  console.log(res.value);
  res = g.next();
}
```

这样不适合异步操作，有的时候必须保证前一步执行完，才可以执行后一步



```
 //把并发操作放进数组里面
    var co = require('co');
    co(
        function* (){
            var res = yield [Promise.resolve(1),Promise.resolve(2)];
            console.log(res);
        }
    ).catch(onerror);
    //把并发操作放进对象里面
    co(
        function* (){
            var res = yield {
                1 : Promise.resolve(1),
                2 : Promise.resolve(2);
            }
            console.log(res);
        }
    ).catch(onerror);
```



### 处理Stream

Stream读写数据，一次只处理数据的一部分，数据分一块块依次处理好

Stream模式，会释放三个事件

1. data事件，下一块数据已经准备好了
2. end事件，整个数据流处理完了
3. error事件，发生错误

```
const co = require('co');
const fs = require('fs');

const stream = fs.createReadStream('./les_miserables.txt');
let valjeanCount = 0;

co(function*() {
  while(true) {
    const res = yield Promise.race([
      new Promise(resolve => stream.once('data', resolve)),
      new Promise(resolve => stream.once('end', resolve)),
      new Promise((resolve, reject) => stream.once('error', reject))
    ]);
    if (!res) {
      break;
    }
    stream.removeAllListeners('data');
    stream.removeAllListeners('end');
    stream.removeAllListeners('error');
    valjeanCount += (res.toString().match(/valjean/ig) || []).length;
  }
  console.log('count:', valjeanCount); // count: 1120
});
```

# 没理解