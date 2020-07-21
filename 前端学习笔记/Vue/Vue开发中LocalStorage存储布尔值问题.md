#### Vue开发中LocalStorage存储布尔值问题

 直接存入bool值 取出来是字符串

所以会出现‘false’ 在条件判断时相当于true

需要通过转化为Number存储可以很好的解决这个问题。