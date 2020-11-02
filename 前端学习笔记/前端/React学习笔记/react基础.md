## React
------

#### react 开发环境的搭建

1. react.js  核心文件
2. react-dom.js  渲染页面中的DOM 当前文件依赖于react核心文件
3. babel.js   ES6转换成ES5  JSX语法转换成Javascript 现今浏览器进行代码的兼容

#### 下载

react 核心包   npm i react --save

react-dom    npm i react-dom --save

babel     npm i babel-standalone --save

```html
    <script src="node_modules/react/umd/react.development.js"></script>
    <script src="node_modules/react-dom/umd/react-dom.development.js"></script>
    <script src="node_modules/babel-standalone/babel.min.js"></script>
```

-----
####  jsx基本语法
1. jsx 的注释不能使用传统js和html的方式
{/**/};  单个元素不能使用该注释，必须有一个父元素包裹才能使用
```js
     let noUse =
        <div>
        {/*<h1>注释</h1>*/}
        </div>;
    ReactDOM.render(noUse,document.querySelector('#noUse'));

```

2. 多行标签必须有一个父元素包裹 推荐+()
```js
   let m =     
    <div>
        <div>first</div><div>Second</div>
    </div>;
    ReactDOM.render(m,document.querySelector('#m'));
```

3. 在html模板中使用  表达式，变量，函数，采取{expr}的方式
和vue不相同，vue使用的是{{expr}}

4. 三目运算符 和其他地方差不多
```js
  let phone = 4999;
    let expr2 = <div>
        phone is cost ￥{phone} --------- {phone>4000?'很贵':'正常'} 
    </div>
    ReactDOM.render(expr2,document.querySelector('#expr2'));
```

5. 渲染数组
数组中只能存放标签和内容 ,而后用{arr}.即可遍历渲染出arr中的所有标签
例
```js
   //渲染数组
    let arr = [
        <p>新闻列表1</p>,
        <p>新闻列表2</p>,
        <p>新闻列表3</p>,
        <p>新闻列表4</p>,
        <p>新闻列表5</p>,
        <p>新闻列表6</p>,
    ]
    let array = (
        <div>{arr}</div>
    )
    ReactDOM.render(array,document.querySelector('#items'));
```
6. 属性设置
   - 直接可以传入字符串的入 href
   - 传入对象的如style
   - jsx 不能使用class，因为class是关键字 需要替换为className
   ```js
    let text = 'Click Me';
    let url = 'http://www.baidu.com';
    // background-color -->backgroundColor
    let aStyle = {
        color:"red",
        backgroundColor:'yellow',
    }
    // jsx 不能使用class，因为class是关键字 需要替换为className
    let a = <a href={url} style={aStyle} className='cn'>{text}</a>;
    ReactDOM.render(a,document.querySelector('#clickA'));

   ```  
-----
#### 基本操作
1.遍历列表
    可以使用es6给数组的map方法, 且和vue的v-for相似，需要设置独一无二的key值，否则会报错，且return 时 推荐加上()，否则换行会出问题
    ```js
        let arr = ['吃饭','睡觉','打豆豆'];
        let myDom = arr.map((item,index)=>{
            console.log(item);
            // 和vue的v-for类似，需要设置独一无二的key值 否则会报错
            return <p key={index}>{item}</p>
        })
        ReactDOM.render(myDom,document.querySelector('#reactDemo'));
    ```
    不仅仅可以使用map函数，也可以用for-in  forEach

2.绑定事件,和重新渲染
  - 绑定事件
    - 绑定事件一定要是驼峰形式，如onClick
    - 绑定事件要写为箭头函数，否则会直接运行且后续点击无效
  - 重新渲染
    点击事件触发以后，不会重新渲染，因为只在初始化的时候渲染了一次，因此如果需要重新渲染的话，可以将DOM和render都各自封装为一个函数，在点击事件触发的时候，调用render函数，去获取当前DON节点的情况，渲染到界面上
    ```js
    let arr = ['吃饭','睡觉','打豆豆'];
    let index = 0;
    function fn(){
        let myDom = arr.map((item,i)=>{
        //绑定事件一定要是驼峰形式，如onClick  相当于vue的 onClick/@click
        //且绑定的函数要写为箭头函数
        return (<p key={i} style={{color: i==index?'green':''}} onClick={
            ()=>
            {   
                index = i;
                console.log(index);
                render();
            }
        }>{item}</p>)
    
       });

        return myDom;
    }
    function render(){
        ReactDOM.render(fn(),document.querySelector('#reactDemo'));
    }
    render();
    ```
