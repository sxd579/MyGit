## Axios

url传参是写到config.params里
token是作为头信息写到config.headers里
body传参（常用json格式）是写到data里

- GET参数通过URL传递，POST放在Request body中。



post

```
 this.axios.get("/api/task/distance", {
                params: {
                    lon: 115,
                    lat: 34,
                }
            }).then((res) => {
               for (let key in res.data.data[0]){
                   console.log('key:'+key);
                   console.log(res.data.data[0][key]);
               }
               console.log(res.data.data[0].task_tag);
                for (let i = 0; i < res.data.data.length; i++) {
                    this.items.push(
                        {
                            id:res.data.data[i].task_id,
                            head_portrait: 'https://img.yzcdn.cn/vant/cat.jpeg',
                            title: res.data.data[i].task_name,
                            tags: res.data.data[i].task_tag,
                            finished: false,
                        },
                    )
                }
            }).catch((error) => {
                alert("error")
                alert(error);
            });
```





post



```
   this.axios.post("/api/user/task/accepting/", {
                    task_id: this.$route.params.id,
                },{
                    headers:{
                        token:this.$store.state.Authorization,
                    },
                    params:{
                        task_id:this.$route.params.id,
                    }
                }).then(res => {
                    console.log("success" + res);
                }).catch(error => {
                    console.log(error);
                })
            },
```

