<template>
    <div @load="onLoad" class="main">
        <van-nav-bar title="评价" left-text="返回" right-text="提交" @click-left="onClickLeft" @click-right="onClickRight" id="malabar"/>
            <van-list
                    v-model="loading"
                    :finished="finished"
                    finished-text="没有更多了"
            >
                <van-cell v-for="item in items1" :key="item" :title="item.answer_id" is-link @click="to_evaluation(item.answer_id)"
                />
            </van-list>
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
                headportrait: '',
                organization:'',
                number: '',
                cost:'',
                content:'',
                navbar: '',
                value: 2.5,
                items: [],
                items1: [],
                items2: [],
                answer_id: '',
                score: 5,
                task_id: this.$route.params.task_id,
                ans_body: '',
                task_name: '',
                user_id: '',
                id: [],
                user_name: '',
                finished: true,
                loading: false,
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
                this.$router.push({name:'order',params:{task_id:this.task_id}})
            },
            onClickRight(){
                this.axios.post(
                    "/api/task/answer/score",{
                        id:1,
                        score:2
                    },{
                        headers:{
                            token:this.$store.state.Authorization
                        }
                    }
                )
                    .then((res)=>{
                        alert("succeed")
                        alert(res.data.msg)
                    }).catch((error)=>{
                    alert("fail")
                    alert(error)
                });
            },
            to_evaluation(answer_id) {
                this.$router.push({name:'evaluation_detail',params:{answer_id,task_id:this.task_id}})
            }

        },
        created(){
            document.documentElement.style.fontSize = document.documentElement.clientWidth / 360*16 + 'px';
            this.axios.get("/api/task/getscoreduserid",{
                params:{
                    task_id:this.task_id
                },
                headers:{
                    token:this.$store.state.Authorization
                }
            }).then((res)=>{
                this.items2=res.data.data
                for(let i=0;i<this.items2.length;i++){
                    this.id.push(res.data.data[i].user_id)
                }
            }).catch(err=>{
                console.log(err);
                alert("fail")
                alert(err)
            });
            this.axios.get("api/task/getUserAnswer",{
                params:{
                    task_id:this.task_id
                },
                headers:{
                    token:this.$store.state.Authorization
                }
            }).then((res)=>{
                this.items=res.data.data
                for(let i=0;i<this.items.length;i++){
                    if(this.id.length==0){
                        let m={
                            answer_id:this.items[i].answer_id,
                        }
                        this.items1.push(m)
                    }
                    for(let j=0;j<this.id.length;j++){
                        if(this.items[i].user_id!=this.id[j]){
                            let t={
                                answer_id:this.items[i].answer_id,
                            }
                            this.items1.push(t)
                        }
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