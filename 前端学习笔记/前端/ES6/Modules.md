## Modules

```
 //Common JS 模块
    let{stat,exists,readFile} = require('fs');
    //es6模块
    import {stat,exists,readFile} from 'fs'
    //es6模块语法
    export var a = 1;
    export function f() {
        console.log("test");
    }
    var b = 0;
    function f1() {
        console.log("test");
    }
    export {b,f1,f as m};   //as 改名

    export  default  1 ;  //default后面应该是一个值，或者是一个有值的变量
    //继承写法
    // export * from 'circle';   export *会忽略default
    // export var e = 2.71828182846;
    // export default function(x) {
    //     return Math.exp(x);
    // }

    //可以运用于一些跨模块的常量
    // import命令  //只能静态解析，甚至不能是表达式
    //引入import方法
    // import()
     //可以动态加载，按需加载，条件加载，返回的是一个Promise对象

    //Common JS   模块输出值的拷贝，运行时加载
    //值拷贝问题如果原始值改变只有通过函数返回才能得到变化的值 module.exports
    //  ES6模块，输出的是值的引用，ES6模块是编译时输出接口
    //  export 原始值改变直接会改变到输出的值，不需要通过函数

    //Node要求ES6模块采用.mjs后缀文件名，只要用import或export命令，就必须采用.mjs后缀名，  import命令时异步的
    //require 不能加载 mjs 且再mjs中也不能使用require 只能用export和import
    //还是测试阶段需要，--experimental-modules 参数才能使用
    //Node的import命令只支持加载本地模块，不支持加载远程模块
    //模块名不含路径的话，import命令就会去node_modules文件夹下寻找这个模块  包含路径则按照路径去找
    
    <script defer> // 异步加载，等到页面在内存中正常渲染结束，才会执行，按页面的出现顺序加载
    </script>
    <script type="module"> // 相当于打开了defer属性  多个module 也一样会按顺序加载
    </script>
    <script async> // 异步加载， 一旦下载完 中断渲染，执行完脚本之后再渲染，加载顺序不保证
    </script>
```

​		ES6加载CommonJS模块

​		CommonJS输出定义再module.exports属性上面

```
module.exports = {
  foo: 'hello',
  bar: 'world'
};
// 等同于
export default {
  foo: 'hello',
  bar: 'world'
};
//es6 import
// 写法一
import baz from './a';
// baz = {foo: 'hello', bar: 'world'};

// 写法二
import {default as baz} from './a';
// baz = {foo: 'hello', bar: 'world'};

// 写法三
import * as baz from './a';
// baz = {
//   get default() {return module.exports;},
//   get foo() {return this.default.foo}.bind(baz),
//   get bar() {return this.default.bar}.bind(baz)
// }

获取
// b.js
module.exports = null;

// es.js
import foo from './b';
// foo = null;

import * as bar from './b';
// bar = { default:null };
通过bar.defualt 访问可以得到default即module.exports
CommonJs 的输出缓存机制仍然有效，即输出的变量不会发生改变
```

​		CommonJS模具哎加载ES6模块

```
// es.mjs
let foo = { bar: 'my-default' };
export default foo;

// cjs.js
const es_namespace = await import('./es.mjs')
//不可以使用require命令，而要使用import()函数 es6的所有输出接口都会成为输入对象的属性
// cjs.js
const es_namespace = await import('./es.mjs');
// es_namespace = {
//   get default() {
//     ...
//   }
// }
console.log(es_namespace.default);
// { bar:'my-default' }
```

前端更常使用es6模块，后端更常使用commonjs模块

在html中script标签一定要加上type=module才能使用es6模块

命令行调用es6模块 node --experimental-modules main.mjs

在一切基于nodejs 的地方，都要使用commonjs来引入模块

es6  export  import

export 在后面单独使用要{}

export default 变量不需要大括号   import可以以任何名字接收 也不用大括号

或者export  {变量}





commonjs module.exports  require 静态   某些情况需要嵌套函数获取修改的值

module.exports = {

​		所有 a :xxx

​                b:xxx

};

**直接给**`exports` **赋值则会切断与**`module.exports`**之间的联系。**

`exports` 和 `module.exports` 是指向同一块内存的。

var exports.x = xxx   //modules.exports相当于返回exports对象，但是是对exports写的属性的覆盖，返回一整个modules.exports的部分

​								 exports.xx =xxxx  相当于给exports添加属性后返回，不会覆盖modules.exports

​								exports只有单独使用才有意义，不要和modules.exports一起使用

可以对exports的require可以用对象接收

 import()动态