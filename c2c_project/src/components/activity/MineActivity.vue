<template>
    <div style="background-color: white">
        <van-nav-bar title="我的"/>
        <van-row  style="background-color: white; margin-top: 1px" @click="goPersonalInfo">
            <van-col span="9" align="center" >
                <van-image
                        style="margin-top: 0.5rem; margin-bottom: 0.5rem"
                        round
                        width="5rem"
                        height="5rem"
                        fit="cover"
                        :src="headPath"
                ></van-image>
            </van-col>
            <van-col span="15">
                <span class="userName">{{username}}</span>
            </van-col>
        </van-row>
        <van-cell-group class="card">
            <van-cell title="历史任务" is-link @click="goHistoricalTask('first')"/>
            <van-grid clickable :column-num="3" icon-size="24px">
                <van-grid-item icon="passed" text="已完成" @click="goHistoricalTask('first')" />
                <van-grid-item icon="thumb-circle-o" text="被完成" @click="goHistoricalTask('second')" />
                <van-grid-item icon="close" text="已拒绝" @click="goHistoricalTask('third')" />
            </van-grid>
        </van-cell-group>

    </div>
</template>

<script>
    import Vue from 'vue';
    import { NavBar, Col, Row, Image as VanImage, Cell, CellGroup, Grid, GridItem } from 'vant';
    Vue.use(NavBar).use(Col).use(Row).use(VanImage).use(Cell).use(CellGroup).use(Grid).use(GridItem);
    export default {
        name: "MineActivity",
        data() {
            return{
                headPath:'',
                username:''
            }
        },
        created(){
            // alert(this.$store.state.Authorization)
          this.axios.get("/api/user/personal", {
              headers:{
                  token:this.$store.state.Authorization,
              }
          }).then((res) => {
              // console.log(res.data.data.name);
              this.username = localStorage.getItem("Flag")?res.data.data.user_name:'未登录'
          }).catch((error) => {
              alert(error)
          });
            this.axios.get("/api/user/headportrait", {
                headers:{
                    token:this.$store.state.Authorization
                }
            }).then((res1)=>{
                this.headPath = localStorage.getItem("Flag")?res1.data.data.headportrait:'https://img.yzcdn.cn/vant/cat.jpeg'
            }).catch((error1)=>{
                alert(error1)
            })
        },
        methods:{
            goPersonalInfo(){
                this.$router.push({
                    name:'personal_info'
                })
            },
            goHistoricalTask(a){
                this.$router.push({
                    name:'historical_task',
                    params:{act:a}
                })
            },
            // goLogin(){
            //     this.$router.push({
            //         name:'login'
            //     })
            // }
        }
    }
</script>

<style scoped>
    .userName{
        color:black;
        font-size: 30px;
        line-height: 6.5rem;
    }
    .card{
        margin: 1.5%;
        box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
        transition: 0.3s;
        width: 97%;
        border-radius: 15px;
    }

</style>