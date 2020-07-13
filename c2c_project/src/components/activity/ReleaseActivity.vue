<template>
    <div >
        <my-nav-bar m-title="发布任务"/>
        <div >
            <van-field v-model="tTitle" label="任务标题" placeholder="请输入任务标题" style="margin-top: 10px"/>
            <van-field v-model="tDetail" type="textarea" rows="3" autosize maxlength="200" show-word-limit label="任务详情" placeholder="请输入任务详情" style="margin-top: 5px"/>
            <van-field v-model="tBudget" type="number" label="预算/元" placeholder="请输入任务预算" style="margin-top: 5px"/>
            <van-cell title="时间要求" style="margin-top: 40px">
                <div style="height: 30px" @click="onChooseTimeStart">
                    {{dateFormat("YYYY-mm-dd HH:MM",tTimeStart)}} ~
                </div>
                <div style="height: 30px" @click="onChooseTimeEnd">
                    {{dateFormat("YYYY-mm-dd HH:MM",tTimeEnd)}}
                </div>
            </van-cell>
            <van-cell title="需求人数/人" style="margin-top: 5px">
                <van-stepper v-model="tTargetNum" integer />
            </van-cell>
            <!--        <van-radio-group v-model="tRadio" direction="horizontal" style="padding-left: 19px;padding-right: 19px;margin-top: 30px;margin-bottom: 30px">-->
            <!--            <van-col span="8">-->
            <!--                <van-radio checked-color="#ffa92b" name="1" >选择</van-radio>-->
            <!--            </van-col>-->
            <!--            <van-col span="8">-->
            <!--                <van-radio checked-color="#ffa92b" name="2" >简答</van-radio>-->
            <!--            </van-col>-->
            <!--            <van-col span="8">-->
            <!--                <van-radio checked-color="#ffa92b" name="3" >混合</van-radio>-->
            <!--            </van-col>-->
            <!--        </van-radio-group>-->
            <van-cell title="管理标签" is-link @click="onManageTag" style="margin-top: 20px"/>
            <van-cell title="选择地址" is-link @click="onChooseAddress" style="margin-top: 5px"/>
            <van-field v-if="tLng!=null && tLng!='null'" v-model="tAddress" label="地址" placeholder="请输入详细地址" style="margin-top: 0px">
                <template #right-icon>
                    <van-icon name="cross" @click="onCloseAddress"/>
                </template>
            </van-field>
            <van-cell title="是否需要拍照" style="margin-top: 20px">
                <van-switch v-model="tFlag"/>
            </van-cell>
        </div>
        <van-button class="confirm" @click="onConfirm" color="#ffa92b" block>确认发布</van-button>
        <van-popup position="bottom" v-model="showChooseTimeStart" style="height: 300px">
            <van-datetime-picker type="datetime" @cancel="onCancelWhenChooseTime" @confirm="onChosenTimeStart" :min-date="timeMin" :max-date="tTimeEnd"/>
        </van-popup>
        <van-popup position="bottom" v-model="showChooseTimeEnd" style="height: 300px">
            <van-datetime-picker type="datetime" @cancel="onCancelWhenChooseTime" @confirm="onChosenTimeEnd" :min-date="tTimeStart" :max-date="timeMax"/>
        </van-popup>
    </div>
</template>

