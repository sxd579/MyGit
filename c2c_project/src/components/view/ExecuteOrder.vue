<template>

    <div @load="onLoad" class="main">
        <!--        标题栏-->
        <my-nav-bar m-title="任务详情" id="myNavBar"/>


        <!--      内容部分  固定大小，并在里面添加滚动条-->
        <div :style="mainView">
            <!--            loading显示-->
            <van-loading  v-if="!loaded" size="50px" color="#1989fa" >加载中...</van-loading>
            <!--            loaded显示-->
            <div v-if="loaded" style="width: 100%">
                <van-cell-group class="card">
                    <van-cell title="任务标题" :value="task_name"></van-cell>
                    <van-cell title="任务薪酬" :value="budget"></van-cell>
                    <van-cell title="截止时间" :value="end_time"></van-cell>
                    <van-cell title="任务地点" :label="location"></van-cell>
                    <van-cell title="任务标签">
                        <van-tag v-for="tag in tags" :key="tag.index" plain type="success">{{tag}}</van-tag>
                    </van-cell>
                </van-cell-group>

                <!--<div class="task_top">-->
                    <!--<div class="task_top_left">-->
                        <!--<span >-->
                        <!--<van-image class="head_portrait" :src="img" round></van-image>-->
                        <!--</span>-->
                    <!--</div>-->
                    <!--<div class="task_top_right">-->
                        <!--<span>任务:{{task_name}}</span>-->
                        <!--<span>地点:{{location}}</span>-->
                        <!--<span>酬劳:{{budget}}</span>-->
                        <!--<span>截止时间:{{end_time}}</span>-->
                    <!--</div>-->
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
                        <van-cell :value="detail"></van-cell>
                    </van-cell-group>
                </div>
                <!--<div class="task_content">-->
                    <!--<span>任务内容:</span>-->
                    <!--<span >-->
                <!--<p class="content">{{detail}}</p>-->
            <!--</span>-->
                <!--</div>-->
                <!--<div class="task_content">-->
                <!--</div>-->
                <!--<van-divider-->
                        <!--:style="{ color: '#1989fa', borderColor: '#1989fa'}">-->
                <!--</van-divider>-->
                <div style="margin: 2.5%">
                    <van-field
                            v-model="text"
                            rows="3"
                            autosize
                            label="答复任务"
                            type="textarea"
                            maxlength="200"
                            placeholder="请输入回答内容"
                            show-word-limit
                            required
                    />
                    <van-uploader :after-read="after_read" max-count="1" class="upLoader" v-model="upFile" :disabled="task_flag">

                    </van-uploader>
                </div>
                <!--<div :style="padding"></div>-->
                <van-button class="confirmButton" block @click="submit_answer">提交任务</van-button>
        </div>
        <!--<van-submit-bar id="take" style="position: fixed" button-text="提交任务"   @click="onSubmit" />-->
    </div>
    </div>

