## Symbol

​	创建Symbol

```
//可以添加一个描述
const sym = Symbol('foo')l

```

Symbol值都不相等，可以作为标识符，防止多模块组成obj时发生覆盖的情况，在对象内部定义属性时，必须放在方括号之中

```
	let a = {};
     a[sym] = "hHH";
    let b{
        [sym] : "hHH"
    };
```

不可以用点运算符调用

```
 // 调用
    console.log(a[sym]);
```



可以用于定义常量，保证常量的值不相等

Stmbol值作为属性名的的时候，属性是公开属性

避免魔术字符串，存到一个常量对象中，便于之后维护

属性名遍历基本不会遍历Symbol 

Object.getOwnPropertySymbols(obj) 可以获取指定对象的Symbol属性名

Reflect.ownKeys(obj) 返回所有类型的键名 包括Symbol

可以用于定于一些非私有，又希望只用于内部的方法

Symbol.for(‘key’)  会在被登记的全局环境中搜索，没有才创建，存在的话返回已经存在的Symbol值

Symbol('key')  不管是否已经存在 都会创建新的，存在也会返回不同的Symbol值，但返回的对象的key是undefined

Symbol.keyFor(Symbol对象) 返回该对象的key