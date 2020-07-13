<template>

    <div @load="onLoad" class="main">
        <van-nav-bar title="已发布任务" left-text="返回" @click-left="onClickLeft" id="malabar"/>
        <div :style="mainView">
            <!--            loading显示-->
            <div v-if="loaded" style="width: 100%">
                <van-cell-group class="card">
                    <van-cell title="任务标题" :value="task_name"></van-cell>
                    <van-cell title="任务薪酬" :value="budget"></van-cell>
                    <van-cell title="截止时间" :value="end_time"></van-cell>
                    <van-cell title="任务地点" :label="location"></van-cell>
                    <van-cell title="已参与人数" :value="attend_num"></van-cell>
                    <van-cell title="任务标签">
                        <van-tag v-for="tag in tags" :key="tag.index" plain type="success">{{tag}}</van-tag>
                    </van-cell>
                </van-cell-group>
                <div class="card">
                    <van-cell-group>
                        <van-cell title="任务内容" size="large"></van-cell>
                        <van-cell :value="detail"></van-cell>
                    </van-cell-group>
                </div>
            </div>
            <van-loading  v-if="!loaded" size="50px" color="#1989fa" >加载中...</van-loading>
            <!--            loaded显示-->
        </div>
        <!--<van-submit-bar id="take" style="position: fixed" button-text="支付"   @click="onSubmit" />-->
    </div>

</template>
<script>
    import Vue from 'vue';
    import {NavBar,Divider,SubmitBar,Loading} from 'vant';
    Vue.use(NavBar).use(Divider).use(SubmitBar).use(Loading);
    export default {
        name: "TaskDetails",
        data(){
            return{
                show: false,
                currentRate: 0,
                mainView:{},
                padding:{},
                gradientColor: {
                    '0%': '#3fecff',
                    '100%': '#6149f6',
                },
                loaded :false,
                title:'',
                img:'',
                organization:'',
                number: '',
                cost:'',
                content:'',
                navbar: '',
                requires:[],
                task_id: this.$route.params.task_id,
                task_name: '',
                location: '',
                budget: '',
                end_time: '',
                detail: '',
                attend_num: '',
            };
        },
        methods:{
            back(){
                this.$router.push({
                    name:'home'
                })
            },
            onLoad(){
                //请求数据


            },
            // onSubmit(){
            //     //接受任务
            // },
            afterRead(file){
                console.log(file);
            },
            showPopup(){
                this.show = true;
            },
            onClickLeft(){
                this.$router.go(-1);
            },


        },
        created(){
            document.documentElement.style.fontSize = document.documentElement.clientWidth / 360*16 + 'px';
            this.axios.get("/api/task/"+this.task_id,{}).then((res)=>{
                console.log(res.data.data)
                this.items=res.data.data
                for(var i=0;i<this.items.length;i++){
                    if(this.items[i].task_id==this.task_id){
                        this.task_name=this.items[i].task_name
                        this.location=this.items[i].location
                        this.budget=this.items[i].budget
                        this.end_time=this.items[i].end_time
                        this.detail=this.items[i].detail
                        this.attend_num=this.items[i].target_num-this.items[i].demand_num
                    }
                }
            }).catch(err=>{
                console.log(err);
                alert("fail")
                alert(err)
            });
            this.img ='https://img.yzcdn.cn/vant/cat.jpeg';
            this.loaded=true;
        },
        mounted(){
            console.log("Mounted执行");
            this.mainView = {
                height: document.documentElement.clientHeight
                    -document.getElementById('take').clientHeight
                    -document.getElementById('malabar').clientHeight
                        -document.getElementById('cell').clientHeight
                    +'px',
                overflow: 'scroll',
                display: 'flex',
            };
            this.padding={
                height:document.getElementById('take').clientHeight+'px',
            };

        },

    }
</script>

<style scoped>
    .task_top{
        display: flex;
    }
    .task_top_left{
        display: flex;
        flex-direction: column;
        justify-content: center;
        line-height: 2.0rem;
        padding: 1.25rem;
        margin-bottom: 0.1rem;
    }
    .task_top_right>span{
        margin: 10px 20px;
    }


    .task_content{
        margin: 0rem 1.25rem;
    }
    .submit{
        position: relative;
        left: 70%;
        bottom: 5%;
    }
    .content{
        text-indent: 2em;
    }
    .head_portrait{
        width: 5rem;
        height: 5rem;
        padding: 3.125rem 0.625rem 0.625rem 1.25rem;
    }

    .main{
        display: flex;
        flex-direction: column;
    }

</style>