<script>
    import MyNavBar from "@/components/view/reusable/MyNavBar";
    export default {
        name: "ReleaseActivity",
        components: {MyNavBar},
        data(){
            return{
                tTitle:"",
                tDetail:"",
                tTimeLimit:"",
                tTargetNum:1,
                tBudget:"0.00",
                tTimeStart:new Date(),
                tTimeEnd:new Date(new Date().getTime()+3600*24*30*1000),
                tLng:null,
                tLat:null,
                tAddress:"",
                timeMin:new Date(),
                timeMax:new Date(new Date().getTime()+3600*24*30*1000),
                tTags:[],
                tFlag:false,
                height:749-100,
                showChooseTimeStart:false,
                showChooseTimeEnd:false
            }
        },
        created(){
            localStorage.setItem('lng_chooseAddressActivity',null)
            localStorage.setItem('lat_chooseAddressActivity',null)
            localStorage.setItem('address_chooseAddressActivity',null)
            localStorage.setItem("tag_manageTagActivity",null)
        },
        methods:{
            onCloseAddress(){
              this.tLat=null
              this.tLng=null
              this.tAddress=""
            },
            onChosenTimeStart(time){
                this.tTimeStart=time
                this.showChooseTimeStart=false
            },
            onChosenTimeEnd(time){
                this.tTimeEnd=time
                this.showChooseTimeEnd=false
            },
            onCancelWhenChooseTime(){
                this.showChooseTimeStart=false
                this.showChooseTimeEnd=false
            },
            onChooseTimeStart(){
                this.showChooseTimeStart=true
            },
            onChooseTimeEnd(){
                this.showChooseTimeEnd=true
            },
            onConfirm(){
                let sTags=[]
                for(var i=0;i<this.tTags.length;i++){
                    var s="\""+this.tTags[i]+"\""
                    sTags.push(s)
                }
                let data={
                    task_name:this.tTitle.toString(),
                    detail:this.tDetail.toString(),
                    task_tag:"["+sTags.toString()+"]",
                    target_num:this.tTargetNum,
                    budget:parseFloat(this.tBudget),
                    release_time:this.dateFormat("YYYY-mm-dd HH:MM:SS",this.tTimeStart),
                    end_time:this.dateFormat("YYYY-mm-dd HH:MM:SS",this.tTimeEnd),
                    flag:this.tFlag ? "2" : "1",
                    Lon:this.tLng,
                    Lat:this.tLat,
                    location:this.tAddress,
                }
                // if(this.tLat != null && this.tLat != "null"){
                //
                // }
                this.axios.post("/api/task/release",data,{
                    headers:{
                        token:this.$store.state.Authorization
                    }
                })
                    .then((res)=>{
                        console.log(res)
                        // alert("发布成功！请耐心等待管理员审核！")
                    // alert("succeed")
                        let s=res.data.data
                        // alert(s)
                        let id=parseInt(s.substring(5))
                        this.$router.replace({
                            name:'task_detail',
                            params:{
                                id:id
                            }
                        })
                }).catch((error)=>{
                    alert(error)
                    console.log(error)
                    alert(error)
                    alert("发布任务失败！请检查您的登录状态后重试！")
                })
            },
            onChooseAddress(){
                this.$router.push({
                    name:'chooseAddress',
                    params:{
                        cache:true,
                        lat:this.tLat,
                        lng:this.tLng
                    }
                })
            },
            onManageTag(){
                this.$router.push({
                    name:'manageTag',
                    params:{
                        cache:true,
                        tags:this.tTags
                    }
                })
            },
            dateFormat(fmt, date) {
                let ret;
                const opt = {
                    "Y+": date.getFullYear().toString(),        // 年
                    "m+": (date.getMonth() + 1).toString(),     // 月
                    "d+": date.getDate().toString(),            // 日
                    "H+": date.getHours().toString(),           // 时
                    "M+": date.getMinutes().toString(),         // 分
                    "S+": date.getSeconds().toString()          // 秒
                    // 有其他格式化字符需求可以继续添加，必须转化成字符串
                };
                for (let k in opt) {
                    ret = new RegExp("(" + k + ")").exec(fmt);
                    if (ret) {
                        fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
                    }
                }
                return fmt;
            }
        },
        watch:{
            tBudget(newVal,oldVal){
                var reg = /^(\d{0,8})(\.(\d{0,2}))?$/g;
                if(!reg.test(newVal)){
                    if(newVal == ''){
                        this.tBudget = 0.00;
                        return;
                    }
                    this.tBudget = oldVal
                }
            }
        },
        activated(){
            var tags=localStorage.setItem("tag_manageTagActivity",null)
            if(tags != null&&tags != 'null'){
                this.tTags=tags
                localStorage.setItem("tag_manageTagActivity",null)
            }
            let tLat=localStorage.getItem("lat_chooseAddressActivity")
            if(tLat != null&&tLat != 'null'){
                this.tLat=tLat
                this.tLng=localStorage.getItem("lng_chooseAddressActivity")
                this.tAddress=localStorage.getItem("address_chooseAddressActivity")
                localStorage.setItem('lng_chooseAddressActivity',null)
                localStorage.setItem('lat_chooseAddressActivity',null)
                localStorage.setItem('address_chooseAddressActivity',null)
            }
        }
    }
</script>

<style lang="scss" scoped>
    .confirm{
        height: 50px;
        position: fixed;
        margin-top: auto;
        bottom: 0;
        width: 100%;
    }
</style>