<template>
    <div style="background-color: white">
        <div style="background-color: white">
            <van-icon name="cross" size="26px" color="#008bfa" style="margin: 20px" @click="close"/>
        </div>
        <div class="titleBar">欢迎注册</div>
        <p class="tag1">账号长度应为1-16位，密码长度应为6-16位</p>
        <van-field v-model="account" placeholder="请输入账号" class="accountField"/>
        <van-field v-model="password" type="password" placeholder="请输入密码" class="accountField"/>
        <van-field v-model="password_confirm" type="password" placeholder="请确认密码" class="accountField"/>
        <div class="registerButton"><van-button round type="info" style="width: 75%" @click="Register">注册账号</van-button></div>
    </div>
</template>

<script>
    import Vue from 'vue';
    import { Toast } from 'vant';

    Vue.use(Toast);
    export default {
        name: "Register",
        data(){
            return{
                account:'',
                password:'',
                password_confirm:''
            }
        },
        methods:{
            close(){
                this.$router.go(-1)
            },
            Register(){
                if(this.account === '' || this.password === '' || this.password_confirm === ''){
                    alert("账号/密码不能为空！")
                }else if(this.account.length > 16){
                    alert("账号长度应为1-16位！")
                }else if(this.password.length < 6 || this.password.length > 16){
                    alert("密码长度应为6-16位！")
                }else {
                    if(this.password !== this.password_confirm){
                        alert("密码输入不一致！")
                    }else {
                        this.axios.post("/api/user/registering",{
                            user_account: this.account,
                            user_passwd: this.password
                        }).then((res)=>{
                            if(res.data.code === 200){
                                console.log(res.data.data);
                                Toast("注册成功！");
                                this.$router.push({
                                    name:'pw_login',
                                    params:{acc:this.account}
                                });
                            }else {
                                alert(res.data.msg)
                            }
                        }).catch((error)=>{
                            alert(error)
                        })
                    }
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
    .tag1{
        font-size:12px;
        font-weight: lighter;
        color: #717070;
        padding-left: 50px;
        margin-top: 5px;
        /*background-color: white;*/
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
    .registerButton{
        width: 100%;
        text-align: center;
        margin-top: 50px;
    }
</style>