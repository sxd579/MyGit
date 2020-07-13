<template>
    <div style="background-color: white">
        <van-nav-bar title="修改密码" left-arrow left-text="返回" @click-left="back"/>
        <van-field v-model="oldPw" placeholder="请输入原密码" class="modifyField"/>
        <van-field v-model="newPw" placeholder="请输入新密码" class="modifyField1"/>
        <div class="modifyButton"><van-button round type="info" style="width: 75%" @click="ModifyPw">确认修改</van-button></div>
    </div>
</template>

<script>
    export default {
        name: "ModifyPassword",
        mounted(){
            let a = this.$route.params.a;
            let p = this.$route.params.p;
            this.account  = a;
            this.phone = p;
        },
        data(){
            return{
                oldPw:'',
                newPw:'',
                account:'',
                phone:''
            }
        },
        methods:{
            ModifyPw(){
                if(this.newPw === ''){
                    alert("密码不能为空！")
                }else {
                    this.axios.post("/api/user/modify/pwd",{
                        user_account:this.account,
                        olduser_passwd:this.oldPw,
                        newuser_passwd:this.newPw,
                        phone:this.phone
                    }).then((res)=>{
                        if(res.data.code === 1008){
                            console.log(res.data.data);
                            this.$router.push({
                                name:'personal_info'
                            })
                        }else {
                            alert(res.data.msg);
                        }
                    }).catch((error)=>{
                        alert(error)
                    })
                }
            },
            back(){
                this.$router.push({
                    name:'personal_info'
                })
            }
        }
    }
</script>

<style scoped>
    .modifyField{
        width: auto;
        padding-left: 0px;
        margin-left: 50px;
        margin-right: 50px;
        margin-top: 50px;
        font-size: 24px;
        line-height: 36px;
        border-bottom-color: gray;
        border-bottom-width: 0.7px;
        border-bottom-style: solid;
    }
    .modifyField1{
        width: auto;
        padding-left: 0px;
        margin-left: 50px;
        margin-right: 50px;
        margin-top: 10px;
        font-size: 24px;
        line-height: 36px;
        border-bottom-color: gray;
        border-bottom-width: 0.7px;
        border-bottom-style: solid;
    }
    .modifyButton{
        width: 100%;
        text-align: center;
        margin-top: 20px;
    }
</style>