3. 遍历对象
   Object.keys 获取键数组
   Object.values 获取值数组
   其余和遍历对象差不多
   ```js
      let obj = {
        name:'小明',
        age:'12',
    }
    console.log(Object.keys(obj));  // 键数组
    console.log(Object.values(obj));  // 值数组

    let myDOM = Object.keys(obj).map((v,i)=>{
         return (<p key={i}> 遍历的属性是: {v},值是:{obj[v]}</p>)
    })
    ReactDOM.render(myDOM,document.querySelector('#reactDemo'))
   ```
-----
#### React组件
分为三部分:
- 属性props
- 状态state
- 生命周期
-----
创建
1.函数组件/无状态组件(可以用function 也可以用 匿名箭头函数)
```js
// 无状态组件的创建方式,函数名一定要首字母大写
    function MyCom(){
        return (
            <div>
                我是一个无状态组件
            </div>)
    }
     // 组件就是自定义标签  单标签 多标签都可以
    let com = <div>
            <MyCom/>
            <MyCom></MyCom>
        </div>
    ReactDOM.render(com,document.querySelector('#reactDemo'));
```

   父子组件 上面的引申
```js
 //子组件
     function MyComA (){
         return (
                <div>
                  我是组件1    
                </div>
         )
     }   

     function MyComB (){
         return (
                <div>
                  我是组件2  
                </div>
         )
     }   

     function MyComC (){
         return (
                <div>
                  我是组件2    
                </div>
         )
     }   
     
     //父组件
     function MyComD (){
         return (
                <div>
                  我是组件3    
                </div>
         )
     }   
     
     function Com(){
         return (
             <div>
                <MyComA></MyComA>
                <MyComB></MyComB>
                <MyComC></MyComC>
                <MyComD></MyComD>   
             </div>
            

         )
     }
     ReactDOM.render(Com(),document.querySelector('#reactDemo'));
```

2. 类组件
es6类语法,继承React.Component
重新render函数 返回一个html
```js
//es6
     class MyCom extends React.Component{
         render(){
             return (
                 <div>类组件</div>
             )
         }
     }
     let com = <MyCom/>;
     ReactDOM.render(com,document.querySelector('#reactDemo'));
```
-----
props是react中一个重要的属性，是组件对外的接口，props可以让组件外部向组件内部传递数据
也可以完成父组件给子组件的数据传递
无论是无状态组件还是类组件都不能修改自身的props

1.无状态组件使用props
```js
  function MyCom (props){
            return (
                <div>
                  这是外部传来的一个数组:{props.content} ----{props.num};    
                </div>
            )
     }
     // 一个个属性写到标签上
    //  let demoData = 'React 真好用';
    //  let demoNum = 1111;
    //  ReactDOM.render(<MyCom content={demoData} num={demoNum}/>,document.querySelector('#reactDemo'));

     // es6 扩展运算符写法 ---推荐
     let demoData = {
         content:'React Props',
         num:666,
     }
     ReactDOM.render(<MyCom {...demoData}/>,document.querySelector('#reactDemo'));
```
默认值
- 在创建的函数下设置defaultProps:{key:value}
- es6语法 在函数内设置 props.key = props.key || value
```js
 // 默认值设置方式一    ---推荐
     function MyCom (props){
            return (
                <div>
                  这是外部传来的一个数组:{props.content} ----{props.num};    
                </div>
            )
     }
     MyCom.defaultProps = {
         content:'content的默认值',
         num:'num的默认值',
     }
     
     let demoData = {
        content:'content的数据',
         num:'num的数据',
     }

     //方式二  
    //  function MyCom (props){
    //      props.content = props.content||'content的默认值';
    //      props.num = props.num|| 'num的默认值';
    //         return (
    //             <div>
    //               这是外部传来的一个数组:{props.content} ----{props.num};    
    //             </div>
    //         )
    //  }
```
验证
导库 prop-types    npm i prop-types --save
```js
  //props验证 --- 验证传递进来的数据是否符合了我们期望的类型或者要求    上线模式建议取消props 因为，只会报错，不会影响运行
     //1.引入prop-type库 npm install prop-types --save 
     //  具体使用官方文档 https://react.docschina.org/docs/typechecking-with-proptypes.html
     MyCom.propTypes = {
         content:PropTypes.number // 验证name这个prop传递进来的必须是number类型 如果不是不影响运行，会报错
     }
```

