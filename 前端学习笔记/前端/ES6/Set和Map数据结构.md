## Set和Map数据结构

### Set

类似数组，Set本身是一个构造函数，用来生成Set数据结构

通过add方法加入成员，Set结构不会添加重复的值

构造函数可以传入一个数组，字符串来作为参数，用来初始化，会去重

add(value) 添加某个值

delete(value) 删除某个值，返回boolean 是否成功

has(value) 表示是否含有 

clear() 清除所有成员

#### 遍历器

keys() values() entries()

forEach(value,key) 使用回调函数遍历每个成员



### WeakSet

接收数组或者类似数组的对象作为参数 Iterable接口的对象都可以

用法类似Set，但是不能遍历，成员都是弱引用，可能遍历结束成员就取不到了，一个用处就是储存DOM节点，不用担心从文档移除时引发内存泄漏

### Map

类似于对象，是键值对的结合，键的范围不再限于字符串，各种类型的值包括对象都可以作为键

​	object :  str-value

​     map : value-value

map是比object更完善的hash结构

方法类似集合

添加方法变为set(key,value)

 查询方法为get(key)

同个key赋值，即覆盖

```
const  m = new Map();
    m.set('s','b');
    m.get('s');
    const o = {
        p:'Hello world'
    }
    m.set(o,'content');
    m.get(o);
    m.has(o);
    m.has('s');
    m.delete('s');
    for (let[key,value] of m){
        console.log(`${key.p} is ${value}`);
    }
    m.clear();
```

### WeakMap

key只能是对象

无法取到键名，弱引用，无法clear

可以得到值

有四个方法可用get（） set（） has（） delete（）

