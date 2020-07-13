<template>
    <div>
        <my-nav-bar m-title="历史任务"/>
        <van-tabs v-model="activeName" sticky animated swipeable>
            <van-tab title="已完成" name="first">
                <van-list
                        v-model="loading1"
                        :finished="finished1"
                        finished-text="没有更多了"
                        @load="onLoad1"
                >
                    <van-cell
                            v-for="item in list1"
                            :key="item.index"
                            :title="item.task_name"
                            :label="item.detail"
                            size="large"
                    />
                </van-list>
            </van-tab>
            <van-tab title="被完成" name="second">
                <van-list
                        v-model="loading2"
                        :finished="finished2"
                        finished-text="没有更多了"
                        @load="onLoad2"
                >
                    <van-cell
                            v-for="item in list2"
                            :key="item.index"
                            :title="item.task_name"
                            :label="item.detail"
                            size="large"
                    />
                </van-list>
            </van-tab>
            <van-tab title="已拒绝" name="third">
                <van-list
                        v-model="loading3"
                        :finished="finished3"
                        finished-text="没有更多了"
                        @load="onLoad3"
                >
                    <van-cell
                            v-for="item in list3"
                            :key="item.index"
                            :title="item.task_name"
                            :label="item.detail"
                            size="large"
                    />
                </van-list>
            </van-tab>
        </van-tabs>
    </div>
</template>

<script>
    import Vue from 'vue';
    import { Tab, Tabs, List, Cell, CellGroup } from 'vant';
    import MyNavBar from "@/components/view/reusable/MyNavBar";
    Vue.use(Tab).use(Tabs).use(List).use(Cell).use(CellGroup);
    export default {
        name: "HistoricalTask",
        components: {MyNavBar},
        mounted(){
            let activeTab = this.$route.params.act;
            this.activeName  = activeTab;
        },
        data() {
            return{
                activeName: 'first',
                list1: [],
                list2: [],
                list3: [],
                loading1: false,
                loading2: false,
                loading3: false,
                finished1: false,
                finished2: false,
                finished3: false
            }
        },
        methods:{
            onLoad1(){
                this.axios.get("/api/user/task/completed",{
                    headers:{
                        token:this.$store.state.Authorization
                    }
                }).then((res)=>{
                    console.log(res.data.data)
                    var json = res.data;
                    if(json.code === 200){
                        for (var i = 0;i<json.data.length;i++){
                            json.data[i].task_name = json.data[i].task_name + ' - 任务id:' + json.data[i].task_id;
                        }
                        this.list1 = json.data;
                        this.finished1 = true;
                    }else {
                        alert(json.msg)
                    }
                }).catch((error)=>{
                    alert(error)
                });
            },
            onLoad2(){
                this.axios.get("/api/user/task/end",{
                    headers:{
                        token:this.$store.state.Authorization
                    }
                }).then((res)=>{
                    console.log(res.data.data)
                    var json = res.data;
                    if(json.code === 200){
                        for (var i = 0;i<json.data.length;i++){
                            json.data[i].task_name = json.data[i].task_name + ' - 任务id:' + json.data[i].task_id;
                        }
                        this.list2 = json.data;
                        this.finished2 = true;
                    }else {
                        alert(json.msg)
                    }
                }).catch((error)=>{
                    alert(error)
                });
            },
            onLoad3(){
                this.axios.get("/api/task/rejected",{
                    headers:{
                        token:this.$store.state.Authorization
                    }
                }).then((res)=>{
                    console.log(res.data.data)
                    var json = res.data;
                    if(json.code === 200){
                        for (var i = 0;i<json.data.length;i++){
                            json.data[i].task_name = json.data[i].task_name + ' - 任务id:' + json.data[i].task_id;
                        }
                        this.list3 = json.data;
                        this.finished3 = true;
                    }else {
                        alert(json.msg)
                    }
                }).catch((error)=>{
                    alert(error)
                });
            }
        }
    }
</script>

<style scoped>

</style>