2.类组件使用props
    和无状态组件基本一样
```js
    class MyCom extends React.Component{
        render(){
            return (
                <div>
                   我是类组件的 ---- {this.props.name} -----{this.props.num}    
                </div>
            )
        }
    }
    let obj = {
        name:'React',
        num:'666'
    }
    ReactDOM.render(<MyCom {...obj}/>,document.querySelector('#reactDemo'));
```
   默认值和验证
```js
 class MyCom extends React.Component{
        render(){
            return (
                <div>
                   我是类组件的 ---- {this.props.name} -----{this.props.num}    
                </div>
            )
        }
    }
    // 默认值
    MyCom.defaultProps = {
             name:'默认的name值',
             num:'默认的num值',
    }
    
    //props验证
    MyCom.propTypes = {
        name:PropTypes.number,
    }

    ReactDOM.render(<MyCom />,document.querySelector('#reactDemo'));
    // let obj = {
    //     name:'React',
    //     num:'666'
    // }
    // ReactDOM.render(<MyCom {...obj}/>,document.querySelector('#reactDemo'));
```
类组件还有一种默认值方法 (其实和上面方法类似,即在外部给类添加属性和内部给类添加属性)
```js
class MyCom extends React.Component{
        static defaultProps = {
             name:'默认的name值',
             num:'默认的num值',
    }
        render(){
            return (
                <div>
                   我是类组件的 ---- {this.props.name} -----{this.props.num}    
                </div>
            )
        }
    }
```
-----
state   props是组件对外的接口,state是组件对内的接口 
组件内可以引用其他组件，则形成了一个树状接口
props  下层如果需要上层的数据，则通过props将上层数据传递给下层
state  则组件管理自身数据时候需要使用到

react中我们只需要关心数据,当数据发生改变页面自动发生改变，
状态等同于页面中的数据， 状态/数据发生改变了， 页面中对应数据绑定的内容也会发生改变
这样就不用手动封装调用render,不需要我们关心页面render，只关心数据的改变即可

与props的区别是
1.state是可变的
2.props对于当前组件是只读的，要修改得修改父组件传进来的数据

