// pages/personal/personal.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        userInfo: [],
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options){
        let userInfo = wx.getStorageSync('userInfo')
        this.setData({
          userInfo
        })
      },
      //获取微信账号信息
      getUserProfile(e) {
        wx.getUserProfile({
          desc: '用于完善会员资料',
          success: (res) => {
            this.setData({
              userInfo: res.userInfo
            })
            wx.setStorageSync('userInfo', res.userInfo)
          }
        })
    },
  //退出登录
  goOut() {
    let that = this;
    wx.showModal({
      title: '提示',
      content: '确定要退出吗？',
      success: function (res) {
        if (res.confirm) {
          wx.removeStorageSync('userInfo')
          that.setData({
            userInfo: [],
          })
        }
      }
    })
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

    }
})