<template>
    <div>
    <van-nav-bar id="navBar"
                 v-bind:title="mTitle"
                 left-text="返回"
                 left-arrow
                 @click-left="back">

    </van-nav-bar>
    </div>
</template>

<script>
    export default {
        name: "MyNavBar",
        props: {
            mTitle: String
        },
        methods: {
            back() {
                this.$router.go(-1);//返回上一层
            },
        },
    }
</script>

<style lang="scss" scoped>

    #navBar /deep/ .van-ellipsis {
        color: $color-text-primary;
    }


</style>