## V-echart使用

extend属性里面可以调用官方文档里面的属性和函数

```
'xAxis.0.axisLabel.rotate': 45,
                    'yAxis.0.axisLabel.formatter':function (value, index) {
                        if(value.length>4){
                            return value.substr(0,4)+'...'
                        }else{
                            return value
                        }
                    },
                    tooltip: {
                        formatter: function (a) {
                            console.log(a);
                            let strOrArr = "";
                            let str = a[0].name
                            let num = 10;
                            let j = 0, o = j;
                            let newArray2 = [];
                            while(j < str.length){
                                    j += num;
                                    newArray2.push([str.slice(o, j)]);
                                    o = j;
                                }
                            strOrArr = newArray2.join('</br>');

                            // let name = a[0].name;
                            // for (let i =0;i<name.length/8;i++){
                            //     name = name.slice(0,8*(i+1))+'</br>'+ name.slice(0,8*(i+1))
                            // }
                            return ('商品名:' + strOrArr + '</br>' + '销量:' + a[0].value + '</br>');
                        }
                    },
```

首个元素则直接 name+:

如果层层调用则用""里面每层用.隔开