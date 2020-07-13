<template>
    <div>
        <van-nav-bar title="订单"/>
        <van-tabs v-model="activeName" sticky animated swipeable>

            <van-tab title="已接订单" name="first">
                <van-pull-refresh success-text="刷新成功" v-model="refreshing1" @refresh="onRefresh1">
                <van-list
                        v-model="loading1"
                        :finished="finished1"
                        finished-text="没有更多了"
                >
                    <van-cell v-for="item in items1" :key="item" :title="item.task_name" :label="item.task_tag"
                              :value="item.budget" is-link @click="to_execute(item.task_id)"
                    />
                </van-list>
                </van-pull-refresh>
            </van-tab>
            <van-tab title="已发布订单" name="second">
                <van-pull-refresh success-text="刷新成功" v-model="refreshing2" @refresh="onRefresh2">
                <van-list
                        v-model="loading2"
                        :finished="finished2"
                        finished-text="没有更多了"
                >
                    <van-cell v-for="item in items2" :key="item" :title="item.task_name" :value="checkState(item.check_state)" is-link @click="to_waiting(item.task_id)"/>
                </van-list>
                </van-pull-refresh>
            </van-tab>
            <van-tab title="待评价" name="third">
                <van-pull-refresh success-text="刷新成功" v-model="refreshing3" @refresh="onRefresh3">
                <van-list
                        v-model="loading3"
                        :finished="finished3"
                        finished-text="没有更多了"
                >
                    <van-cell v-for="item in items3" :key="item" :title="item.task_name" :value="item.check_state" is-link @click="to_evaluate(item.task_id)"/>
                </van-list>
                </van-pull-refresh>
            </van-tab>
            </van-tabs>
    </div>
</template>

<script>
    export default {
        name: "OrderActivity",
        data() {
            return {
                activeName: 'first',
                finished1:false,
                loading1:false,
                refreshing1:false,
                finished2:false,
                loading2:false,
                refreshing2:false,
                finished3:false,
                loading3:false,
                refreshing3:false,
                items1: [],
                items2: [],
                items3: [],
                items4: [],
                code: '',
                budget: '',
                check_state: '',
                task_state: '',
                state: 'ss',
                creatid: '',
                demand_num: '',
                detail: '',
                lat: '',
                lon: '',
                overview: '',
                release_time: '',
                target_num: '',
                task_id: '',
                task_name: '',
                task_type: '',
                time_limit: '',
            };
        },
        methods: {
            onLoad(){
                setTimeout(() => {

                    location.reload()
                }, 1000)
            },
            show(){
                this.$router.push({
                    name:'finished_order',
                });
            },
            to_execute(task_id){
                this.$router.push({name:'execute_order',params:{task_id}})

        },
            to_waiting(task_id){
                this.$router.push({name:'wait_order',params:{task_id}})
            },
            to_evaluate(task_id){
                this.$router.push({name:'wait_evaluate',params:{task_id}})
            },
            checkState(check_state){
                if (check_state == 0){
                    this.state = "待审核";
                }
                if (check_state == 1){
                    this.state = "审核通过";
                }
                if (check_state == 2){
                    this.state = "审核未通过";
                }
                return this.state
    },
            onLoad1(){
                this.axios.get("/api/user/task/executing",{headers:{
                        token:this.$store.state.Authorization
                    }}).then((res)=>{
                        this.refreshing1=false
                    this.items1=res.data.data
                    this.loading1=false
                    this.finished1=true
                }).catch((error)=>{
                    alert(error)
                    this.refreshing1=false
                    this.loading1=false
                    this.finished1=true
                });
            },
            onLoad2(){
                this.axios.get("/api/user/task/executed"   ,{headers:{
                        token:this.$store.state.Authorization
                    }}).then((res)=>{
                        this.refreshing2=false
                    this.items2=res.data.data;
                    for (var i=0,n=0;i<this.items2.length;i++){
                        if(this.items2[i].task_state===2){
                            this.items3[n]=this.items2[i];
                            n++
                        }
                    }
                    this.loading2=false
                    this.finished2=true
                    this.refreshing3=false
                    this.loading3=false
                    this.finished3=true
                    this.axios.get("/api/user/task/checking",{headers:{
                            token:this.$store.state.Authorization
                        }}).then((res)=>{
                        this.items4=res.data.data
                        for(let i=0;i<this.items4.length;i++){
                            let t={
                                task_id:this.items4[i].task_id,
                                task_state: this.items4[i].task_state,
                                task_name: this.items4[i].task_name
                            }
                            this.items2.push(t)
                        }
                    }).catch((error)=>{
                        alert(error)
                    });
                }).catch((error)=>{
                    alert(error)
                    this.refreshing2=false
                    this.loading2=false
                    this.finished2=true
                    this.refreshing3=false
                    this.loading3=false
                    this.finished3=true
                })
            },
            onLoad3(){
                this.onLoad2()
            },
            onRefresh1(){
                this.finished1=false
                this.loading1=true
                this.loading1=true
                this.onLoad1()
            },
            onRefresh2(){
                this.finished2=false
                this.loading2=true
                this.onLoad2()
            },
            onRefresh3(){
                this.finished3=false
                this.loading3=true
                this.onLoad3()
            }
        },
        mounted(){
            this.loading=false;
        },
        created(){
            this.onRefresh1();
            this.onRefresh2();
        }
    }

</script>

<style scoped>
    .order {
        display: flex;
        align-items: center;
        border: 1px ridge #313030;
        border-radius: 20px;
        background-color: pink;
    }

    .head {
        width: 40%;
    }

    .content {
        width: 55%;
    }


    .small_title {
        height: 10px;
        font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
        font-size: 15px;
        font-weight: bold;
    }

    .tags {
        margin: 1px 5px;
        background-color: red;
        color: #f0eff5;
        font-size: 12px;
        border: 1px solid #313030;
        border-radius: 5px;
    }
</style>