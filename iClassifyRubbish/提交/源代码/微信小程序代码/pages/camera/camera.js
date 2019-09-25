// pages/camera/camera.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    src:'',
    isLoading : true,
    content:"该垃圾为有害垃圾"
    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

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


  //拍照
  takePhoto() {
    var that = this;
    var uploadTask;
    //CaneraContext 实例，与页面中唯一的camera组件绑定，操作对应的camera组件
    const ctx = wx.createCameraContext();
    //拍照方法
    ctx.takePhoto({
      //相片质量
      quality: 'high',
      //拍照成功
      success: (res) => {
        // tempImagePath 照片文件的临时路径
        console.log('temp photo path:', res.tempImagePath);

        //保存图片
        // wx.saveImageToPhotosAlbum({
        //   filePath: res.tempImagePath,
        //   success: function (fres) {
        //     console.log(fres);
        //   }
        // })
        // this.setData({
        //   src: ''+res.tempImagePath
        // })
        //上传拍好的照片
        var that = this;
        console.log(res.tempImagePath)
        wx.showLoading({
          title: '正在检测',
        })
         wx.uploadFile({
            url: 'http://47.97.216.231:8080/test_photo/', // 服务器地址
            filePath: res.tempImagePath,       //拍好的照片路径
            name: 'rubbish',        //key，服务端可以通过这个key获取文件的二进制内容
            formData: {
              'user': 'test'    //额外的信息 
            },
            success(res) {
              
            var data =  JSON.parse(res.data)
              console.log(data)
              //do something
              wx.hideLoading()
              wx.showModal({
                title: '检测完毕',
                content:''+data.type
              })
            }
           
          })
         }
    
    })
  },

  
})