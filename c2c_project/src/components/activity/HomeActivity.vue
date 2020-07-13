<template>


    <div class="main">
        <!--标题-->
        <van-nav-bar
                title="C2C众包项目"
                id="navBar"
        />
        <!--        搜索栏-->
        <form action="/">
            <van-search
                    id="search"
                    v-model="value"
                    show-action
                    placeholder="请输入搜索关键词"
                    @search="onSearch"
                    @cancel="onCancel"
            ></van-search>
        </form>
        <!--内容-->
        <div :style="mainView">
            <!--            加载时-->
            <van-loading v-if="loading" style="margin: 50% 40% " size="3rem" color="#1989fa">加载中...</van-loading>
            <!--            加载完毕-->
            <div v-if="!loading">
                <div>
                    <!--                    轮播图-->
                    <van-swipe :autoplay="3000" @change="onChange">
                        <van-swipe-item v-for="(image, index) in images" :key="index">
                            <lazy-component>
                            <img v-lazy="image" width="100%" height="280px"/>
                            </lazy-component>
                        </van-swipe-item>
                    </van-swipe>
                </div>
                <!--                    任务列表-->
                <!--                为空-->
                <div v-if="isEmpty" style="display: flex;align-items: center;margin: 50% 30%">
                    没有发现相关任务
                </div>
                <!--                不为空-->
                <keep-alive>
                    <div v-if="!isEmpty">
                        <van-list
                                finished-text="没有更多了"
                                v-for="item in items"
                                :key="item.index"
                                style="align-self: auto"
                        >
                            <div class="task">
                                <!--                            任务栏左-->
                                <div class="taskLeft">

                                    <!--                                头像-->
                                    <div class="taskHead">
                                        <van-image class="head_portrait" :src="item.head_portrait" round></van-image>
                                    </div>
                                    <div class="taskContent">
                                        <div class="taskContentTitle">
                                            {{item.title}}
                                        </div>
                                        <div>
                                            <van-tag color="red" v-for="tag in item.tags" :key="tag.index"
                                                     style="font-size: 15px;margin: 5px 5px 5px 5px;">{{tag}}
                                            </van-tag>
                                        </div>
                                        <div style="font-size: 12px;color: grey;">
                                            目标距离您{{item.distance}}Km
                                        </div>


                                    </div>
                                </div>
                                <!--                            任务栏右-->
                                <div class="taskRight">
                            <span>
                        <van-button round type="info" @click="show" style="align-self: flex-end;width: 100px;"
                                    :name="item.id">查看详情</van-button>
                    </span>
                                </div>
                                </div>
                        </van-list>
                    </div>
                </keep-alive>
            </div>
            <div :style="padding"></div>
        </div>
    </div>