**如果使用state则不能使用无状态组件**
- 要显示写constructor，则必须写super(),如果constructor中传入props，则super(props);
- 修改state不能采取直接赋值需要使用 this.setState({key:value}) 这是异步的，react在修改完毕后会自动重新render
```js
class MyCom extends React.Component{
        //es6中不管写不写constructor 在new实例的时候都会补上constructor
        //可以不写constructor,但是写的话,一定要写上super
        constructor(props){
            //如果想在用props,则super也必须协商props
            super(props);
            //定义state
            this.state = {
                content:'React State'
            }
        }
        render(){
            return (
                <div>
                <button onClick= {
                    ()=>{
                        // this.state.content += '!'; // 错误
                        // 要使用this.setState({key:value}), 异步，react会自动触发renderj进行数据的渲染
                        this.setState({content:this.state.content+'!'});
                    }
                }>
                    修改State
                </button>
                <div>
                   我是一个组件----{this.state.content}
                </div>
                </div>
              
            )
        }
    }
    ReactDOM.render(<MyCom />,document.querySelector('#reactDemo'));
```
state 进阶
- state异步 this.setState({value:key},callback)
    ```js
    class MyCom extends React.Component{
        //es6中不管写不写constructor 在new实例的时候都会补上constructor
        //可以不写constructor,但是写的话,一定要写上super
        constructor(props){
            //如果想在用props,则super也必须协商props
            super(props);
            //定义state
            this.state = {
                content:'React State',
                tp:`<p>这是一个state</p>`
            }
        }
        fn = ()=>{
                        // 箭头函数才能调用外部this，否则访问不到setState({key:value})
                        // this.state.content += '!'; // 错误
                        // 要使用this.setState({key:value}), 异步，react会自动触发renderj进行数据的渲染
                        this.setState({content:this.state.content+'!'},()=>{
                            //这样会在修改完值后调用，显示的会是最新的
                            console.log(this.state.content);
                        });
                        //因为是异步所以显示的是旧的，可以通过setState的第二个参数回调函数来使用
                        // console.log(this.state.content);
        }
        render(){
            return (
                <div>
                <button onClick= {
                    //函数可以写出去，传入不要加()，否则会立即调用
                    this.fn
                    // ()=>{
                    //     // this.state.content += '!'; // 错误
                    //     // 要使用this.setState({key:value}), 异步，react会自动触发renderj进行数据的渲染
                    //     this.setState({content:this.state.content+'!'});
                    // }
                }>
                   修改State
                </button>
                <div>
                   我是一个组件----{this.state.content}
                </div>
            )
        }
    }

- 解析state中字符串为html  用 dangerouslySetInnerHTML = {{__html:this.state.key}}
```js
                <div>
                {/* 字符串插入的话使用dangerouslySetInnerHTML = {{__html:要插入的字符串}} */}
                    {this.state.tp}  {/*会直接显示标签字符串*/}
                    <div dangerouslySetInnerHTML = {{__html:this.state.tp}}></div> {/*正常显示标签内容*/}
                </div>
            
```
-----
转发refs  react当中提供了一个ref的数据  (不能在无状态组件当中来进行使用 因为无状态组件没有实例)
    表示当前组件的真正实例的引用，他会返回绑定当前属性的元素
    标识组件内vu的元素---方便我们查找
    react给我们3种方式进行ref的使用
    - 字符串的形式
    - 回调函数(推荐)
        就是dom节点上或者组件上挂载函数，函数的实参形参是dom节点 ，他的效果和字符串的方式一样的都是获取值的引用
    - React.createRef()  (react16.3提供的一种方式)  把值给一个变量，通过ref挂载在节点或者组件上，使用ref的current属性得到这个节点
    ```js
    class MyCom extends React.Component{
        constructor(props){
                super(props);
                this.myRef = React.createRef();
        }
        fn = ()=>{
            // console.log(this.refs.demoInput.value);
            console.log(this.myRef.current.value);
        }
        render(){
            return (
                <div>
                   我是组件
                   {/*<input type="text" ref="demoInput" placeholder="请输入"></input>
                   <button onClick ={this.fn}>输出(字符串形式)</button>*/}
                   {/*<input type="text" ref="demoInput" placeholder="请输入"></input>
                   <button onClick ={()=>{this.fn();}}>输出(回调函数(推荐))</button>*/}
                   <input type="text" ref={this.myRef} placeholder="请输入"></input>
                   <button onClick ={()=>{this.fn();}}>输出(React.createRef())</button>
                </div>
            )
        }
    }
    let obj = {
        name:'React',
        num:'666'
    }
    ReactDOM.render(<MyCom {...obj}/>,document.querySelector('#reactDemo'));
    ```
    **建议不要过度使用ref,优先考虑state**
-----
事件与this
1.修改this指向
    - bind方式绑定 在组件中 onClick = {this.func.bind(this)}
    - 箭头函数方式绑定,在类中的函数 func = ()=>{}  onClick = {this.func}  箭头函数可以获取他父作用域的this
    - 提前方式绑定在constructor中提前给对应函数绑定好this  this.调用方法 = this.执行方法.bind(this)  onClick = {this.调用方法}
    - 在箭头函数中调用 obj.fn 则fn的this会指向obj 所以可以通过onClick = {()=>{this.fn();}}
2.函数参数传递和事件
    - onClick = {this.fn.bind(this,...args)}
    - onClick = {(e)=>{this.fn(e) // e代表事件}}
```js
class MyCom extends React.Component{
        constructor(props){
            super(props);
            //提前绑定
            this.func = this.funC.bind(this);
        }
        funA() {
            console.log(this);
        }
        funB = ()=>{
            console.log(this);
        }
        funC(){
            console.log(this);
        }
        funD(){
            console.log(this);
        }
        funcE = (i)=>{
            console.log(i);
        }
        render(){
            return (
                <div>
                   我是组件
                   {/*
                      <button onClick={this.funA}>bind方式绑定</button>   //输出undefined
                   */}
                   <button onClick={this.funA.bind(this)}>bind方式绑定</button>   
                   <button onClick={this.funB}>箭头函数方式绑定(指向父作用域的this)</button>   
                   <button onClick={this.func}>提前方式绑定(提前给对应函数绑定好this,调用以及绑定好this的函数)</button>
                   <button onClick={()=>{this.funD()}}>调用方式为箭头函数(函数this指向调用的函数的对象)</button>
                    <h1>函数参数传递</h1>
                    <button onClick={this.funcE.bind(this,'bind方式传实参')}>点我传实参</button>
                    <button onClick={(e)=>{this.funcE('调用方式为箭头函数的实参')}}>点我传实参2</button>
                </div>
            )
        }
    }
    ReactDOM.render(<MyCom/>,document.querySelector('#reactDemo'));
