## Vue

### 引入vue

```
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>  //开发环境版本
<script src="https://cdn.jsdelivr.net/npm/vue"></script>//生产环境版本
```

###  Vue实例

el存"#id"

data:{

​	属性名: 属性值

}

methods:{

​     方法名:function(){

​     }

}

```
let app5 = new Vue({
        el: "#app-5",
        data: {
            message: "Hello Vue!"
        },
        methods:{
            reverseMessage:function () {
                this.message = this.message.split('').reverse().join('');
            }
        }
    });
```

属性集合props:['']

​			  标签template:''     HTML

```
 Vue.component("todo-item",{
        // todo-item 组件现在接收一个
        //'prop',类似于一个自定义特性。
        //这个prop名为todo
        //属性集合 props
        props:['todo'],
        template:'<li>{{todo.text}}</li>'

        }

    );
```

生命周期钩子

created 在对模板渲染之前，初始化之前

,mounted 在模板渲染之后，初始化后对DOM节点进行某些操作

,updated,destoryed

```
new Vue({
  data: {
    a: 1
  },
  created: function () {
    // `this` 指向 vm 实例
    console.log('a is: ' + this.a)
  }
})
// => "a is: 1"
```



v-once 不需要给值，这个文本的值将不会被改动，配合{{}}使用

v-html="html文本数据"   里面可以是变量名，不需要再与{{}}配合使用

这样才会将数据解释为HTML代码，否则会被解释为普通文本

{{}} 只在标签内部起作用

在标签内的特性为v-bind:属性="",布尔特性存在意味着true，如果是其他则该特性不会渲染

{{}}中可以使用单个JavaScript表达式，

语句

```
var a = 1 这个是语句
a + 1 这个是表达式
```

则不会生效



#### 指令带有v-前缀

指令的值预期是单个jsp表达式 



### 标签参数

v-bind:参数名=""

HTML attribute 可以动态的更新这个属性 = 后面的值即为Vue中的属性，

v-on:监听事件名=""

= 后面的值即为Vue中的函数

##### 动态参数

v-bind:[attr]=""

[attr] 即Vue中的值，值包含的是HTML标签中的属性

v-on:[event]=""

[attr] 即Vue中的值，值包含的是HTML标签中的属性

[]中也可以传入 **没有空格或引号的表达式**

### 修饰符

.指令的特殊后缀

v-on:click.stop 可以防止事件冒泡





可以缩写 



```
<!-- 完整语法 -->
<a v-bind:href="url">...</a>

<!-- 缩写 -->
<a :href="url">...</a>
```



```
<!-- 完整语法 -->
<a v-on:click="doSomething">...</a>

<!-- 缩写 -->
<a @click="doSomething">...</a>
```

{{}}中也可以放函数，为函数的返回值

##### 计算属性是缓存的，函数是每次调用都要执行的

##### 侦听器

##### computed

用来**监控自己定义的变量，该变量不在data内声明，直接在computed 里面定义**，然后可以在页面上进行双向数据绑定展示出结果或用作其他处理，

比较**适合于多个变量或者对象**进行处理后返回的一个结果值

##### watch

更适合运用于数据异步或者并发更新

**监控变量必须在data里面声明才可以**，并且只可以**监控一个变量或者对象**

```
let app = new Vue({
        //watch 监听 和计算属性
        el:"#app-1",
        data: {
            firstName:'S',
            lastName:'XD',
            watchName:'SXD'
        },
        computed:{
            fullName:function () {
                return this.firstName+this.lastName;
            }
        },
        watch:{
            firstName: function (val) {
                this.watchName = val + this.lastName;
            },
            lastName: function (val) {
                this.watchName = this.firstName() + val;
            }
        }

    })
    app.firstName = 'M'
```





class绑定 

v-bind:class = "{ css类名:属性即boolean值 }"  与之前的class用空格相隔，最后组成新的class最后去寻找

