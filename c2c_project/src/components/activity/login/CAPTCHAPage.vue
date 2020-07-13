<template>
    <div style="background-color: white">
        <div style="background-color: white">
            <van-icon name="arrow-left" size="26px" color="#008bfa" style="margin: 20px" @click="close"/>
        </div>
        <div class="titleBar">请输入验证码</div>
        <p class="tag1">已向{{this.LoginTel}}发送验证码，有效期为5分钟</p>

        <div style="text-align: center">
            <van-password-input
                    :value="value"
                    :length="6"
                    :focused="showKeyboard"
                    :mask="false"
                    @focus="showKeyboard = true"
                    style="width: 90%;margin: 0 auto"
            />
        </div>
        <p class="tag2">若验证码发送失败，请点击重新发送验证码</p>
        <div class="tag3" @click="Resend">重新发送验证码</div>
        <van-number-keyboard
                :show="showKeyboard"
                @input="onInput"
                @delete="onDelete"
                @blur="showKeyboard = false"
        />
    </div>
</template>

<script>
    import Vue from 'vue';
    import { Icon, Field, Button, PasswordInput, NumberKeyboard, Toast } from 'vant';

    Vue.use(Icon).use(Field).use(Button).use(PasswordInput).use(NumberKeyboard).use(Toast);
    export default {
        name: "CAPTCHAPage",
        mounted(){
            let loginTel = this.$route.params.tel;
            this.LoginTel  = loginTel;
        },
        data(){
            return {
                value:'',
                showKeyboard:true,
                LoginTel:''
            }
        },
        methods:{
            onInput(key) {
                this.value = (this.value + key).slice(0, 6);
                if(this.value.length === 6){
                    this.axios.post("/api/sms/login", {
                        phone:this.LoginTel,
                        code:this.value
                    }).then((res)=>{
                        if(res.data.code === 200){
                            console.log(res.data.data);
                            var Token = res.data.data;
                            this.$store.dispatch("userLogin", true);
                            this.$store.dispatch("setToken",Token);
                            localStorage.setItem("Flag", "isLogin");
                            this.$router.push({
                                name:'mine'
                            });
                        }else {
                            alert(res.data.msg)
                        }
                    }).catch((error)=>{
                        alert(error)
                    })
                }
            },
            onDelete() {
                this.value = this.value.slice(0, this.value.length - 1);
            },
            close(){
                this.$router.go(-1)
            },
            Resend(){
                this.axios.post("/api/sms/send", {
                    phone:this.LoginTel
                }).then((res)=>{
                    if(res.data.code === 200){
                        console.log("验证码发送成功");
                        Toast("已重新发送验证码");
                    }else {
                        alert(res.data.msg);
                    }
                }).catch((error)=>{
                    alert(error)
                })
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
        font-size:14px;
        font-weight: lighter;
        color: #717070;
        padding-left: 50px;
        margin-top: 5px;
        /*background-color: white;*/
    }
    .tag2{
        font-size:14px;
        font-weight: lighter;
        color: #717070;
        padding-left: 50px;
        margin-top: 20px;
        /*background-color: white;*/
    }
    .tag3{
        font-size:16px;
        font-weight: normal;
        color: #008bfa;
        padding-left: 50px;
        margin-top: 5px;
        /*background-color: white;*/
    }
</style>