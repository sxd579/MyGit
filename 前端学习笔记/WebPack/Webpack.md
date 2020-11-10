## Webpack

​		webpack是一个现代 JavaScript 应用程序的静态模块打包器(module bundler)。当webpack处理应用程序时，它会递归地构建一个依赖关系图(dependency graph),其中包含应用程序需要的每个模块，然后将所有这些模块打包成一个或多个bundle

-----

官方文档  https://www.webpackjs.com/

1. 初始化

   ```shell
   npm init -y
   ```

2. 安装

   ```shell
   npm install webpack webpack-cli --save-dev
   ```

   注：不推荐全局安装

   webpack-cli` : 提供 webpack 命令、工具，类似 `create-react-app

   webpack` : webpack 代码，类似 `react

3. 使用

   ```bash
   ./node_modules/.bin/webpack
   //查看版本
   ./node_modules/.bin/webpack -v
   ```

   也可以编辑 'package.json' 的 'scripts' 来简化输入

   ```json
   // package.json
   {
   	...,
   	"scripts": {
   		"start": "webpack"	// scripts 中可以定位到 ./node_modules/.bin/ 目录下
   	}
   }
   ```

   ​     `scripts` 中使用 `test`、`start`、`restart`、`stop` 命名的时候，可以在调用的时候省略 `run`，即直接 `npm start`

   当然，还可以使用更加方便的方式:

   ```bash
   npx webpack
   ```

   通过 `npx` 也可以帮助我们定位命令到 `./node_modules/.bin/` 目录下

    注：npm5.2+ 增加，如果没有，可以使用 npm i -g npx 来安装

4. 打包模块

   打包之前，我们需要了解一个概念，入口文件

   -  入口文件

     入口文件就是我们项目中加载的第一个文件，比如Vue中的mian.js文件，其他文件都是通过 `import` 等方式引入的，`webpack` 会从我们指定的入口文件开始分析所有需要依赖的文件，然后打包成一个完整文件。

     通过设置webpack.config.js的 

     ```js
     module.exports = {  
         ...   //配置项
     	entry：'path'  //入口文件                 
     }
     ```

     

   - 打包命令

     ```bash
     webpack ./js/index.js   //同样可以配置在package.json的scirpts下 简化命令
     ```

     上面命令会使用`webpack`  默认的一些配置对模块进行打包，并把打包后的文件输出到默认创建的 `./dist`  目录下，打包后的文件名默认为  `main.js`

     模块文件打包以后 ，就可以在不支持es6 模板语法的浏览器环境下引入使用了

     **打包文件分析**

     -  把分散的模块文件打包到一个文件中，不需要外部引入了

     -  内置了一个小型模块加载器(类似 `requireJS`), 实现了打包后代码的隔离与引用

       以上就是 webpack 最基础的使用于基本原理，当然强大的 `webpack` 远远不止这些功能。_

