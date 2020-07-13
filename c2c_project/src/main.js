import Vue from 'vue'
import VueRouter from 'vue-router'
import Vuex from "vuex"
import HomeActivity from "@/components/activity/HomeActivity";
import {
    Tabbar,
    TabbarItem,
    Col,
    Row,
    Button,
    Field,
    Radio,
    RadioGroup,
    List,
    Skeleton,
    Panel,
    Cell,
    Uploader,
    CountDown,
    Tag,
    Stepper, DatetimePicker, Icon, Switch, Rate, PullRefresh
} from 'vant';
import App from "@/App";
import NearbyActivity from "@/components/activity/NearbyActivity";
import ReleaseActivity from "@/components/activity/ReleaseActivity";
import OrderActivity from "@/components/activity/OrderActivity";
import MineActivity from "@/components/activity/MineActivity";
import TaskDetails from "@/components/view/TaskDetails";
import axios from "axios"
import VueAxios from "vue-axios";
import VueAMap from "vue-amap/src/lib";
import ChooseAddressActivity from "@/components/activity/release/ChooseAddressActivity";
import PersonalInfo from "@/components/activity/personal/PersonalInfo";
import HistoricalTask from "@/components/activity/personal/HistoricalTask";
import {Popup} from "vant";
import ExecuteOrder from "@/components/view/ExecuteOrder";
import WaitingOrder from "@/components/view/WaitingOrder";
import LoginActivity from "@/components/activity/login/LoginActivity";
import "@/assets/iconfont/iconfont.css";
import PasswordLogin from "@/components/activity/login/PasswordLogin";
import CAPTCHAPage from "@/components/activity/login/CAPTCHAPage";
import ManageTagActivity from "@/components/activity/release/ManageTagActivity";
import WaitingEvaluate from "@/components/view/WaitingEvaluate";
import Register from "./components/activity/login/Register";
import ModifyUsername from "./components/activity/personal/ModifyUsername";
import ModifyPassword from "./components/activity/personal/ModifyPassword";
import ModifyPhone from "./components/activity/personal/ModifyPhone";
import EvaluationDetail from "@/components/view/EvaluationDetail";
Vue.use(Button)
    .use(TabbarItem)
    .use(Tabbar)
    .use(Col)
    .use(Row)
    .use(VueRouter)
    .use(Field)
    .use(RadioGroup)
    .use(Radio)
    .use(VueAxios,axios)
    .use(List)
    .use(VueAMap)
    .use(Cell)
    .use(Popup)
    .use(Skeleton)
    .use(Panel)
    .use(Uploader)
    .use(CountDown)
    .use(Tag)
    .use(Stepper)
    .use(DatetimePicker)
    .use(Icon)
    .use(Switch)
    .use(Vuex)
    .use(Rate)
    .use(PullRefresh)

Vue.prototype.$axios=axios
axios.defaults.baseURL="/api"
axios.defaults.headers.post["Access-Control-Allow-Origin"]="*"
axios.defaults.headers.post["Access-Control-Allow-Headers"]="Content-Type, Content-Length, Authorization, Accept, X-Requested-With"
axios.defaults.headers.post["Access-Control-Allow-Methods"]="PUT,POST,GET,DELETE,OPTIONS"
axios.defaults.headers.post["X-Powered-By"]="3.2.1"
axios.defaults.headers.post["Content-Type"]="application/json;charset=utf-8"
Vue.config.productionTip = false
VueAMap.initAMapApiLoader({
  key:'a9594943c05b599c149817c8b6988053',
  plugin: ['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor','AMap.GeometryUtil','AMap.Geolocation'],
  // 默认高德 sdk 版本为 1.4.4
  v: '1.4.4',
  uiVersion:'1.0.11'
})


