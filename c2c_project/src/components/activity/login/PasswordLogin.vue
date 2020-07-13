<template>
    <div style="background-color: white">
        <div style="background-color: white">
            <van-icon name="cross" size="26px" color="#008bfa" style="margin: 20px" @click="close"/>
        </div>
        <div class="titleBar">密码登录</div>
        <van-field v-model="account" placeholder="请输入账号" class="accountField"/>
        <van-field v-model="password" type="password" placeholder="请输入密码" class="accountField"/>
        <div class="loginButton"><van-button round type="info" style="width: 75%" @click="PWLogin">登录</van-button></div>
    </div>
</template>

<script>
    import Vue from 'vue';
    import { Icon, Field, Button } from 'vant';

    Vue.use(Icon).use(Field).use(Button);
    export default {
        name: "PasswordLogin",
        mounted(){
            let a = this.$route.params.acc;
            this.account  = a;
        },
        data() {
            return {
                account: '',
                password:''
            }
        },
        methods:{
            close(){
                this.$router.push({
                    name:'login'
                })
            },
            PWLogin(){
                if(this.loginPhone === ''||this.password === ''){
                    alert("账号/密码不能为空！")
                }else{
                    this.$axios.post("/api/user/login",{
                        useraccount:this.account,
                        userpasswd:this.password
                    }).then((res) => {
                        if(res.data.code === 200){
                            var Token = res.data.data[0];
                            // alert(Token);

                            this.$store.dispatch("userLogin", true);
                            this.$store.dispatch("setToken",Token);
                            localStorage.setItem("Flag", "isLogin");

                            this.$router.push({
                                name:'mine'
                            });
                        }else {
                            alert(res.data.msg);
                        }
                    }).catch((error)=>{
                            alert("login fail");
                            alert(error);
                        })
                }
            }
        }
    }
</script>

<style scoped>
    .titleBar{
        background-color: white;
        font-size: 28px;
        margin-left: 50px;
    }
    .accountField{
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
    .loginButton{
        width: 100%;
        text-align: center;
        margin-top: 50px;
    }
</style>