5. 打包配置

   虽然 ，我们可以直接通过命令来打包，但是推荐创建一个 `webpack.config.js` 的配置文件来实现更方便和强大的功能

   `webpack` 命令在运行的时候，默认会读取运行命令所在的目录下的`webpack.config.js`  文件 ，通常我们会在项目的根目录下运行命令和创建配置文件

   我们也可以通过 `--config` 选项来指定配置文件路径(不是很需要)

   ```bash
   webpack --config ./configs/my_webpack.config.js
   ```

   通常情况下，我们的项目目录大致如下:

   ```txt
   /
   -- /dist - 项目打包后存放目录
   -- /node_modules - 第三方模块
   -- /src
   ------ css/
   ------ images/
   ------ js/
   ------ index.js
   -- webpack.config.js
   -- package.json
   ```

   配置文件webpack.config.js

   ```js
   module.exports = {
     ...	//配置项
   }
   ```

   而在html中的script标签不再引入原来的入口文件 而是引入打包后的`main.js,`

   打包后就没有同步异步加载的问题了，都打包到一个文件中，解决了**依赖问题`**

6. 核心配置

   - mode

     模式: "production" | "development"|"none"

     不同模式会对 `webpack` 打包的时候进行一些对应的优化配置。

     development(开发模式):不压缩

     production(生产模式):压缩 

   - entry

     指定打包入口文件，有三种不同的形式

     string|object|array

     <!--一对一：一个入口、一个打包文件-->

     ```js
     module.exports = {
       entry: './src/index.js'
     }
     ```

     <!--多对一：多个入口、一个打包文件-->

     ```js
     module.exports = {
       entry: [
         './src/index1.js',
         './src/index2.js',
       ]
     }
     ```

     <!--多对多：多个入口、多打包文件-->

     ```js
     module.exports = {
       entry: {
         'index1': "./src/index1.js",
         'index2': "./src/index2.js"
       }
     }
     ```

   -  output

      entry对应要有output,默认输出的/dist/main.js  

       [name]是webpack内置的一个占位符，会被entry中的key值取代

       有多少个key就会输出多少个文件

       多个key也会默认输出多个js文件 以key为名 

     ```
     const path = require('path');
     module.exports = {
         mode:"development",
         entry: {
             "index1":'./src/a.js',
             "index2":'./src/a.js',
         },
         output:{
             path:path.resolve(__dirname,'dist'),
             //[name] 是webpack内置的一个占位符，会被entry中的key值取代
             filename:'[name].js',
         }
     }
     ```

        生成的文件结构

     ```txt
     --dist
     	-- index1.js
     	-- index2.js
     ```

7. Webpack:  

   在 `webpack` 中，有一个很重要的特性：模块不仅仅只是 `js` 的文件，`webpack` 可以把任意文件数据作为模块进行处理，包括：非 js 文本、css、图片等等

   ```txt
   解析入口 => 解析依赖 => import等导入 =>解析依赖=>导入依赖=>加载依赖的文件数据(二进制)=>?=>打包=>输出
   
   ?:对加载的依赖书据进行处理的过程,默认情况下，webpack会把数据作为js进行处理(如在txt文件中写一段代码，虽然他是txt文件仍然会被当做js解析使用)
   webpack提供了一个接口: loader,来处理?那一块的内容解析
   ```
- `loaders`：`webpack` 中灰常核心的内容之一，前面我们说的非 js 类型的模块处理就靠它了。webpack 可以使用 loader 来预处理文件。这允许你打包除 JavaScript 之外的任何静态资源。你可以使用 Node.js 来很简单地编写自己的 loader。
- `plugins`：`webpack` 中另外一个核心的内容，它主要是扩展 `webpack` 本身的一些功能。插件可以运行在 `webpack` 的不同阶段（钩子 / 生命周期）。
8. Loaders

   https://www.webpackjs.com/loaders/

   - 选择下载需要的loader

   - 配置模块loader解析规则(即什么后缀用什么loader)

     - raw-loader

       ```bash
       npm install --save-dev raw-loader
       ```

     ```js
      // 配置模块loader解析规则
         module:{
             //模块解析规则
             rules:[
                 //每一个规则就是一个对象
                 {
                     //当前解析的模块id(路径)
                     test:/\.txt$/i,
                     //匹配规则以后使用的loader
                     use:'raw-loader',
                 }
             ]
         }
     ```

     - file-loader

       把识别出的资源模块，移动到指定的输出⽬目录，并且返回这个资源在输出目录的地址(字符串)

       ```bash
       npm install --save-dev file-loader
       ```

       ```js
       rules: [
         ...,
       	{
       	test: /\.(png|jpe?g|gif)$/,
           use: {
             loader: "file-loader",
             options: {
               // placeholder 占位符 [name] 源资源模块的名称
               // [ext] 源资源模块的后缀
               name: "[name]_[hash].[ext]",
               //打包后的存放位置
               outputPath: "./images",
               //打包后文件的url  相对于调用者打包后存放的位置 
           	publicPath:'../dist/images',
             }
           }
       	}
       ]
       ```

     - url-loader

       可以处理理 `file-loader` 所有的事情，但是遇到图片格式的模块，可以选择性的把图片转成 `base64` 格式的字符串，并打包到 `js` 中，对⼩体积的图片⽐较合适，⼤图⽚不合适。

       - 安装

         ```bash
         npm install --save-dev url-loader
         ```

       - ```js
         rules: [
           ...,
         	{
         	test: /\.(png|jpe?g|gif)$/,
             use: {
               loader: "url-loader",
               options: {
                 // placeholder 占位符 [name] 源资源模块的名称
                 // [ext] 源资源模块的后缀
                 name: "[name]_[hash].[ext]",
                 //打包后的存放位置
                 outputPath: "./images",
                 //打包后文件的url  相对于调用者打包后存放的位置 
             	publicPath:'../dist/images',
                 // 小于100字节转化为base64格式
                 limit:100,
               }
             }
         	}
         ]
         ```

     - css-loader

       分析 `css` 模块之间的关系，并合成⼀个 `css`

       ```bash
       npm install --save-dev css-loader
       ```

       ```js
         {
              test:/\.css$/,
              use:{
              loader:"css-loader",
              options: {
              // 启用/禁用 url()处理   需要把突变也作为模块解析时
               url:true,
               // 启用/禁用 @import 处理
               import:true,
               // 启用/禁用 SourceMap
               sourceMap:false,
                           }
                       }
                   }
       ```

       

     - style-loader

       把 `css-loader` 生成的内容，用 `style` 标签挂载到⻚面的 `head` 中

       ```bash
       npm install --save-dev style-loader
       ```

       ```js
       rules: [
         ...,
       	{
       		test: /\.css$/,
           use: ["style-loader", "css-loader"]
       	}
       ]
       //同一个任务的 `loader` 可以同时挂载多个，处理顺序为：从右到左，也就是先通过 `css-loader` 处理，然后把处理后的 `css` 字符串交给 `style-loader` 进行处理
       rules: [
         ...,
       	{
       		test: /\.css$/,
           use: [
         		{
         			loader: 'style-loader',
         			options: {}
         		},
             'css-loader'
       		]
       	}
       ]
       ```

       