```
-----
条件渲染
在React中没有Vue封装好的条件渲染  -----根据状态变化渲染其中一部分
jsx中不允许用if 所以用state的方式，通过触发state来重新render
也可以在组件内部用三元运算符来条件渲染
```js
 class MyCom extends React.Component{
        //jsx中不允许用if 所以用state的方式，通过触发state来重新render
        constructor(props){
               super(props);
               this.state = {
                   bool:true,
               }
        }
        render(){
            let text;
            if(this.state.bool){
                text = '哈哈';
            }else{
                text = '嘻嘻';
            }
            return (
                <div>
                     <p>{text}</p>
                     <button onClick={()=>{this.setState({bool:!this.state.bool})}}>change state</button>
                </div>
            )
        }
    }

    ReactDOM.render(<MyCom/>,document.querySelector('#reactDemo'));
```
-----
状态提升
假设A组件和B组件需要一个公有的状态，则通过props在A,B组件中调用
用闭包的思想，给他们一个父组件，在父组件中设置state,以props的形式传给A,B
则这样A,B则公有这个父组件的state，如果修改了这个state，A,B组件都会发生变化
```js
  //在A组件和B组件公有一个状态,实现方式有点类似闭包的思想
    class DemoA extends React.Component{
        constructor(props){
            super(props);
           
        }
        render(){
            return (
                <div>
                    first   {this.props.pbc}
                </div>
              
            )
        }
    }

    class DemoB extends React.Component{
        constructor(props){
            super(props);
        }
        render(){
            return (
                <div>
                    Second  {this.props.pbc}
                </div>
              
            )
        }
    }

    class MyCom extends React.Component{
        constructor(props){
            super(props);
            this.state = {
                pbc:'这是一个公有的内容噢'
            }
        }
        render(){
            return (
                <div>
                    <DemoA pbc={this.state.pbc}/>
                    <DemoB pbc={this.state.pbc}/>
                    <button onClick={
                        ()=>{
                            this.setState({pbc:'这是修改了的公有内容'});
                        }
                    }>修改公有内容</button>
                </div>
            )
        }
    }
    ReactDOM.render(<MyCom />,document.querySelector('#reactDemo'));
```
-----
在WebStorm中会默认配置好，
在VSCode中，html文件引入本地js文件会产生跨域问题