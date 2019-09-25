const app = getApp();
Component({
  options: {
    addGlobalClass: true,
  },
  data: {
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar,
    ColorList: app.globalData.ColorList,
    list: [{
      title: '可回收物',
      img: "http://47.97.216.231:5000/images/可回收长.png",
      url: '/collected/collected'
    },
    {
      title: '湿垃圾',
      img: "http://47.97.216.231:5000/images/湿垃圾长.png",
      url: '/wet/wet'
    },
    {
      title: '干垃圾',
      img: "http://47.97.216.231:5000/images/干垃圾长.png",
      url: '/dry/dry'
    },
    {
      title: '有害垃圾',
      img: "http://47.97.216.231:5000/images/有害垃圾长.png",
      url: '/harmful/harmful'
    }
    ]
  },
  methods: {
    toChild(e) {
      wx.navigateTo({
        url: '/pages/garbage' + e.currentTarget.dataset.url
      })
    },
  }
});