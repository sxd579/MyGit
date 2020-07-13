<template>
    <div>
        <van-tabbar id="mainTabbar" v-model="currIndex">
            <van-tabbar-item
                    v-for="(item,index) in tabbars"
                    :key="index"
                    v-bind:icon="item.icon"
            >
                <span>{{item.title}}</span>
            </van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script>
    export default {
        name: "TabBarView",
        data() {
            return {
                currIndex: -1,
                currIndexExcept2:-1,
                tabbars: [
                    {
                        title: "首页",
                        icon:"home-o",
                    },
                    {
                        title: "附近",
                        icon: "location-o"
                    },
                    {
                        title: "发布任务",
                        icon: "add"
                    },
                    {
                        title: "订单",
                        icon: "todo-list-o"
                    },
                    {
                        title: "我的",
                        icon: "user-o"
                    }
                ]
            };
        },
        created(){
            this.currIndex=0
        },
        activated(){
            if(this.currIndex==2){
                this.currIndex=this.currIndexExcept2
            }
        },
        watch:{
            currIndex:function (val) {
                if(val!=2){
                    this.currIndexExcept2=val
                }
                switch (val) {
                case 0: this.$router.push({
                        name:'home',
                    params:{
                        cache:true
                    }
                    })
                    break;
                case 1: this.$router.push({
                        name:'nearby',
                    params:{
                        cache:true
                    }
                    })
                    break;
                case 2:
                    this.$router.push({
                        name:'release',
                        params:{
                            cache:true
                        }
                    })
                    break;
                case 3: this.$router.push({
                        name:'order',
                    params:{
                        cache:true
                    }
                    })
                    break;
                case 4: this.$router.push({
                        name:'mine',
                    params:{
                        cache:true
                    }
                    })
                    break;
                }
            }
        }
    };
</script>

<style lang="scss" scoped>
    .active_tab img {
        width: $dimen-tabbar-icon;
        height: $dimen-tabbar-icon;
    }

    .van-tabbar-item--active {
        color: $color-primary;
    }

</style>