<template>
    <div style="background-color: white">
        <van-nav-bar title="修改手机号" left-arrow left-text="返回" @click-left="back"/>
        <van-field v-model="newPhone" type="tel" placeholder="请输入新手机号" class="modifyField"/>
        <div class="modifyButton"><van-button round type="info" style="width: 75%" @click="ModifyPhone">确认修改</van-button></div>
    </div>
</template>

<script>
    export default {
        name: "ModifyPhone",
        data(){
            return{
                newPhone:''
            }
        },
        methods:{
            ModifyPhone(){
                if(this.newPhone.length !== 11){
                    alert("请输入正确格式的手机号！")
                }else {
                    this.axios.post("/api/user/modify/phone",{
                        phone:this.newPhone
                    },{
                        headers: {
                            token:this.$store.state.Authorization
                        }
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
    .modifyButton{
        width: 100%;
        text-align: center;
        margin-top: 20px;
    }
</style>