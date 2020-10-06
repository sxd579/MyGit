## 手写Vue

1.正常使用引入Vue

```css
 <div id="app">
        <input type="text" v-model="mine.val">
        <div>{{mine.val}}</div>
        <input type="text">
        <div>手写Vue</div>
        {{mine.val}}
    </div>
```

```js
let vue = new Vue({
        el:'#app',
        data:{
             mine:{
                 val:'哈哈'
             }
        }
    })
```

2.自己手写创建myVue.js文件 ，首先创建Vue类，且从html文件中引入

  首先编写Vue类的基本结构

```js
class Vue{
     consturctor(options){
         // $el $data
         this.$el = options.el;
         this.$data = options.data;
         //首先，要做的是把data中的数据编译到模板中去
         if(this.$el){
             //编译
             // 第一个参数传要编译到的元素，第二个参数用于获取数据
             new Compiler(this.$el,this);
         }
     }
     
}
```

3.编写Compiler类

- 首先判断传入的el元素是不是节点

  ```js
  Class Compiler{
       consturctor(el,vm){
         //获取需要编译的元素
           this.el  =  this.isElement(el)?el:document.queryElement(el);
           this.vm  = vm;
           //因为反复修改节点会触发多次回流，重绘，为了避免这样，就可以运用先把节点放到内存中，待节点替换完毕后再取出，这样就只有头尾两次重绘，回流，提高了性能
           let fragment = this.node2fragment(this.el)''
       }
       isElement(node){
           return node.nodeType === 1 ; //判断传入的是节点还是字符串
       }
  }
  ```

- 若是要将数据更新到界面上，则需要考虑重绘回流

```js
Class Compiler{
     consturctor(el,vm){
       //...
       //因为反复修改节点会触发多次回流，重绘，为了避免这样，就可以运用先把节点放到内存中，待节点替换完毕后再取出，这样就只有头尾两次重绘，回流，提高了性能
         let fragment = this.node2fragment(this.el)''
     }
    //...
    node2fragment(node){
        //创建文档碎片
        let fragment = document.createDocumentFragment();
        let firstChild;
        while(firstChild = node.firstChild){
            //appendChild具有移动性
            fragment.appendChild(firstChild);
        }
        //循环完毕后，元素下的子节点全都放入文档碎片中,接下来在碎片操作
        return fragment;
    }
}
```

