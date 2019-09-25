// pages/exam/exam.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
     isSubmit :false,
     correct:0,
     questions:[]

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
          var that = this
          wx.request({
            url: 'http://47.97.216.231:8080/getQuestions/',
            method:'GET',
            success: function (res) {
              //请求数据成功的回调函数
              var data = res.data
              console.log(data.space)
              that.setData({
                questions:data.space
              })
            }
        
          })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    var that = this
    wx.request({
      url: 'http://47.97.216.231:8080/getQuestions/',
      method: 'GET',
      success: function (res) {
        //请求数据成功的回调函数
        var data = res.data
        console.log(data.space)
        that.setData({
          questions: data.space
        })
      }

    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that = this
    wx.request({
      url: 'http://47.97.216.231:8080/getQuestions/',
      method: 'GET',
      success: function (res) {
        //请求数据成功的回调函数
        var data = res.data
        console.log(data.space)
        that.setData({
          questions: data.space
        })
      }

    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  //选择
  selectA:function(e){
      //  console.dir(e)
       var item = this.data.questions[e.currentTarget.dataset.index]
   
       item.A = !item.A
       if(item.A){
         item.selection = '厨余垃圾'
         item.D = false
         item.B = false
         item.C = false
       }
       else {
         item.selection = ''
         
       }
       this.setData({
         questions : this.data.questions
       })
       
    
  },

  selectB: function (e) {
    // console.dir(e)
    var item = this.data.questions[e.currentTarget.dataset.index]
    
    item.B = !item.B
    if (item.B) {
      item.selection = '可回收物'
      item.A = false
      item.D = false
      item.C = false
    }
    else {
      item.selection = ''

    }
    this.setData({
      questions: this.data.questions
    })
    
  },

  selectC: function (e) {
    // console.dir(e)
    var item = this.data.questions[e.currentTarget.dataset.index]
    
    item.C = !item.C
    if (item.C) {
      item.selection = '有害垃圾'
      item.A = false
      item.B = false
      item.D = false
    }
    else {
      item.selection = ''
      
    }
    this.setData({
      questions: this.data.questions
    })
    

  },

  selectD: function (e) {
    // console.dir(e)
    var item = this.data.questions[e.currentTarget.dataset.index]
    
    item.D = !item.D
    if (item.D) {
      item.selection = '其他垃圾'
      item.A = false
      item.B = false
      item.C = false
    }
    else{
      item.selection =''
    }
    this.setData({
      questions: this.data.questions
    })
    
  },
  submit:function(){
    var correct = this.correct ;
    correct = 0
    var i
     for(i =0;i<20;i++){
       var answer =this.data.questions[i].answer
       console.log(answer)
       var selection =this.data.questions[i].selection
       console.log(selection)
       console.log(answer == selection)
         if(answer == selection){
           correct++
         }
     }
    var isSubmit = this.data.questions.isSubmit
    isSubmit = true;
    this.setData({
      isSubmit:isSubmit
    })
     console.log(correct)
    wx.showModal({
      title: '正确率',
      content: ''+correct+'/20',
      cancelText:'查看答案',
      confirmText:'返回上级',
      success(res){
        if(res.confirm){
          console.log('返回首页')
          wx.navigateBack({
            delta: 100
          }) 
      }
      }
    })
  }

})