## JS将时间戳转化为yyyy-mmmm-dddd

```
function transformTimestamp (timestamp) {
	let date = new Date(timestamp)
	let year = date.getFullYear()
    let month= date.getMonth() + 1 > 9 ? date.getMonth() + 1 : '0' + (date.getMonth() + 1)
    let date = date.getDate() + 1 > 9 ? date.getDate() + 1 : '0' + (date.getDate() + 1)
    return year + month + date
}
```

