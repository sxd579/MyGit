<template>
    <div>
        <van-nav-bar title="我的账号" left-arrow left-text="返回" @click-left="back"/>
        <van-cell-group>
            <van-cell title="头像" is-link center @click="showPopup">
                <van-image
                        round
                        height="3rem"
                        width="3rem"
                        fit="cover"
                        :src="headPath"
                ></van-image>
            </van-cell>
            <van-cell title="账号" :value="useraccount" value-class="accValue"/>
            <van-cell title="用户名" :value="username" @click="changeUsername" is-link/>
            <van-cell title="密码" value="修改密码" @click="changePw" is-link/>
            <van-cell title="手机号" :value="phoneNumber" @click="changePhone" is-link/>
            <van-cell title="性别" :value="sex" @click="changeSex" is-link/>
            <van-cell title="出生日期" :value="birthday" @click="changeBirthday" is-link/>
            <van-cell title="省份" :value="province" @click="changeProvince" is-link/>
        </van-cell-group>
        <van-button class="exitButton" block @click="exitAccount">退出当前账号</van-button>
        <van-popup v-model="show" position="bottom">
            <van-cell-group>
                <van-uploader :after-read="afterRead" max-count="1">
                    <van-cell title="更换头像" clickable/>
                </van-uploader>
                <van-cell title="取消" clickable @click="hidePopup"/>
            </van-cell-group>
        </van-popup>
        <van-popup v-model="showSexPicker" round position="bottom">
            <van-picker
                    title="选择性别"
                    show-toolbar
                    :columns="columns"
                    @cancel="showSexPicker = false"
                    @confirm="onConfirmS"
            />
        </van-popup>
        <van-popup v-model="showDatePicker" round position="bottom">
            <van-datetime-picker
                    v-model="currentDate"
                    type="date"
                    title="选择生日"
                    :min-date="minDate"
                    :max-date="maxDate"
                    @cancel="showDatePicker = false"
                    @confirm="onConfirmD"
            />
        </van-popup>
        <van-popup v-model="showProPicker" round position="bottom">
            <van-area
                    title="选择省份"
                    show-toolbar
                    :area-list="areaList"
                    :columns-num="1"
                    visible-item-count="4"
                    @cancel="showProPicker = false"
                    @confirm="onConfirmP"
            />
        </van-popup>
    </div>
</template>