</template>
<script type="text/javascript">
    import Vue from 'vue';
    import {
        List,
        Button,
        Search,
        Toast,
        Swipe,
        SwipeItem,
        Lazyload,
        Skeleton,
        Image as VanImage,
        NavBar,
        Loading,
        Tag
    } from "vant";


    Vue.use(List).use(Search).use(Toast).use(Swipe).use(SwipeItem).use(Lazyload, {
        lazyComponent: true,
    }).use(Skeleton).use(VanImage).use(Button)
        .use(NavBar).use(Loading).use(Tag);
    export default {

        name: "HomeActivity",
        data() {
            return {
                lat: '',
                lng: '',
                value: '',
                images: [
                    require('@/assets/drawable/img0.png'),
                    require('@/assets/drawable/img1.png'),
                    require('@/assets/drawable/img2.png'),
                    require('@/assets/drawable/img3.png'),
                    require('@/assets/drawable/img4.png'),
                    require('@/assets/drawable/img5.png')
                ],
                loading: true,
                padding: {},
                mainView: {},
                items: [],
                isEmpty: '',
                head_portrait: '',

            };
        },
        methods: {
            onSearch(val) {
                // 想服务器发送请求，更新items列表
                this.axios.get("/api/search", {
                    params: {
                        keyword: val
                    },
                    headers: {
                        token: this.$store.state.Authorization,
                    }

                }).then(res => {
                    console.log(res);
                    this.isEmpty = true;
                    this.items = [];
                    for (let i = 0; i < res.data.data.length; i++) {
                        console.log(res);
                        // console.log(res.data.data[i].task_tag);
                        this.axios.post("/api/user/getidhead", {
                            "user_id": "" + res.data.data[i].creatid
                        }).then((res_serch) => {
                            // alert("开始搜索成功");

                            // console.log(this);
                            this.items.push(
                                {
                                    id: res.data.data[i].task_id,
                                    head_portrait: res_serch.data.data.headportrait,
                                    title: res.data.data[i].task_name,
                                    tags: eval(res.data.data[i].task_tag),
                                    finished: false,
                                },
                            )
                            this.isEmpty = false;

                        });
                    }
                }).catch((error) => {
                    console.log(error);
                });
            },
            onCancel() {
                this.items = [];
                this.axios.get("/api/task/list", {
                    headers: {
                        token: this.$store.state.Authorization
                    },
                }).then((res) => {
                    // console.log(res);
                    this.isEmpty = true;
                    for (let i = 0; i < res.data.data.length; i++) {
                        console.log(res);
                        // console.log(res.data.data[i].task_tag);
                        this.axios.post("/api/user/getidhead", {
                            "user_id": "" + res.data.data[i].creatid
                        }).then((res_serch) => {
                            // alert("开始搜索成功");

                            // console.log(this);
                            this.items.push(
                                {
                                    id: res.data.data[i].task_id,
                                    head_portrait: res_serch.data.data.headportrait,
                                    title: res.data.data[i].task_name,
                                    tags: eval(res.data.data[i].task_tag),
                                    finished: false,
                                },
                            )
                            this.isEmpty = false;

                        });
                        // console.log(this);

                        console.log(this.items);
                        console.log(this.items.length);

                    }


                }).catch((error) => {
                    console.log(error);
                    this.items = []
                });
                console.log(this.items);
                // loading 结束
                this.loading = false;
            },
            onChange() {
            },

            show(event) {
                console.log("跳转任务 id:" + event.currentTarget.name)
                this.$router.push({
                    name: 'task_detail',
                    params: {
                        id: event.currentTarget.name,
                        cache: true,
                    }
                });

            },


        },
        created() {
            console.log("loaded location....");
            console.log(this.lng);
            console.log(this.lat);
        },
        activated() {
            document.documentElement.style.fontSize = document.documentElement.clientWidth / 360 * 16 + 'px';
            //请求数据
            this.axios.get("/api/task/distance", {
                headers: {
                    token: this.$store.state.Authorization
                },
                params: {
                    lon: 115,
                    lat: 36,
                },
            }).then((res) => {
                // console.log(res);
                this.isEmpty = true;
                this.items = [];
                for (let i = 0; i < res.data.data.length; i++) {
                    console.log(res);
                    // console.log(res.data.data[i].task_tag);
                    this.axios.post("/api/user/getidhead", {
                        "user_id": "" + res.data.data[i].creatid
                    }).then((res_serch) => {
                        // alert("开始搜索成功");

                        // console.log(this);
                        this.items.push(
                            {
                                id: res.data.data[i].task_id,
                                head_portrait: res_serch.data.data.headportrait,
                                title: res.data.data[i].task_name,
                                tags: eval(res.data.data[i].task_tag),
                                finished: false,
                                distance: Math.floor(res.data.data[i].distance * 100) / 100,
                            },
                        )
                        this.isEmpty = false;

                    });
                    // console.log(this);

                    console.log(this.items);
                    console.log(this.items.length);

                }


            }).catch((error) => {
                console.log(error);
                this.items = []
            });
            console.log(this.items);
            // loading 结束
            this.loading = false;
        },
        mounted() {
            console.log("Mounted执行");
            this.padding = {height: document.getElementById('mainTabbar').clientHeight + 'px',};
            this.mainView = {
                height: document.documentElement.clientHeight
                    - document.getElementById('mainTabbar').clientHeight
                    - document.getElementById('search').clientHeight
                    - document.getElementById('navBar').clientHeight - 1
                    + 'px',
                overflow: 'scroll',
                display: 'flex',
                flexDirection: 'column',
            };
        },

    }
</script>

<style scoped>
    .main {
        display: flex;
        flex-direction: column;
    }

    .task {
        display: flex;
        align-items: center;
        justify-content: space-between;
        border: 1px solid #313030;
        border-radius: 1.25rem;
        background-color: white;
    }

    .taskLeft {
        display: flex;
        align-items: center;
    }

    .taskHead {
        margin-right: 20px;
    }

    .head_portrait {
        width: 3.125rem;
        height: 3.125rem;
        margin: 1.25rem;
        box-sizing: border-box;
    }


    .taskContent {
        display: flex;
        flex-direction: column;
    }

    .taskContent > div:nth-child(1) {
        font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
        font-size: 0.9375rem;
        font-weight: bold;
    }

    .tags {
        margin: 0.0625rem 0.3125rem;
        background-color: red;
        color: #f0eff5;
        font-size: 0.75rem;
        border: 1px solid #313030;
        border-radius: 0.3125rem;
    }
</style>