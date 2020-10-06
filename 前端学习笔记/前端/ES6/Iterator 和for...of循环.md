## Iterator 和for...of循环

### Iterator(遍历器)的概念

遍历过程

创建一个指针对象，指向当前数据结构的起始位置

第一个调用指针对象的next方法，可以将指针，指向数据结构的第一成员个，以此类推，且成员会返回两个属性value done  值和是否遍历结束

类似链表



对于类似数组的对象（存在数值键名和`length`属性），部署 Iterator 接口，有一个简便方法，就是`Symbol.iterator`方法直接引用数组的 Iterator 接口。

```
NodeList.prototype[Symbol.iterator] = Array.prototype[Symbol.iterator];
// 或者
NodeList.prototype[Symbol.iterator] = [][Symbol.iterator];

```

自己写的遍历器next必须部署，return 和throw可选

return方法的使用场合时 for...of循环提前退出， 出错或者遇到break语句



### for...of

JavaScript 原有的`for...in`循环，只能获得对象的键名，不能直接获取键值。

ES6 提供`for...of`循环，允许遍历获得键值

`for...of`循环调用遍历器接口，数组的遍历器接口只返回具有数字索引的属性。

Set 结构遍历时，返回的是一个值，而 Map 结构遍历时，返回的是一个数组，该数组的两个成员分别为当前 Map 成员的键名和键值。

对于普通的对象，`for...of`结构不能直接使用，会报错，必须部署了 Iterator 接口后才能使用。但是，这样情况下，`for...in`循环依然可以用来遍历键名

for...in`循环主要是为遍历对象而设计的，不适用于遍历数组

数组推荐用for 或foreach

foreach

```
myArray.forEach(function(value){
console.log(value);
});
```

