<template>
    <div>
        <my-nav-bar m-title="选择地址"/>
        <div class="amap-wrapper">
            <el-amap class="amap-box" :vid="amap-vue">
                <el-amap-marker
                        v-if="mLat==null"
                        vid="component-marker"
                        draggable="true"
                        :events="markerEvents"
                />
                <el-amap-marker
                        v-if="mLat!=null"
                        vid="component-marker"
                        draggable="true"
                        :position="[mLng,mLat]"
                        :events="markerEvents"
                />
            </el-amap>
        </div>
        <van-button class="confirm" @click="onConfirm" color="#ffa92b" block>确认选择</van-button>
    </div>
</template>

<script>
    import MyNavBar from "@/components/view/reusable/MyNavBar";
    export default {
        name: "ChooseAddressActivity",
        components: {MyNavBar},
        data(){
            return{
                mPosition:[100.1,50.1],
                haveChosen:false,
                markerEvents:{
                  dragend :(e)=>{
                      this.mPosition=[e.lnglat.lng,e.lnglat.lat]
                      this.haveChosen=true
                  }
                },
                mLat:null,
                mLng:null
            }
        },
        mounted(){
            var lat=this.$route.params.lat
            var lng=this.$route.params.lng
            if(lat != null&&lat!='null'){
                this.mLat=lat
                this.mLng=lng
                this.markerEvents.dragend({lnglat:{lng:this.mLng,lat:this.mLat}})
            }
        },
        methods:{
            onConfirm(){
                if(this.haveChosen){
                    this.$axios.get("https://restapi.amap.com/v3/geocode/regeo",{
                        params:{
                            key:'b95efc48bf77746a3f62c063177815b1',
                            location:""+this.mPosition[0]+","+this.mPosition[1]
                        }
                    }).then(res=>{
                        var data=res.data
                        if(data.status==0){
                            alert(data.info)
                            return
                        }
                        localStorage.setItem('address_chooseAddressActivity',data.regeocode.formatted_address)
                        localStorage.setItem('lng_chooseAddressActivity',this.mPosition[0])
                        localStorage.setItem('lat_chooseAddressActivity',this.mPosition[1])
                        this.$router.go(-1);
                    }).catch(error=>{
                        console.log(error)
                        alert("逆地理编码失败！请检查您的网络连接后重试！")
                    })
                }
                else{
                    alert("请拖拽地图中的点标记以选择地址！")
                }
            }
        },
    }
</script>

<style lang="scss" scoped>
    .amap-wrapper{
        height: 100%;
        position: fixed;
        margin-top: 50px;
        bottom: 50px;
        width: 100%;
    }
    .confirm{
        height: 50px;
        position: fixed;
        margin-top: auto;
        bottom: 0;
        width: 100%;
    }
</style>