<template>
    <div>
        <div class="amap-page-container">
            <el-amap ref="map" vid="amap" :amap-manager="amapManager" :center="center" :zoom="zoom" :plugin="plugin"
                     :events="events" :style="amap">
                <el-amap-marker v-for="(marker,index) in markers"
                                :vid="marker.id"
                                :position="marker.position"
                                :events="marker.events"
                                :animation="marker.animation"
                                :key="index"
                                :name="index"
                >
                </el-amap-marker>
            </el-amap>
        </div>
        <div v-if="showTaskList" :style="taskList">

            <!--                    任务列表-->
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
                            <div >
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
                        <van-button round type="info" @click="show" style="align-self: flex-end"
                                    :name="item.id">查看详情</van-button>
                    </span>
                    </div>

                </div>
            </van-list>


        </div>
    </div>
</template>
<script type="text/javascript"
        src="https://webapi.amap.com/maps?v=1.4.15&key=a9594943c05b599c149817c8b6988053">
</script>
<script type="text/javascript">
    import * as VueAMap from "vue-amap";
    // const exampleComponents = {
    //     props: ['text'],
    //     template: `<div>text from  parent: {{text}}</div>`
    // }
    let amapManager = new VueAMap.AMapManager();
    export default {
        name: "NearbyActivity",
        data() {
            let self = this;
            return {
                //Map 和Marker的属性
                amapManager,
                zoom: 15,   //地图缩放
                center: [115, 34.76],   //高德顶图中心点
                lat: '',
                lng: '',
                amap: {},
                markerNow: {},
                events: {
                    init: (o) => {
                        console.log(o.getCenter())
                        console.log(this.$refs.map.$$getInstance())
                        o.getCity(result => {
                            console.log(result)
                        })
                    },
                    /*
                    * 点击事件
                    * 点击地图上某个位置 移动中心点到点击的位置
                    * 隐藏任务列表
                    * 恢复地图满屏
                    * */
                    'click': () => {
                        // let m = e.lnglat;
                        // self.center = [m.lng, m.lat];
                        // console.log("点击到" + m);
                        self.amap.height = document.documentElement.clientHeight - document.getElementById('mainTabbar').offsetHeight + 'px';
                        self.showTaskList = false;
                    }
                },
                plugin: [
                    'ToolBar', {
                        pName: 'MapType',
                        defaultType: 0,
                        events: {
                            init(o) {
                                console.log(o);
                            },
                        }
                    },

                    {
                        pName: "Geolocation",
                        events: {
                            init(o) {
                                // o 是高德地图定位插件实例
                                o.getCurrentPosition((status, result) => {
                                    // console.log(result.position.lng,result.position.lat);  //  能获取定位的所有信息
                                    if (result && result.position) {
                                        // 经纬度
                                        self.lng = result.position.lng;
                                        self.lat = result.position.lat;
                                        // 地址信息
                                        self.center = [self.lng, self.lat];
                                    }
                                });
                            }
                        }
                    }
                ],


                // 任务列表的属性
                showTaskList: false,
                taskList: {
                    position: 'absolute',
                    bottom: '',
                    height: '',
                    width: '',
                    backgroundColor: 'white',
                    overflow: 'scroll',
                },
                //点坐标测试
                markers: [],
                //任务列表测试数据
                items: [],

                markerItems: [],

            };
        },

        methods: {
            // getMap() {
            //     // amap vue component
            //     console.log(amapManager._componentMap);
            //     // gaode map instance
            //     console.log(amapManager._map);
            // },

            showList(index) {
                this.showTaskList = true;
                this.taskList.height = document.documentElement.clientHeight * 0.3 + 'px';
                this.taskList.width = document.documentElement.clientWidth + 'px';
                this.taskList.bottom = document.getElementById('mainTabbar').offsetHeight + 'px';
                this.amap.height = document.documentElement.clientHeight * 0.7 - document.getElementById('mainTabbar').offsetHeight + 'px';
//清空items
                this.items = [];
                //加载任务列表
                console.log("loading task list.....");
                console.log(this.markers);
                console.log(index);
                for (let i = 0; i < this.markers[index].tasks.length; i++) {
                    this.axios.get("/api/task/" + this.markers[index].tasks[i], {}).then((res) => {
                        console.log(res);
                        //取出该marker的任务id并区获得任务后获得头像，添加进items
                        this.axios.post("/api/user/getidhead", {
                            "user_id": "" + res.data.data[0].creatid
                        }).then((res_serch) => {
                            console.log(eval(res.data.data[0].task_tag));
                            this.items.push(
                                {
                                    id: res.data.data[0].task_id,
                                    head_portrait: res_serch.data.data.headportrait,
                                    title: res.data.data[0].task_name,
                                    tags: eval(res.data.data[0].task_tag),
                                    finished: false,
                                    distance: Math.floor(AMap.GeometryUtil.distance([res.data.data[0].lon, res.data.data[0].lat], [this.lng,this.lat]) / 10) / 100,
                                },
                            )
                            console.log(this.items);

                        });
                    }).catch((error) => {
                        console.log(error);
                        console.log("???????")
                    })
                }
                console.log(this.items);
            },
            show(event) {
                // console.log(event);
                this.$router.push({
                    name: 'task_detail',
                    params: {
                        id: event.currentTarget.name,
                        cache: true,
                    }
                });
            },


            //addMarker
            addMarker(marker) {

//新marker的position
                let position = [marker.lon, marker.lat];
                let limit = 10000  //距离
                for (let i = 0; i < this.markers.length; i++) {
                    // 判断新加入marker 与 markers中各个的距离，小于limit 直接聚合
                    let distance = AMap.GeometryUtil.distance(position, this.markers[i].position);
                    if (distance < limit) {
                        this.markers[i].tasks.push(marker.task_id);
                        return;
                    }
                }
                let index = this.markers.length;
                this.markers.push({
                    id: marker.task_id,
                    position: [marker.lon, marker.lat],
                    tasks: [marker.task_id],
                    events: {
                        'click': () => {
                            this.showList(index);
                        },
                    },
                    animation: 'AMAP_ANIMATION_NONE',
                })

            }
            ,
        }

        ,
        created() {
            document.documentElement.style.fontSize = document.documentElement.clientWidth / 360 * 16 + 'px';
            //请求数据
            this.axios.get("/api/task/list", {
                headers: {
                    token: this.$store.state.Authorization
                },
            }).then((res) => {
                //addMarker
                console.log("添加...");
                for (let i = 0; i < res.data.data.length; i++) {
                    console.log(eval(res.data.data[i]));
                    this.addMarker(eval(res.data.data[i]));
                }
                console.log(this.markers);
            }).catch((error) => {
                console.log(error);
            });
        }
        ,
        mounted() {
            this.amap = {
                height: document.documentElement.clientHeight - document.getElementById('mainTabbar').offsetHeight + 'px',
            };

            console.log(this.amap.height);
            console.log(this.markers);
        }

    }
    ;

    // // 初始化地图对象，加载地图
    // let map = new AMap.Map("amap", {});
    //
    //
    //
    // function mapZoomstart(){
    //     console.log(1)
    // }
    // function mapZoom(){
    //     console.log(2)
    // }
    // function mapZoomend(){
    //     console.log(3)
    // }
    //
    //
    // // 事件绑定
    // function zoomOn(){
    //     map.on('zoomstart', mapZoomstart);
    //     map.on('zoomchange', mapZoom);
    //     map.on('zoomend', mapZoomend);
    // }
    //
    // console.log(map);
    // zoomOn();

</script>

<style scoped>
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