- 获取节点之后，则需要替换节点中的命令内容，用数据编译模板

  ```js
  Class Compiler{
       consturctor(el,vm){
         //...
         //用数据编译模板
           this.compile(fragment);
       }
      //...
      compile(fragment){
          //首先取出fragment所有子节点,childNodes只能获得一层子节点，所以后续子节点的操作需要递归进行
          let childNodes = frament.childNodes;
          [...childNodes].forEach(child=>{
              //child分为元素节点和文本节点
              //元素节点则需要检查其attributes 有没有v-model v-xxx等
              //判断元素节点可以用到之前对于传入的el判断类型的函数
              if(this.isElement(child)){
                  //编译元素
                  compileElement(child);
                  //是元素后续还需要检查他是否有节点，因为childNodes只能检查一层，则递归编译
                  this.compile(child);
              }
              //文本节点则需要检查其{{}}
              else{
                  //编译文本
                  compileText(child);
                  
              }
          })
      }
     
  }
  ```

  ```js
  Class Compiler{
       consturctor(el,vm){
         //...
         //用数据编译模板
           this.compile(fragment);
       }
      //...
      compile(fragment){
      //...将节点分为元素和文本编译
      }
      isDirective(name){
          return name.startsWith('v-');
      }
      compileElement(node){
          //如果元素则获取其属性
          let attrs = node.attributes;
          [...attrs].forEach(attr=>{
              //对于每一个属性attribute 都有name和value
              //用解构赋值
              let {name,value:expr} = attr;
              //对于name非v-开头的无需操作
              if(isDirective(name)){
                  //则获取命令来执行相应的函数
                  let [,directive] = name.split('v-');
                  //创建一个编译的工具对象
                  CompileUtils[directive](node,expr,this.vm);
              }
          })
      }
      compileText(node){
          //如果是文本则需要是对于{{xxx}}替换
          let content = node.textContent;
          if(/\{\{(content)\}\}/.test(content)){
              CompileUtils['text'](node,expr,this.vm);
          }
      }
     
  }
  ```

  - 写编译的工具类

    ```js
    CompileUtil{
        
        //拆分x.y.z的链式调用
        getValue(expr,vm){
           //可以用Array的reduce方法，取出最里面的值
            return expr.spilt('.').reduce((data,cur)=>{
                return data[cur];
            },vm.$data)
            
        },
        //v-model v-html  v-name
        //model修改的是节点的value值
        //html修改的是innerHTML 其余操作大体相似，所以可以把最后不同的操作封装到一个对象里面
        model(node,expr,vm){
            //传过来的expr 可能是链式调用的 要从vm中获取值
            // 不可以vm[x.y]
            // 需要vm[x][y]
            let value = getValue(expr,vm);
            //获取value,之后调用公共对象中的方法来使用
            let fn = this.updater.modelUpdater;
            fn(node,value);
        },
        html(){
        
        },
        text(node,expr,vm){
            //传进来的expr是{{xxxx}}所以要先取出来xxxx，且是对expr的替换
            let content = expr.replace(/\{\{(.+?)\}\}/g,(...args)=>{
                // args  就是 x.y.z的形式，与element一样去链式调用
                return this.getValue(args[1],vm)
            })
            let fn = this.updater.textUpdater;
            fn(node,value);
        
        },
        updater:{
            modelUpdater(node,value){
                node.value = value;
            }
            htmlUpdater(node,value){
                
            }
            text(node,value){
                node.textContent = value;
            }
        }
    
    }
    ```

  - 到这里编译部分就结束了，那么怎么实现响应式呢

    vue2中实现的方式是对象通过Object.definedProperty();进行数据劫持

    ```
    class Vue{
         consturctor(options){
             // $el $data
             this.$el = options.el;
             this.$data = options.data;
             //首先，要做的是把data中的数据编译到模板中去
             if(this.$el){
                 //编译
                 // 第一个参数传要编译到的元素，第二个参数用于获取数据
                 //把数据 全部转化成用Object.defineProperty来定义
                 new Observer(this.$data)
                 new Compiler(this.$el,this);
             }
         }
         
    }
    ```

    也就是发布订阅模式

    观察者 被观察者 存放观察者的地方

    - 先是Observer ，给每个对象类型数据的每个属性绑定上get和set方法

      访问对象则调用get方法返回正常值，如果存在watcher.target则绑定到存放watcher的dep上，如果是修改对象则会调用set方法，且通知更新

    ```js
    class Observer{
         consturctor(data){
             this.observer(data);
         }
         observer(data){
             if(data&&typeof data == 'object'){
                 //如果是对象的话
                 for(let key in data){
                     this.defineReactive(data,key,data[key]);
                 }
             }
         }
         //先给每个对象属性绑定上getter和setter
        defineReactive(obj,key,value){
            //如果value也是对象，则递归，让每一层都绑定上getter和setter
            Object.defineProperty(obj,key,{
                //访问该属性用getter
                get(){
                    return value;
                }
                //如果修改该属性则会调用setter
                set:(newValue)=>{
                   if(newValue!=value){
                       //修改的newValue也要重新添加getter和setter
                       this.observer(newVal)
                       value = newValue;
                   } 
                }
            })
        }
    }
    ```

  - Watcher 创建观察者

    ```js
    class Watcher{
        consturctor(vm,expr,callback){
            this.vm = vm;
            this.expr = expr;
            this.callback = callback;
            this.oldValue = this.get();
        }
        get(){
            let value =              CompileUtil.getValue(this.vm,this.expr);
            return value;
        }
        update(){
            // 通知更新时
            let newValue = CompileUtil.getValue(this.vm,this.expr);//访问当前data里面的值
            if(newValue !== this.oldValue){
                this.callback(newValue);
            }
        }
    }
    ```

  - Dep存放watcher

    ```js
    class Dep{
         constructor(){
             this.subs = [];//存放watcher
         } 
         addSub(watcher){
             this.subs.push(watcher);
         }
         notify(){
              this.subs.forEach(watcher=>{
                  watcher.update();
              })
         }
    }
    ```

  - 创建则需要给每个属性绑定上watcher，让他们在初始化的时候，先生成watcher，加入Dep.subs，回调函数就是他们要执行的函数，则修改CompileUtil对象

    ```js
    CompileUtil:{
        model(node,expr,vm){
            let fn = this.updater.modelUpdater
            let value = this.getValue(vm,expr);
            fn(node,value);
            //第一次编译完后，创建属性的watcher
            new Watcher(vm,expr,(newValue)=>{
                fn(node,newValue);
            })
        }
        getCurrentValue(expr,vm){
            //即同样的方法，替换每个{{expr}} 只不过是由多个{{}}时，不论哪个修改 偶会触发整个text得重新获取
            return expr.replace(/\{\{(.+?)\}\}/,(...args)=>{
                return this.getValue(vm,args[1]);
            })
        }
        text(node,expr,vm){
            let fn = this.updater.textUpdater;
            let content = expr.replace(/\{\{(.+?)\}\}/,   (...args)=>{
                //初始化时，给每一个属性{{a}} {{b}}全都绑定单独的watcher，如果一个修改，这修改这一整块text
                new Watcher(vm,expr.(newValue)=>{
                    fn(node,this.getCurrentValue(expr,vm))
                })
                
                return this.getValue(vm,args[1]);
            })
            fn(node,content);//初始化
        }
    }
    ```

  - watcher在创建的时候，get方法在获取value之前设置target，而后获取值时触发Observer中给每个属性绑定的Observer，在这里把自己添加入全局的Dep.subs

    ```js
    class Watcher{
         //....
         
         get(){
             //设置Dep.target = this;
             Dep.target = this;
             //访问时，触发Observe的getter
             let vlaue  = CompileUtil.getValue(vm,expr);
             Dep.target = null;
             return value;
         }
    }
    
    class Observer{
        //...
        defineReactive(obj,key,value){
            //...递归
            this.observer(value);
            // 闭包 所有绑定getter 和 setter的属性公有一个dep
            let dep = new Dep();
            Object.defineProperty(obj,key,{
                get(){
                    //初始化时添加的
                    if(Dep.target){
                        dep.addSub(Dep.target);
                    }
                    return value;
                }
                set:(newVal){
                   //重新监控
                   this.observer(newVal);
                   value = newVal;
                   dep.notify();//通知更新
                   //整体步骤
                   // 初始化，给每个需要观察的创建观察者，创建观察则时会设置oldValue用于之后的更新操作的比较，会调用get方法，在里面将Dep.target设置为自己这个观察者，然后访问vm实例上绑定的expr的值，此时会触发Observer的getter函数，检查如果有Dep.target则说明是在初始化状态,往由于闭包而公有的dep对象的subs中,来保存观察者,如果没有则说明不是从watcher的访问过来的,即正常访问,添加完毕后,回到watcher的get方法,将Dep.target设置为null，因为这个watcher已经添加进去了。之后每当有修改属性的操作就会调用Observer的setter函数，则通过这个函数调用公有dep的notify通知操作，让所有观察者都去更新，在里面判断如果值修改了就更新，调用callback函数，获取新值，传入编译工具对象 CompileUtil.updater,对视图进行更新。
            }
            })
        }
    }
    ```

    