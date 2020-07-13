<template>
    <div id="app">
        <keep-alive>
            <router-view/>
        </keep-alive>
        <keep-alive>
            <TabBarView v-if="showTabbar" />
        </keep-alive>
    </div>
</template>

<script>
    import TabBarView from "@/components/view/TabBarView";
    export default {
        name: 'App',
        data () {
            return {
                showTabbar: true,
                screenHeight:0
            }
        },
        watch: {
            /**
             * 侦听路由的变化,控制底部tabbar的显示
             */
            '$route' (to) {
                if (to.path === '/home' || to.path === '/nearby' || to.path === '/order' || to.path === '/mine') {
                    this.showTabbar = true
                } else {
                    this.showTabbar = false
                }
            }
        },

        components: {
            TabBarView
        },
        mounted(){
            this.screenHeight=document.documentElement.clientHeight || document.body.clientHeight
        }
    }
</script>

<style lang="scss" scoped>
    #app{
        height: 100%;
        background-color: #f0eff5;
        // 设置全局字体
        font-family: 'PingFang SC', 'STHeitiSC-Light', 'Helvetica-Light', arial, sans-serif, 'Droid Sans Fallback';
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        user-select: none;
        -webkit-tap-highlight-color: transparent;
    }
</style>