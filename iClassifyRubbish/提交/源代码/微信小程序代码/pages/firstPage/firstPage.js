// pages/firstPage/firstPage.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    content: '该垃圾为有害垃圾'

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
  // 拍照功能
  getLocalImage: function () {
    var that = this;
    wx.chooseImage({
      count: 1,
      success: function (res) {
        // 这里无论用户是从相册选择还是直接用相机拍摄，拍摄完成后的图片临时路径都会传递进来   
        wx.showLoading({
          title: '正在检测',
        })
        console.log("选择路径为",res.tempFilePaths)
        wx.uploadFile({
          url: 'http://47.97.216.231:8080/test_photo/',
          filePath:String(res.tempFilePaths),
          name: 'rubbish',
          success: function (res) {
            var data = JSON.parse(res.data)
            console.log(data)
            //do something
            wx.hideLoading()
            wx.showModal({
              title: '检测完毕',
              content: '' + data.type
            })
          }
        })
      },
      fail: function (error) {
        console.error("调用本地相册文件时出错")
        console.warn(error)
      },
      complete: function () {

      }
    })
  },
  goTakePhoto:function(){


    wx.navigateTo({
      url: '../camera/camera',
    })
  }
  

  
})