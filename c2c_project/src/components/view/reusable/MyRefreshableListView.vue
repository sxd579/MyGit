<template>
    <van-list>
        <van-cell
                v-for="(item,index) in this.mData"
                :key="index"
                @click="tab(this.onClickItem(item,index))"

                v-bind:icon="item.icon"
        >
        </van-cell>
    </van-list>
</template>

<script>
    export default {
        name: "MyRefreshableListView",
        params:{
            mData:Array,
            mLast:Number,
            mRow:Number,
            onClickItem:Function(Object,Number)
        }
    }
</script>

<style scoped>

</style>