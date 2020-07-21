### JS 序列化与反序列化

#### 序列化

```
1.使用toJSONString
var last=obj.toJSONString(); //将JSON对象转化为JSON字符  
2.使用stringify
var last=JSON.stringify(obj); //将JSON对象转化为JSON字符  
```



#### 反序列化

```
 1.使用eval 
var obj=eval("("+data+")");  
2.使用parseJSON
var obj = data.parseJSON(); //由JSON字符串转换为JSON对象  
 3.使用parse
 var obj = JSON.parse(data); //由JSON字符串转换为JSON对象  
```

