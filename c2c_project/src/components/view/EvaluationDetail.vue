<template>
    <div @load="onLoad" class="main">
        <van-nav-bar title="评价详情" left-text="返回" right-text="提交" @click-left="onClickLeft" @click-right="onClickRight" id="malabar"/>
        <van-panel title="答案">
            <div class="content">{{ans_body}}</div>
        </van-panel>
        <van-panel title="您对执行者满意吗？" status="待评价">
            <div class="task_top_left">
                <van-row>
                    <van-col>
                        <van-image class="head_portrait" :src="this.head_portrait" round></van-image>
                    </van-col>
                    <van-col>
                        <van-row>
                            {{task_name}}
                        </van-row>
                        <van-row>
                            {{user_name}}
                        </van-row>
                    </van-col>
                </van-row>
            </div>
            <div>
                <van-row type="flex" justify="center">
                    <van-col span="6"><van-button>满意</van-button></van-col>
                    <van-col span="6"><van-button>不满意</van-button></van-col>
                </van-row>
            </div>
            <div class="rate">
                <van-rate
                        v-model="value"
                        void-icon="star"
                        void-color="#eee"
                />
            </div>
            <div class="field">
                <van-field
                        v-model="message"
                        rows="7"
                        label="评价"
                        type="textarea"
                        maxlength="50"
                        placeholder="请输入评价"
                        show-word-limit
                />
            </div>
        </van-panel>
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
                head_portrait: '',
                organization:'',
                number: '',
                cost:'',
                content:'',
                navbar: '',
                value: 3,
                items: [],
                items1: [],
                answer_id: this.$route.params.answer_id,
                score: 5,
                task_id: this.$route.params.task_id,
                ans_body: '',
                task_name: '',
                user_id: '',
                user_name: '',
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
            onSubmit(){
                //接受任务
            },
            afterRead(file){
                console.log(file);
            },
            showPopup(){
                this.show = true;
            },
            onClickLeft(){
                this.$router.push({name:'wait_evaluate',params:{task_id:this.task_id}})
            },
            onClickRight(){
                this.axios.post("/api/task/answer/score", {
                    id:this.answer_id,
                    score:this.value
                    },
                    {
                        headers:{
                            token:this.$store.state.Authorization
                        }
                    }).then((res)=>{
                    alert(res.data.msg)
                }).catch(err=>{
                    console.log(err);
                    alert("fail")
                    alert(err)
                });
            },

        },
        created(){
            document.documentElement.style.fontSize = document.documentElement.clientWidth / 360*16 + 'px';
            this.axios.get("api/task/getUserAnswer",{
                params:{
                    task_id:this.task_id
                },headers:{
                    token:this.$store.state.Authorization
            }
            }).then((res)=>{
                this.items=res.data.data
                for(let i=0;i<this.items.length;i++){
                    if(this.answer_id==this.items[i].answer_id){
                        this.user_id=this.items[i].user_id
                        this.ans_body=this.items[i].ans_body
                        this.axios.post("/api/user/getidhead", {
                            user_id:this.user_id
                        },).then((res)=>{
                            this.head_portrait=res.data.data.headportrait
                        }).catch(err=>{
                            console.log(err);
                            alert("fail")
                            alert(err)
                        });
                    }
                }
            }).catch(err=>{
                console.log(err);
                alert("fail")
                alert(err)
            });
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
        margin-bottom: 0.1rem;
    }
    .task_top_right>span{
        margin: 10px 20px;
    }


    .rate{
        padding-bottom: 2rem;
        left: 50%;
        transform: translate(30%,50%);
    }
    .submit{
        position: relative;
        left: 70%;
        bottom: 5%;
    }
    .content{
        margin-left: 1rem;
    }
    .head_portrait{
        width: 3rem;
        height: 3rem;
        padding:  0.625rem 1.625rem 1.25rem;
    }

    .main{
        display: flex;
        flex-direction: column;
        line-height: 3rem;
    }
    .field{
        height: 15rem;
    }

</style>