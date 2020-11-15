## React脚手架
-----
安装
npm install -g create-react-app
安装成功
create-react-app --version
新建
cd 需要创建的文件夹 create-react-app 项目名(不要中文和特殊符号) 创建项目
启动
npm start

-----
- 构建的结构
    - public 静态资源文件夹
    - src 写代码的地方
        - src下手动创建文件夹components 在这里面写组件.js
        - 输入rcc快速初始化react框架
    - 使用组件 在App.js中删除根元素地下的默认内容, 然后在App.js中 import 组件 from components   然后就可以使用了
        ```js
                import Home from './componnets/Home'
                function App() {
                return (
                    <div className="App">
                        <Home></Home>
                    </div>
                );
                }
        ```
    - 组件中多个标签也一样一定要有一个父标签包裹起来,如果不想新增标签节点
        - 可以在外层标签只加两个空标签  <></>
        - import React, { Component,Fragment } from 'react';  外层标签用 <Fragment></Fragment>
    - 静态资源引入   <img src='1.png'></img>    自动会去 public文件夹 下找 1.png
    - 静态资源引入 也可以在src下创建 assets文件夹 采用import的方式  import ImgA from '../assets/2.png'; 
      然后在src = {ImgA}
    - 静态资源也可以在src = {require('path')}等
        ```js
                    import ImgA from '../assets/2.png';
                    class Home extends Component {
                        render() {
                            return (
                                <Fragment>
                                    你好我是组件{parseInt(Math.random()*10)}
                                    <img src='1.png'></img>
                                    <img src={ImgA}></img>
                                </Fragment>
                            );
                        }
                    }    
        ```
-----
脚手架组件之间传值
### 父传子(React和Vue一样)
和之前一样可以用props ，使用方式一样
            ```js
            //Child.js
            import React, { Component } from 'react';

            class Child extends Component {
                render() {
                    return (
                        <div>
                            你好
                            {this.props.text}
                        </div>
                    );
                }
            }

            export default Child;
            //Parent.js
            import React, { Component } from 'react';
            import Child from './Child';
            class Parent extends Component {
                render() {
                    return (
                        <div>
                            <Child text='Parent Msg => Child'/>
                        </div>
                    );
                }
            }

            export default Parent;
            //App.js
            import './App.css';
            import Parent from './componnets/Parent'
            function App() {
            return (
                <div className="App">
                    <Parent></Parent>
                </div>
            );
            }
            export default App;
            ```js

输入rccp快速创建带props的模板
state的引用和修改和之前不用脚手架一样，修改this指向等都一样

### 子传父
Vue 通过监听子组件的事件，触发父组件定义的函数，子组件通过触发函数emit来触发父组件定义函数对应的事件
React 通过父组件通过props把函数传给子组件 子组件通过触发事件，来调用this.props.FatherFn.bind(this,childArgs)来触发父组件的函数，并将子组件的数据传入该函数中
```js
//父
class TransValueFather extends Component {
    constructor(props){
        super(props);
    
    }
    FatherFn(...args){
        console.log('父组件函数');
        console.log(args);
    }
    render() {
        return (
            <div>
                <Child fatherFn = {this.FatherFn}></Child>
            </div>
        );
    }
}
//子
class TransValueChild extends Component {
    constructor(props){
        super(props);
        this.state = {
            num:123,
            childTest:'我是子组件的数据',
        }
    }
    render() {
        return (
            <div>
                <button onClick={this.props.fatherFn.bind(this,this.state.childTest)}>子组件按钮</button>
            </div>
        );
    }
}
```

### 同级传值
Vue使用EventBus 采取一个公有的Vue实例,各个组件在上面绑定事件和对应函数，也在上面触发emit触发相应事件，并且传参
React 采用pubsub 本质和EventBus一样
#### 使用pubsub    
安装  npm i pubsub-js --save
主要使用两个函数
    - 发送方: PubSub.publish('事件名','参数');
    - 接收方: PubSub.subscribe('事件名',callBack:(event,data)=>{});
