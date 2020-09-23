## Vuex使用

1.创建项目

```
vue webpack init demo
```

2.引入vuex

```
npm install vuex --save
```

```
//store.js
import Vue from 'vue'
import Vuex from 'vuex';
Vue.use(Vuex);
export default new Vuex.Store({
  //state 中存放得就是全局共享数据
  state:{

  },
  mutations:{

  },
  actions:{

  }
})

//main.js挂载到Vue实例上
import store from "./store";
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})

```

3.核心概念

- State

  State提供唯一得公共数据源，所有共享得数据都要统一放到Store中的State中进行存储

  - ```
    
    import Vue from 'vue'
    import Vuex from 'vuex';
    Vue.use(Vuex);
    export default new Vuex.Store({
      //state 中存放得就是全局共享数据
      state:{
          count:0,//测试存储
      },
      mutations:{
    
      },
      actions:{
    
      }
    })
    
    ```

  - 访问方式一  this.$store.state.count

    ```
     <h3>当前最新得Count值为:{{$store.state.count}}</h3>
      其他地方访问需要加上this
    ```

  - 访问方式二 

    ```
    vuex中按需导入mapState函数，在组件中导入函数mapstate
    import {mapState} from 'vuex';
    将当前组件需要得全局数据映射为当前组件得computed计算属性
    computed:{
       ...mapState(['key']);
    }
    在组件中即可用{{key}}来引用
    <h3>当前最新得Count值为:{{count}}</h3>
    ```

- 不推荐在组件中直接绑定事件修改全局数据（不利于后期维护）

- Mutation

- 触发mutations方式一

  用于变更Store中的数据

  - Vuex中智能通过Mutation变更Store中的数据，不可以直接操作Store中的数据

  - 通过这种方式虽然繁琐，但是可以集中监控所有数据得变化，方便后期得维护

  - 实例

    ```
    import Vue from 'vue'
    import Vuex from 'vuex';
    Vue.use(Vuex);
    export default new Vuex.Store({
      //state 中存放得就是全局共享数据
      state:{
          count:0,
      },
      mutations:{
          add(state){  //增加方法
            //变更状态
            state.count++;
          }
      },
      actions:{
    
      }
    })
    
    ```

    绑定事件，在事件中使用this.$store.commit('mutation中的函数名');来调用某个Mutation函数

    ```
      methods:{
                hander_add(){
                    this.$store.commit('add');
                }
            }
    ```

  - 传参实例

    ```
    import Vue from 'vue'
    import Vuex from 'vuex';
    Vue.use(Vuex);
    export default new Vuex.Store({
      //state 中存放得就是全局共享数据
      state:{
          count:0,
      },
      mutations:{
          add(state){
            //变更状态
            state.count++;
          },
          addN(state,step){
            state.count += step;
          }
      },
      actions:{
    
      }
    })
    
    ```

    ```
    hander_addN(){
        this.$store.commit('addN',3);
    }
    ```

- 触发mutations方式二

  - 引入mapMutations函数

    ```
    import {mapMutations} from 'vuex';
    ```

  - 将指定的mutations函数，映射为当组件的methods函数

    ```
          methods:{
                ...mapMutations(['add','addN'])
            }
    ```

  - 使用实例

    ```
      <button @click="hander_sub">-1</button>
      <button @click="hander_subN">-N</button>
        methods:{
                ...mapMutations(['subtract','subtractN']),
                hander_sub(){
                    this.subtract();
                },
                hander_subN(){
                    this.subtractN(3);
                }
            }
    ```

  - mutations里面不不可以执行异步操作

- Action 

  用于处理异步任务

  如果通过异步操作变更数据，必须通过Action，而不能使用mutation，但是在Action中还是要通过触发Mutation的方式间接变更数据