</template>
<script>
    import Vue from 'vue';
    import {NavBar,Divider,SubmitBar,Loading, Toast} from 'vant';
    import MyNavBar from "@/components/view/reusable/MyNavBar";
    Vue.use(NavBar).use(Divider).use(SubmitBar).use(Loading).use(Toast);
    export default {
        name: "TaskDetails",
        components: {MyNavBar},
        data(){
            return{
                currentRate: 0,
                mainView:{},
                padding:{},
                gradientColor: {
                    '0%': '#3fecff',
                    '100%': '#6149f6',
                },
                loaded :false,
                img:'',
                cost:'',
                content:'',
                text:'',
                requires:[],
                code: '',
                budget: '',
                check_state: '',
                creatid: '',
                demand_num: '',
                detail: '',
                lat: '',
                lon: '',
                overview: '',
                release_time: '',
                end_time: '',
                location: '',
                target_num: '',
                task_id: this.$route.params.task_id,
                task_name: '',
                task_type: '',
                time_limit: '',
                items: [],
                tags:'',
                task_img:'',
                upFile:[],
                task_flag:''
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
            submit_answer() {
                if(this.task_flag === 0){
                    this.axios.post("/api/task/answer/submitting",{
                            task_id: this.task_id,
                            ans_body: this.text},
                        {
                            headers:{
                                token:this.$store.state.Authorization
                            },
                            params:{
                                task_id: this.task_id
                            }
                        }).then((res)=>{
                            if(res.data.code === 200){
                                Toast("提交成功！")
                                this.$router.push({
                                    name:'home'
                                })
                            }else {
                                alert(res.data.msg);
                            }
                    }).catch((error)=>{
                        alert(error)
                    })
                }else {
                    this.axios.post("/api/task/answer/submitting",{
                        task_id: this.task_id,
                        ans_body: this.text,
                        img: this.task_img
                        },
                        {
                            headers:{
                                token:this.$store.state.Authorization
                            },
                            params:{
                                task_id: this.task_id
                            }
                        }).then((res)=>{
                        if(res.data.code === 200){
                            Toast("提交成功！")
                            this.$router.push({
                                name:'order'
                            })
                        }else {
                            alert(res.data.msg);
                        }
                    }).catch((error)=>{
                        alert(error)
                    })
                }

            },
            after_read(file) {
                    // 此时可以自行将文件上传至服务器
                    console.log(file);
                    const formData = new FormData();  // 声明一个FormData对象
                    formData.append("image-file", file.file);
                    this.axios.put("/api/file", formData).then((res)=>{
                        if(res.data.code === 200){
                            this.task_img = res.data.data;
                            Toast("上传成功！")
                        }else {
                            alert(res.data.msg)
                        }
                        }).catch((error)=>{
                        alert(error)
                    });
                },
            // onSubmit(){
            //     this.axios.post("/api/task/answer/submitting/?task_id",{
            //         task_id: this.task_id,
            //         ans_body:this.text,
            //         // detail:this.
            //     }).then((res)=>{
            //         alert("succeed")
            //         alert(res.data.msg)
            //     }).catch((error)=>{
            //         alert("fail")
            //         alert(error)
            //     })
            //     //接受任务
            // },


        },
        created(){
            document.documentElement.style.fontSize = document.documentElement.clientWidth / 360*16 + 'px';
            this.axios.get("/api/task/" + this.task_id, {}).then((res)=>{
                console.log(res.data.data);
                this.items=res.data.data[0];
                this.task_id=this.items.task_id;
                this.task_name=this.items.task_name;
                this.location=this.items.location;
                this.budget=this.items.budget;
                this.end_time=this.items.end_time;
                this.detail=this.items.detail;
                this.tags=eval(this.items.task_tag);
                this.task_flag=this.items.flag === 0;
            }).catch(err=>{
                console.log(err);
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
                    -document.getElementById('myNavBar').clientHeight
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
    }
    .task_top_right{
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        padding: 1.25rem;
    }
    .task_top_right>span{
        margin: 10px 20px;
    }


    .task_content{
        margin: 0rem 1.25rem;
    }
    .submit{
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        line-height: 5rem;
        padding: 2rem;
    }
    .content{
        text-indent: 2em;
    }
    .head_portrait{
        width: 5rem;
        height: 5rem;
        padding: 3.125rem 0.625rem 0.625rem 1.25rem;
    }
    .count{
        padding: 1.25rem 0.625rem 0.625rem 2.25rem;
    }
    .main{
        display: flex;
        flex-direction: column;
        background-color: white;
    }

    .card{
        margin: 2.5%;
        box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
        transition: 0.3s;
        width: 95%;
        border-radius: 15px;
    }

    .confirmButton{
        color: #51b0f6;
        font-size: 1rem;
        position: fixed;
        bottom:0;
        width: 100%;
        height: 4rem;
    }

    .upLoader{
        margin: 2.5%;
    }
</style>