Pubsub.publish由于严格模式会触发两次
```js
//接收方
class PubSubA extends Component {
    constructor(props){
        super(props);
        //PubSub.subscribe（'监听的事件',callback(event,msg)）
        this.state = {
            data:'A自己的数据'
        }
        PubSub.subscribe('pbe',(e,msg)=>{
            this.setState({data:msg});
            console.log("A收到了B传来的数据:",msg);
        })
    }
    render() {
        return (
            <div>
            <h1>{this.state.data}</h1>
            </div>
        );
    }
}
//发送方
class PubSubB extends Component {
    constructor(props){
        super(props);
        this.state = {
            content:'我是B的数据',
        }
    }
    pubsub(){
        console.log("B开始传输");
        // PubSub.publish('抛出事件名','参数')
        PubSub.publish('pbe',this.state.content);
    }
    render() {
        return (
            <div>
                <button onClick={this.pubsub.bind(this)}>点我进行B=>A</button>
            </div>
        );
    }
}
```
-----
数据请求和json-server

数据请求 npm i axios --save
引入库以后，用法和vue中axios差不多
```js
  axios.get('http://localhost:4000/arr').then(res=>{
            console.log(res);
  })
```
模拟数据json-server  npm i json-server --save
1.cd到mock文件夹下
2.启动 json-server data.json --port 4000
3.http://localhost:4000/arr访问

-----
跨域问题
1.正向代理--开发环境 一个位于客户端和目标服务器之间的代理服务器 为了获取目标服务器的内容 客户端向代理服务器发送一个请求，
代理服务器帮助我们去目标服务器里面获取数据并且返回我们(前端)
node_modules->react-scripts->config->webpackDevSever.config.js->table
```js
proxy:{
      "/api":{
        target:"http://www.weather.com.cn/data/cityinfo/", //请求地址
        changeOrigin:true,
        "pathRewrite":{
          "^/api":"/"
        }
      }
    },
```
只会即可跨域向服务器请求数据

2.反向代理--上线环境 可以通过代理服务器来接受网络上的连接请求 然后将这个请求转发给内部的网络服务器上，并且把这个服务器上得到的
数组返回给请求的客户端(后端)

-----
React路由
根据url不同来切换组件，实现spa(单页应用) 在页面切换的时候不会刷新 更加接近原生体验
1.下载  npm install react-router-dom --save

react-router 只提供了一些核心API
react-router-dom 提供了更多的一些选项