v-bind:class = "属性" 即是对应的属性值即去寻找属性值对应的class

```
<div v-bind:class="{ active: isActive }" id="app-1">
    <p>isActive</p>
</div>
```

对应css   .active

```
<div class="static" v-bind:class="{ active: isActive}" id="app-2">
    <p>class bind </p>
</div>
即<div class="static active"></div>
```

空格换.

static active对应css  .static.active     

.static.active 即继承static和active 但是优先级前者高，新定义的属性优先级最高



绑定的数据对象，也可以不放在内联定义的模板中

```
<div v-bind:class="classObject" id="app-4">
    <p>isActive 656565</p>
</div>
let app3 =  new Vue({
        el:"#app-4",
        data:{
            classObject:{
                static:true,
                active:false

            }
        }
    })
```



用在组件上

```
 Vue.component('cp',{
        template:'<p class = "static">hhhhhhhhhh</p>'
    })
    let app3 = new Vue({
        el:"#app-3"
    })
<cp class="active" id="app-3"></cp>    
```

```
<div v-bind:style="{color:activeColor, fontSize:activeSize+'px'}" id="app"> 
<p>style绑定</p>
</div>
<div v-bind:style="obj" id="app2">    
<p>style绑定对象</p>
</div>
<body>
<script>     
let app = new Vue({         
el:"#app",         
data:{            
activeColor: 'red',
activeSize:30         
}     });
```

即v-bind:style = "{属性:vue中属性名，。。。+}"

v-if v-else-if v-else  条件渲染    **运行条件很少改变时使用**

```
<div id="app">
    <h1 v-if="awesome">if??</h1>
    <h1 v-else-if ="ok" >ok?</h1>
    <h1 v-else>oh ?</h1>
    <template v-if="loginType === 'username'">
        <label >Username</label>
        <input type="text" placeholder="Please input your username" key="username-input">
        <button @click>change</button>
    </template>
    <template v-else>
        <label >Email</label>
        <input type="text" placeholder="Please input your email" key="email-input">
    </template>
</div>

let  app  =  new Vue({
        el : "#app",
        data :{
            awesome: false,
            ok:true,
            // loginType:'username',
            loginType:'email',
        }

    })
```

v-show 相当于v-if 不支持v-else不支持template   **频繁切换时使用**

v-for 遍历列表 item 数组元素，index 索引号

```
let example = new Vue({
        el : "#app-1",
        data :{
            parentMessage:"parent",
            items:[
                {message:'Foo'},
                {message: 'Bar'}
            ]
        }

    })
    <ul id="app-1">
    <li v-for="(item,index) in items">
        {{parentMessage}}-{{item.message}}-{{index}}
    </li>
</ul>

迭代数组别名，索引
```



v-for遍历对象  提供value 值 name 键名 index索引号

```
<ul id="app-2">
    <li v-for="(value,name,index) in obj">
           {{name}}-{{value}}-{{index}}
    </li>
</ul>
//  值 键名 索引

 let example1 = new Vue({
        el:"#app-2",
        data:{
            obj:{
                first:'1111',
                second:'2222',
                third:'3333'
            }
        }
    })
```

尽可能v-for时候提供v-bind:key  key采用string value

**没有key会被移除，会根据key变化给元素排序**



v-on绑定事件，可以通过在参数加入$event 传入事件对象

v-model双向绑定

```
<div id="app-3">
    <input v-model="message1" placeholder="input something">
    <p>Message is :{{message1}}</p>
</div>
显示输入的值且会修改message1的值，message1也会被设置为表单元素默认值

复选框再绑定布尔值时候则反应的是选没选的对应true-value,false-value
如果绑定的是数组之类的，绑定的是选没选决定value加不加入数组
```

单选框时候第一个如果是提示信息 比如请选择，应该设置成disabled

默认内容即为value，也可以设定value 这样子选项的value就可以和选项的内容不同