<script>
    import Vue from 'vue';
    import { Cell, CellGroup, Icon, Popup, Image as VanImage, Button, Area, Picker } from 'vant';
    import areaList from "@/assets/area";
    Vue.use(Cell).use(CellGroup).use(Icon).use(Popup).use(VanImage).use(Button).use(Area).use(Picker);
    export default {
        name: "PersonalInfo",
        data(){
            return{
                useraccount:'',
                username:'',
                phoneNumber:'',
                sex:'',
                birthday:'',
                province:'',
                columns:['man','woman'],
                areaList,
                headPath:'',
                show:false,
                showSexPicker:false,
                showProPicker:false,
                showDatePicker:false,
                minDate: new Date(1900, 1, 1),
                maxDate: new Date(),
                currentDate: new Date()
            }
        },
        created(){
          this.axios.get("/api/user/personal", {
              headers:{
                  token:this.$store.state.Authorization
              }
          }).then((res) => {
              if(res.data.code === 200){
                  this.useraccount = 'user_account' in res.data.data?res.data.data.user_account:'';
                  this.username = 'user_name' in res.data.data?res.data.data.user_name:"未设置";
                  this.phoneNumber = 'phone' in res.data.data?res.data.data.phone:"未设置";
                  this.sex = 'gender' in res.data.data?res.data.data.gender:"未设置";
                  this.birthday = 'birthday' in res.data.data?res.data.data.birthday:"未设置";
                  this.province = 'province' in res.data.data?res.data.data.province:"未设置";
              }else {
                  alert(res.data.msg);
              }
          }).catch((error) => {
              alert(error)
          });
          this.axios.get("/api/user/headportrait", {
              headers:{
                  token:this.$store.state.Authorization
              }
          }).then((res1)=>{
              if(res1.data.code === 200){
                  this.headPath = res1.data.data.headportrait !== ''?res1.data.data.headportrait:'https://img.yzcdn.cn/vant/cat.jpeg'
              }else {
                  alert(res1.data.msg);
              }
          }).catch((error1)=>{
              alert(error1)
          })
        },
        methods:{
            showPopup(){
                this.show=true
            },
            hidePopup(){
                this.show=false
            },
            exitAccount(){
                localStorage.removeItem("Flag");
                localStorage.removeItem("Authorization");
                this.$store.dispatch("delToken", '');
                this.$router.push({
                    name:'mine'
                })
            },
            changeUsername(){
                this.$router.push({
                    name:'modify_username'
                })
            },
            changePw(){
                this.$router.push({
                    name:'modify_pw',
                    params:{
                        a:this.useraccount,
                        p:this.phoneNumber
                    }
                })
            },
            changePhone(){
                this.$router.push({
                    name:'modify_phone'
                })
            },
            changeSex(){
                this.showSexPicker=true
            },
            changeProvince(){
                this.showProPicker=true
            },
            changeBirthday(){
                this.showDatePicker=true
            },
            onConfirmS(value) {
                this.axios.post("/api/user/modify/gender",{
                    gender:value
                },{
                    headers: {
                        token:this.$store.state.Authorization
                    }
                }).then((res)=>{
                    console.log(res.data.data);
                    this.sex = value;
                    this.showSexPicker = false;
                }).catch((error)=>{
                    alert(error)
                })
            },
            onConfirmP(value) {
                this.axios.post("/api/user/modify/province",{
                    province:value[0].name
                },{
                    headers:{
                        token:this.$store.state.Authorization
                    }
                }).then((res)=>{
                    console.log(res.data.data);
                    this.province = value[0].name;
                    this.showProPicker = false;
                }).catch((error)=>{
                    alert(error)
                })
            },
            onConfirmD(value) {
                let year = value.getFullYear();
                let month = value.getMonth() + 1;
                let day = value.getDate();
                if (month >= 1 && month <= 9) { month = `0` + month }
                if (day >= 1 && day <= 9) { day = `0` + day }
                this.axios.post("/api/user/modify/birthday",{
                    birthday:year + '-' + month + '-' + day
                },{
                    headers:{
                        token:this.$store.state.Authorization
                    }
                }).then((res)=>{
                    console.log(res.data.data);
                    this.birthday = year + '-' + month + '-' + day;
                    this.showDatePicker = false;
                }).catch((error)=>{
                    alert(error)
                })
            },
            back(){
                this.$router.push({
                    name:'mine'
                })
            },
            afterRead(file){
                let content = file.file;
                const formData = new FormData();
                formData.append('image-file', content);
                // formData.append('0', file);
                // Object.keys(file).forEach((key) => {
                //     console.log(key, file[key]);
                //     formData.append(key, file[key]);
                // });
                // console.log(formData[0]);
                this.axios.put("/api/file", formData).then((res)=>{
                    if(res.data.code === 200){
                        // console.log(res.data);
                        this.axios.post("/api/user/modify/headportrait", {
                            headportrait:res.data.data
                        },{
                            headers:{
                                token:this.$store.state.Authorization
                            }
                        }).then((res1)=>{
                            console.log(res1.data);
                            if(res1.data.code === 1008){
                                this.headPath = res.data.data;
                                this.show = false;
                            }else {
                                alert(res1.data.msg);
                            }
                        }).catch((error1)=>{
                            alert(error1)
                        });
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
    .exitButton{
        color: red;
        position: fixed;
        bottom:0;
        width: 100%;
        height: 4rem;
    }
    .accValue{
        padding-right: 20px;
    }

</style>