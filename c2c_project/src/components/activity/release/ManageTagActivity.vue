<template>
    <div>
        <my-nav-bar m-title="管理标签"/>
        <van-field v-model="customTag"
                   label="自定义标签"
                   placeholder="单个标签的字数不超过5个"
                   clearable
                   maxlength="5"
                   style="margin-top: 10px">
            <template #button>
                <van-button class="addTagButton" @click="onAddTag" size="small">确定</van-button>
            </template>
        </van-field>
        <van-row style="margin-top: 20px;margin-bottom: 20px;height: 30px">
            <van-col
                    v-for="(item,index) in tags"
                    :key="index"
                    offset="2"
                    span="5"
            >
                <van-tag color="#51b0f6" size="large" @click="onClickTag(index)" style="display: inline-table;height: 100%;width: 100%;text-align: center">{{item}}</van-tag>
            </van-col>
        </van-row>
        <van-button round disabled color="#a0a0a0" style="margin-left:15%;width: 70%;margin-top: 50px">热门标签</van-button>
        <van-row
                v-for="(row,i) in hotTags"
                :key="i"
                style="margin-top: 20px;margin-bottom: 20px;height: 30px">
            <van-col
                    v-for="(col,j) in hotTags[i]"
                    :key="j"
                    offset="2"
                    span="5"
            >
                <van-tag color="#51b0f6" size="large" @click="onClickHotTag(i,j)" style="display: inline-table;height: 100%;width: 100%;text-align: center">{{col}}</van-tag>
            </van-col>
        </van-row>
        <div style="margin-top: 100px"/>
        <van-popup position="bottom" v-model="showPopup" style="height: 120px">
            <van-button @click="onDeleteTag" class="deleteButtonInPopup" type="danger">删除</van-button>
            <van-button @click="onCancelWhenDeleteTag" class="cancelButtonInPopup">取消</van-button>
        </van-popup>
        <van-button class="confirm" @click="onConfirm" color="#ffa92b" block>保存</van-button>
    </div>
</template>

<script>
    import MyNavBar from "@/components/view/reusable/MyNavBar";
    export default {
        name: "ManageTagActivity",
        components: {MyNavBar},
        data(){
            return{
                tags:[],
                customTag:"",
                clickedTagIndex:-1,
                showPopup:false,
                hotTags:[]
            }
        },
        created(){
            this.tags=this.$route.params.tags
            this.$axios.get("/tag/hot").then(res=>{
                // alert(res.data)
                // alert(res.data.data)
                // alert(res.data.code)
                var data=res.data.data
                for(var i=0;i<data.length;i++){
                    if(i%3==0){
                        this.hotTags.push([])
                    }
                    this.hotTags[parseInt(i/3)].push(data[i])
                }
            }).catch(error=>{
                alert(error)
            })
        },
        methods:{
            onAddTag(){
                if(this.customTag.length==0){
                    return
                }
                if(this.tags.length>=3){
                    this.notifyCannotAddTags()
                }
                else{
                    this.tags.push(this.customTag)
                    this.customTag=""
                }
            },
            onClickTag(index){
                this.clickedTagIndex=index
                this.showPopup=true
            },
            onDeleteTag(){
               this.tags.splice(this.clickedTagIndex,1)
                this.onCancelWhenDeleteTag()
            },
            onCancelWhenDeleteTag(){
                this.showPopup=false
            },
            onClickHotTag(row,col){
                if(this.tags.length>=3){
                    this.notifyCannotAddTags()
                    return
                }
                this.tags.push(this.hotTags[row][col])
            },
            notifyCannotAddTags(){
                alert("最多只能添加3个标签")
            },
            onConfirm(){
                localStorage.setItem('tag_manageTagActivity',this.tags)
                this.$router.go(-1);
            }
        }
    }
</script>

<style lang="scss" scoped>
    .addTagButton{
        color:$color-accent;
    }
    .confirm{
        height: 50px;
        position: fixed;
        margin-top: auto;
        bottom: 0;
        width: 100%;
    }
    .deleteButtonInPopup{
        height: 50px;
        width: 100%;
    }
    .cancelButtonInPopup{
        height: 50px;
        width: 100%;
        margin-top: 20px;
    }
</style>