路由模式:(和vue-router基本一样)
hash  HashRouter (hash 模式 通过修改#部分，刷新时页面不会丢失)
browser BrowserRouter  (history模式 通过历史记录api进行路由切换,刷新会丢失，本地模式不会)

2.在index.js引用
import {BrowserRouter,HashRouter} from 'react-router-dom'

3.将App根组件进行包裹 
路由模式包裹根组件
```js
ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter><App /></BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);
```
4.在App.js引用路由模块出口(使路由在组件中生效)
import {Route} from 'react-router-dom'

5.注册router列表
```js
        <Route path='/home' component={Home}>Home.js</Route>
        <Route path='/parent' component={Parent}>Parent.js</Route>
```
即可通过 http://localhost:3000/home 这样的地址来访问相应的组件

6.切换为hash模式
在index.js中修改根组件包裹为
```js
 <HashRouter><App /></HashRouter>
```
即此时地址变为 http://localhost:3000/#/home 的形式

7.路由导航
- Link
在App.js中多引入一个Link
import {Route,Link} from 'react-router-dom'
通过Link标签 配合to属性实现页面的跳转
```js
 <div>
        <Link to='/'>前往app</Link>
        <Link to='/home'>前往home</Link>
        <Link to='/parent'>前往parent</Link>
 </div>
```
- NavLink
NavLink相比Link可以动态设置样式类active,可以修改App.css中active样式来使用
import {Route,Link,NavLink} from 'react-router-dom'
```js
        <div>
        <NavLink to='/home'>前往home</NavLink>
        <NavLink to='/parent'>前往parent</NavLink>
        </div>
```
Route的path设置为'/'时候，每次都会匹配到，都会显示
```js
<Route path='/' component={Home}>Home.js</Route>
```
如果不需要则通过引入属性exact 则不会在其子url时候匹配到

- Switch
当一个路由被多次匹配到,会多次渲染,解决这要给问题则需要引入Switch
import {Route,Link,NavLink,Switch} from 'react-router-dom'
这样就不会重复渲染，解决唯一渲染
```js
        <Switch>
        <Route path='/' exact component={Home}>Home.js</Route>
        <Route path='/home' component={Home}>Home.js</Route>
        <Route path='/parent' component={Parent}>Parent.js</Route>
        <Route path='/parent' component={Parent}>Parent.js</Route>
        </Switch>

```

- Redirect
import {Route,Link,NavLink,Switch,Redirect} from 'react-router-dom'
重定向
```js
 <Redirect from='/' to='parent' exact></Redirect>
```

- 在二级页面使用
类似在App.js中一级路由的使用
```js
                import {Route,NavLink} from 'react-router-dom'
                <NavLink to="/home/homea">前往homea</NavLink>
                <NavLink to="/home/homeb">前往homeb</NavLink>
                <Route to='/home/homea' exact></Route>
                <Route to='/home/homeb' exact></Route>
```
- 路由进阶与路由高级使用
    - withRouter 使不是路由切换的组件也有路由切换的功能，也有其三个属性(location match history) 添加在组件的props里
    高阶组件 HOC 参数使一个组件 返回的也是一个组件，这类组件称之为高阶组件
    ```js
        // app.js
        import {Route,Link,NavLink,Switch,Redirect,withRouter} from 'react-router-dom'
        export default withRouter(App);

    ````
    比如 history可以监听路由状况
    ```js
        props.history.listen((link)=>{
        console.log(link);
         })
    ```
    - 编程式导航 js导航
        通过withRouter变为高阶组件后 通过 props.history.push('url')
    ```js
        <button onClick={()=>{props.history.push('/home')}}>跳转home</button>
        <button onClick={()=>{props.history.push('/parent')}}>跳转parent</button>
    ```
    - 路由传参
        - 使用props(刷新地址，参数依然存在，只能传递字符串，参数多的时候url很丑)
            - 需要在路由规则中设置传递接受的参数  :xxx
            ```js
                <Route path='/parent/:id' component={Parent}></Route>
            ```
            - 发送参数直接在跳转路径进行编写
            ```js
            <button onClick={()=>{props.history.push('/parent/我是参数')}}>跳转parent</button>
            ```
            - 接受参数 this.props.match.params.参数名
            ```js
                componentDidMount(){
                console.log(this.props.match.params.id);
                }
            ```
        - query方式(不需要再路由规则中传递参数的配置,适合传递更加复杂的参数)
            - 直接发送数据 通过{pathname:xxx,query:{key:value}}

            ```js
            <button onClick={()=>{props.history.push({pathname:'/parent',query:{name:'小铭'}})}}>跳转parent</button>
            <NavLink to={{pathname:'/parent',query:{name:'小铭'}}}>前往parent</NavLink>
            ```

            - 使用this.props.location.query.属性名 接受参数

            ```js
            console.log(this.props.location.query.name);
            ```
-----
ReactHook(react16.7新增的的特性) 主要是用来让无状态组件可以使用状态，之前状态管理需要类组件和redux管理
import React, { Component,useState } from 'react';
   - useState是来定义一个状态的，与类组件的状态不同，函数组件的状态可以是对象，也可以是基础类型数据
   - useState返回的是一个数组，第一个是当前的状态值，第二个是对象表明用于更改状态的函数(类似setState) 
     let [val,setVal] = useState(状态值) 即可取得状态值和设置状态值的函数

        ```js
            let [val,setVal] = useState(0);
            <div>
                使用数据:{val},
                <button onClick={()=>{setVal(val+1)}}>修改数据</button>
            </div>
        ```

        - 多状态 
        - 对象方式声明  不方便修改,要用其他方式修改
            ```js
                    let [val,setVal] = useState({
                        vala:0,
                        valb:1,
                        valc:2,
                    })

                    <div>
                        使用数据:{val.vala},
                        使用数据:{val.valb},
                        使用数据:{val.valc},
                        <button onClick={()=>{setVal(val.vala+1)}}>修改数据</button>
                    </div>
        
            ```
        - 多次声明 即多个  let [valn,setValn] = useState(n);  (推荐使用，修改更加方便)

-----
redux
- js提供的一个可预测性的状态容器，集中管理react中多个组件的状 类似vuex
- 需求场景
    - 多个组件的状态需要共享的时候
    - 一个组件需要改变另一个组件的状态的时候
    - 组件中的状态需要在任何地方都可以拿到
- 三大原则:
    - 单一数据源  整个react中的状态都会被统一的管理到store
    - state是只读的 我们不能够直接改变state 而是要通过触发redux中的特定方法来进行修改
    - 使用纯函数来执行修改操作: action来改变redux中的state
- 下载 npm npm install redux --save

- 使用 在src下新建文件夹redux,里面新建一个js文件reducer.js
  新建一个js文件 store.js   一个应用程序只能由一个store对象

- 在哪个页面有哦那个就需要引入store文件
  import {store} from '../redux/store'