- text 和 textarea 元素使用 `value` 属性和 `input` 事件；
- checkbox 和 radio 使用 `checked` 属性和 `change` 事件；
- select 字段将 `value` 作为 prop 并将 `change` 作为事件。



v-model的修饰符

.lazy 默认每次input事件触发后将输入框的值与数据同步，使用这个修饰符改变为change事件同步

.number 自动将用户的输入值转为数值类型

.trim 自动过滤掉用户输入的首尾空白字符





## 组件基础

### 组件注册

#### 全局注册

组件的创建格式

```
Vue.component('组件名',{
	data:function(){
		//不像创建Vue实例直接 data:{}
		//组件的data必须是一个函数
	},
	props:['attr'], //一些自定义的prop attribute 就类似id class name 这些属性
	template:'HTML语言'，
})
```

#### 局部注册



### Prop

在HTML中attr 使用的是a-b-c 如果使用驼峰标识 在HTML中会自动转化成a-b-c的形式

与其他attribute一样，可以接受动态，静态值

单向数据流，每一个父Prop单向传递给子Prop

```
两种情形，初始化，转换
 //单向数据流
    Vue.component('component-b',{
        props:['size'],
        data:function () {
            return {
                //通过prop初始化，之后prop改变 count不变
                count:this.size
            }
        },
        computed:{
            //根据prop改变，upSize也会改变
            upSize:function () {
                return this.size + 1
            }
        },
        template: `<p>count是{{count}} upSize是{{upSize}}</p>`

    })
```

#### prop验证

##### 字符串 静态直接传，动态用v-bind 其他静态动态都要用v-bind

##### 验证时如果不符合要求任然会显示，只不过控制台中会有警告

##### 另外验证是在组件创建之前，所以computed和data里面的变量不能调用

##### type可以是函数，能检查是否传入的值是通过指定构造函数创建的

##### 大多数attribute传入值会替换原有值，而style和class则是合并起来



不希望组件的根元素继承attribute 

通过inheritAttrs:false



子组件可以通过调用内建的$emit方法并传入事件名称来触发一个事件

$emit() 

第一个参数是绑定的事件名

第二个参数是事件抛出的一个值

​			可以在父级组件监听这个事件的时候通过$event来访问被抛出的这个值

​           在父级组件也可以只写函数名，这个参数将被默认作为第一个参数传入父级组件这个函数

```
 <listener v-on:test="say($event)"></listener>
 
 
  let component1 = Vue.component('listener',{
        data:function () {
            return {
                count:0
            }
        },
        props:["title"],
        // template: `<div><p>监听子组件事件啦！！！</p> <button v-on:click="$emit('test')">Extend</button></div>`
        template: '<div><p>监听子组件事件啦！！！</p> <button v-on:click="$emit(\'test\',\'666\')">Extend</button></div>'
    })
```



### 踩坑

1.使用转义符号“\”转义

```
template: '<div><p>监听子组件事件啦！！！</p> <button v-on:click="$emit(\'test\',\'666\')">Extend</button></div>'
```

1.使用转义符号“\”转义

```
template: `<div><p>监听子组件事件啦！！！</p> <button v-on:click="$emit('test')">Extend</button></div>`
```





组件中的v-model

为了使其正常工作，这个组件必须将

- 将value attr绑定到一个value的prop上
- 在其input事件被触发时，将新的值通过自定义的事件input抛出，即传给外部组件

```
//组件上的v-model
    let component2 = Vue.component('v_model',{
        data:function () {
            return {
                count:0
            }
        },
        props:["value"],
        template: '<input v-bind:value = value v-on:input="$emit(\'input\',$event.target.value)"/>'
    })


<div id="app-4">
    <v_model v-model="searchText"></v_model>
    <p>{{searchText}}</p>
</div>
```



### 插槽

slot元素，在需要的地方加入插槽即可

即将父标签的text文本传入slot中





- value 绑定到一个名叫value的prop上
- 在其input事件被触发时，将新的值通过自定义的input抛出