const router=new VueRouter({
  routes:[
    {
      path: "/",
      component: App,
        meta: {
            isLogin: false
        }
    },
    {
      path:'/home',
      name:'home',
      component:HomeActivity,
        meta: {
            isLogin: false
        }
    },
    {
      path: '/nearby',
      name: 'nearby',
      component: NearbyActivity,
        meta: {
            isLogin: true
        }
    },
    {
      path: '/release',
      name: 'release',
      component: ReleaseActivity,
        meta: {
            isLogin: true
        }
    },
    {
      path: '/order',
      name: 'order',
      component: OrderActivity,
        meta: {
            isLogin: true
        }
    },
    {
      path: '/mine',
      name: 'mine',
      component: MineActivity,
        meta: {
            isLogin: false
        }
    },
    {
      path:'/task_detail',
      name:'task_detail',
      component:TaskDetails,
        meta: {
            isLogin: false
        }
    },
    {
      path:'mine/personal_info',
      name:'personal_info',
      component:PersonalInfo,
      meta: {
          isLogin: true
      }
    },
    {
      path:'mine/historical_task',
      name:'historical_task',
      component:HistoricalTask,
        meta: {
            isLogin: true
        }
    },
    {
      path: 'release/chooseAddress',
      name: 'chooseAddress',
      component: ChooseAddressActivity,
        meta: {
            isLogin: false
        }
    },
    {
      path:'/execute_order',
      name:'execute_order',
      component:ExecuteOrder,
        meta: {
            isLogin: false
        }
    },
    {
      path:'/wait_order',
      name:'wait_order',
      component:WaitingOrder,
        meta: {
            isLogin: false
        }
    },
    {
      path: '/login',
      name: 'login',
      component: LoginActivity,
      meta: {
          isLogin: false
      }
    },
    {
      path:'login/pw_login',
      name:'pw_login',
      component:PasswordLogin,
      meta: {
          isLogin: false
      }
    },
    {
      path:'login/CAPTCHA',
      name:'CAPTCHA',
      component:CAPTCHAPage,
      meta: {
          isLogin: false
      }
    },
    {
      path: '/release/manageTag',
      name: 'manageTag',
      component: ManageTagActivity,
        meta: {
            isLogin: false
        }
    },
      {
          path: '/wait_evaluate',
          name: 'wait_evaluate',
          component: WaitingEvaluate,
          meta: {
              isLogin: false
          }
      },
      {
          path:'login/register',
          name:'register',
          component:Register,
          meta: {
              isLogin: false
          }
      },
      {
          path:'mine/personal_info/modify_username',
          name:'modify_username',
          component:ModifyUsername,
          meta: {
              isLogin: true
          }
      },
      {
          path:'mine/personal_info/modify_pw',
          name:'modify_pw',
          component:ModifyPassword,
          meta: {
              isLogin: true
          }
      },
      {
          path:'mine/personal_info/modify_phone',
          name:'modify_phone',
          component:ModifyPhone,
          meta: {
              isLogin: true
          }
      },
      {
          path:'/evaluation_detail',
          name:'evaluation_detail',
          component: EvaluationDetail,
          meta: {
              isLogin: false
          }
      }
  ]
})
Vue.mixin({
  beforeRouteLeave:function(to, from, next){
    if (!to.params["cache"])
    {//此处判断是如果返回上一层，你可以根据自己的业务更改此处的判断逻辑，酌情决定是否摧毁本层缓存。
      if (this.$vnode && this.$vnode.data.keepAlive)
      {
        if (this.$vnode.parent && this.$vnode.parent.componentInstance && this.$vnode.parent.componentInstance.cache)
        {
          if (this.$vnode.componentOptions)
          {
            var key = this.$vnode.key == null
                ? this.$vnode.componentOptions.Ctor.cid + (this.$vnode.componentOptions.tag ? `::${this.$vnode.componentOptions.tag}` : '')
                : this.$vnode.key;
            var cache = this.$vnode.parent.componentInstance.cache;
            var keys  = this.$vnode.parent.componentInstance.keys;
            if (cache[key])
            {
              if (keys.length) {
                var index = keys.indexOf(key);
                if (index > -1) {
                  keys.splice(index, 1);
                }
              }
              delete cache[key];
            }
          }
        }
      }
      this.$destroy();
    }
    next();
  },
});
const store = new Vuex.Store({
    // 设置属性
    state: {
        isLogin: false,
        Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : ''
    },
    getters: {
        isLogin: state => state.isLogin,
        Authorization:state => state.Authorization
    },
    // 设置属性状态
    mutations: {
        //保存登录状态
        userStatus(state, flag) {
            state.isLogin = flag
        },
        // 修改token，并将token存入localStorage
        changeToken (state, token) {
            state.Authorization = token;
            localStorage.setItem('Authorization', token);
        }
    },
    actions: {
        userLogin({commit}, flag){
            commit("userStatus", flag)
        },
        setToken({commit}, token){
            commit("changeToken", token)
        },
        delToken({commit}, token){
            commit("changeToken", token)
        }
    }
})
router.beforeEach((to, from, next) => {
    let getFlag = localStorage.getItem("Flag");
    //如果登录标志存在且为isLogin，即用户已登录
    if(getFlag === "isLogin"){
        store.state.isLogin = true
        next()
        // if (!to.meta.isLogin) {
        //     next({
        //         path: '/home'
        //     })
        // }

        // 如果登录标志不存在，即未登录
    }else{
        if(to.meta.isLogin){
            next({
                name:'login'
            })
        }else{
            next()
        }

    }

});
// router.afterEach((route) => {
//     window.scroll(0, 0);
// });
new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')


