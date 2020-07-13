<template>


    <div class="main">
        <!--        标题栏-->
        <my-nav-bar m-title="任务详情" id="myNavBar"/>


        <!--      内容部分  固定大小，并在里面添加滚动条-->
        <div :style="mainView">
            <!--            loading显示-->
            <van-loading v-if="!loaded" size="50px" color="#1989fa">加载中...</van-loading>
            <!--            loaded显示-->
            <div v-if="loaded" style="width: 100%">
                <van-cell-group class="card">
                    <van-cell title="任务标题" :value="title"></van-cell>
                    <van-cell title="需求人数" :value="person_num"></van-cell>
                    <van-cell title="任务薪酬" :value="cost"></van-cell>
                    <van-cell title="任务地点" :label="address"></van-cell>
                    <van-cell title="任务时间" :label="time"></van-cell>
                    <van-cell title="任务标签">
                        <van-tag v-for="tag in tags" :key="tag.index" plain type="success">{{tag}}</van-tag>
                    </van-cell>
                </van-cell-group>

                    <!--<div class="task_top_left">-->
                        <!--<van-image class="head_portrait" :src="img" round></van-image>-->
                    <!--</div>-->

                <!--&lt;!&ndash;            分割线&ndash;&gt;-->
                <!--<van-divider-->
                        <!--:style="{ color: '#1989fa', borderColor: '#1989fa' }"-->
                <!--&gt;-->
                <!--</van-divider>-->

                <!--                任务内容-->
                <div class="card">
                    <van-cell-group>
                        <van-cell title="任务内容" size="large"></van-cell>
                        <van-cell :value="content"></van-cell>
                    </van-cell-group>
                    <!--<span>任务内容:</span>-->
                    <!--<span>-->
                <!--<p class="content">{{content}}</p>-->
            <!--</span>-->
                </div>
                <!--                分割线-->
                <!--        <van-divider-->
                <!--                :style="{ color: '#1989fa', borderColor: '#1989fa' }"-->
                <!--        >-->
                <!--        </van-divider>-->
                <!--                任务要求-->
                <!--        <div class="task_content">-->
                <!--            <span>任务要求:</span>-->
                <!--            <span >-->
                <!--               <p v-for="(require,index) in requires" :key="index">{{index+1}}、 {{require}}</p>-->
                <!--            </span>-->
                <!--        </div>-->
                <div :style="padding"></div>
                <div class="button_block">
                <van-button  color="green" round plain type="info" id="take"  @click.native="takeIn" style="padding: 1rem; margin: 0.8rem; width: 40%">接受任务</van-button>
                <van-button  color="red" round plain type="info" id="refuse"  @click.native="refuse" style="padding: 1rem; margin: 0.8rem; width: 40%">拒绝任务</van-button>
<!--                <van-submit-bar id="refuse" style="position: fixed;right: 20px;" button-text="拒绝任务" @click.native="refuse"/>-->
                </div>
            </div>
        </div>

    </div>

</template>
<script>
    import Vue from 'vue';
    import {NavBar, Divider, SubmitBar, Loading, Toast, Cell, CellGroup} from 'vant';
    import MyNavBar from "@/components/view/reusable/MyNavBar";

    Vue.use(NavBar).use(Divider).use(SubmitBar).use(Loading).use(Toast).use(Cell).use(CellGroup);
    export default {
        name: "TaskDetails",
        components: {MyNavBar},
        data() {
            return {
                currentRate: 0,
                mainView: {},
                padding: {},
                gradientColor: {
                    '0%': '#3fecff',
                    '100%': '#6149f6',
                },
                loaded: false,
                title: '',
                img: '',
                person_num: '',
                address: '',
                time: '',
                cost: '',
                content: '',
                requires: [],
                tags:''
            };
        },
        methods: {
            back() {

                this.$router.go(-1);
            },
            takeIn() {
                //接受任务1
                // alert("Take in……" + parseInt(this.$route.params.id));

                this.axios.post("/api/user/task/accepting/", {
                    task_id: parseInt(this.$route.params.id)
                },{
                    headers:{
                        token:this.$store.state.Authorization
                    },
                    params:{
                        task_id:this.$route.params.id
                    }
                }).then(res => {
                    console.log(res);
                    console.log(res.data.msg)
                    Toast("接受任务返回信息:"+res.data.msg);
                    this.$router.push({
                        name:'home'
                    })
                }).catch(error => {
                    console.log(error);
                })
            },
            refuse(){
                this.axios.post("/api/user/task/rejecting/", {
                    task_id: parseInt(this.$route.params.id)
                },{
                    headers:{
                        token:this.$store.state.Authorization
                    },
                    params:{
                        task_id:this.$route.params.id
                    }
                }).then(res => {
                    console.log(res);
                    console.log(res.data.msg)
                    Toast("拒绝任务返回信息:"+res.data.msg);
                    this.$router.push({
                        name:'home'
                    })
                }).catch(error => {
                    console.log(error);
                })

            },

        },
        created() {
            document.documentElement.style.fontSize = document.documentElement.clientWidth / 360 * 16 + 'px';

            //获取该id的任务
            // 测试数据
            this.axios.get("/api/task/" + this.$route.params.id, {}).then(res => {
                // for (let key in res.data.data[0]) {
                //     console.log('key:' + key);
                //     console.log(res.data.data[0][key]);
                // }
                 console.log(res);
                this.title = res.data.data[0].task_name;
                this.img = 'https://img.yzcdn.cn/vant/cat.jpeg';
                this.person_num = res.data.data[0].demand_num;
                this.address = res.data.data[0].location;
                this.time = res.data.data[0].end_time + ' ~ ' + res.data.data[0].end_time;
                this.cost = Math.floor(res.data.data[0].budget / res.data.data[0].demand_num*100)/100;
                this.content = res.data.data[0].detail;
                this.tags = eval(res.data.data[0].task_tag);
            }).catch(error => {
                alert("failed" + error);

            })

            this.loaded = true;
        },
        mounted() {
            console.log("Mounted执行11");
            this.mainView = {
                height: document.documentElement.clientHeight
                    - document.getElementById('take').clientHeight
                    - document.getElementById('myNavBar').clientHeight
                    + 'px',
                overflow: 'scroll',
                display: 'flex',
            };
            this.padding = {
                height: document.getElementById('take').clientHeight + 'px',
            };

        },

    }
</script>

<style scoped>
    .task_top {
        display: flex;
    }

    .task_top_left {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .task_top_right {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        padding: 1.25rem;
    }

    .task_top_right > span {
        margin: 10px 20px;
    }


    .task_content {
        background-color: white;
        margin: 0rem 1.25rem;
    }

    .content {
        text-indent: 2em;
    }

    .head_portrait {
        width: 5rem;
        height: 5rem;
        padding: 3.125rem 0.625rem 0.625rem 1.25rem;
    }

    .main {
        background-color: white;
        display: flex;
        flex-direction: column;
    }

    .card{
        margin: 2.5%;
        box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
        transition: 0.3s;
        width: 95%;
        border-radius: 15px;
    }

    .button_block{
        position: fixed;
        bottom:0;
        width: 100%;
        height: 4rem;
        box-shadow: 0 -2px 8px 0 rgba(0,0,0,0.2);
        transition: 0.3s;
        border-radius: 15px 15px 0 0;
        text-align: center;
    }

</style>