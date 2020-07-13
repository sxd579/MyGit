<template>
    <div style="background-color: white" id="login">
        <div style="background-color: white">
            <van-icon name="cross" size="26px" color="#008bfa" style="margin: 20px" @click="close"/>
            <span class="register" @click="RegisterPage">立即注册</span>
        </div>
        <div class="titleBar">欢迎登录</div>
        <van-field v-model="loginPhone" type="tel" placeholder="请输入手机号" class="phoneField"/>
        <p class="tag1">已绑定手机号的用户可使用短信验证码直接登录</p>
        <div class="loginButton"><van-button round type="info" style="width: 75%" @click="sendCAPTCHA">获取短信验证码</van-button></div>
        <div class="otherLogin">
            <p style="color: #717070;font-size: 15px">其他登录方式</p>
            <div style="width: 50%;display: inline-block;text-align: right">
                <van-icon class="iconfont" class-prefix="icon" name="weixin" size="50px" color="green" style="padding-right: 15px" @click="wxLogin"/>
            </div>
            <div style="width: 50%;display: inline-block;text-align: left">
                <van-icon class="iconfont" class-prefix="icon" name="mima" size="50px" color="gray" style="padding-left: 15px" @click="pwLogin"/>
            </div>
        </div>
    </div>
</template>

<script>
    import Vue from 'vue';
    import { NavBar, Icon, Field, Divider, Button } from 'vant';

    Vue.use(NavBar).use(Icon).use(Field).use(Divider).use(Button);
    export default {
        name: "LoginActivity",
        data() {
            return{
                loginPhone:''
            }
        },
        // watch:{
        //   $route:{
        //       handler:function (route) {
        //           this.redirect = route.query && route.query.redirect
        //       },
        //       immediate:true
        //   }
        // },
        methods:{
            close(){
                this.$router.push({
                    name:'mine'
                })
            },
            RegisterPage(){
                this.$router.push({
                    name:'register'
                })
            },
            wxLogin(){

            },
            pwLogin(){
                this.$router.push({
                    name:'pw_login'
                })
            },
            sendCAPTCHA(){
                if(this.loginPhone === ''){
                    alert("手机号不能为空！")
                }else if(this.loginPhone.length !== 11){
                    alert("请输入正确格式的手机号！")
                }else {
                    this.axios.post("/api/sms/send", {
                        phone:this.loginPhone
                    }).then((res)=>{
                        if(res.data.code === 200){
                            console.log("验证码发送成功");
                            this.$router.push({
                                name:'CAPTCHA',
                                params:{tel:this.loginPhone}
                            })
                        }else {
                            alert(res.data.msg);
                        }
                    }).catch((error)=>{
                        alert(error)
                    })
                }
            }
        }
    }
</script>

<style scoped>
    .register{
        float: right;
        margin: 20px;
        color: #008bfa;
        font-size: 18px;
    }
    .titleBar{
        background-color: white;
        font-size: 28px;
        margin-left: 50px;
        margin-top: 50px;
    }
    .phoneField{
        width: auto;
        padding-left: 0px;
        margin-left: 50px;
        margin-right: 50px;
        margin-top: 16px;
        font-size: 24px;
        line-height: 36px;
        border-bottom-color: gray;
        border-bottom-width: 0.7px;
        border-bottom-style: solid;
    }
    .tag1{
        font-size:12px;
        font-weight: lighter;
        color: #717070;
        padding-left: 50px;
        margin-top: 5px;
        /*background-color: white;*/
    }
    .loginButton{
        width: 100%;
        text-align: center;
        margin-top: 20px;
    }
    .otherLogin{
        width: 100%;
        position: fixed;
        bottom:0;
        margin-bottom: 40px;
        text-align: center;
    }

</style>