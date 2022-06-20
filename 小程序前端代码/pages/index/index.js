// pages/index/index.js
Page({

    /**
     * 页面的初始数据
     */
    data: {

    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

    },

    //获取微信账号信息
    getUserProfile(e) {
        wx.getUserProfile({
            desc: '用于完善会员资料',
            success: (res) => {
                wx.setStorageSync('userInfo', res.userInfo)
                if (e == "1") {
                    wx.navigateTo({
                        url: '/pages/estimate/estimate',
                    })
                } else {
                    wx.navigateTo({
                        url: '/pages/marry/marry',
                    })
                }
            }
        })
    },

    //跳转词汇量估算
    toEstimate() {
        let userInfo = wx.getStorageSync('userInfo')
        if (userInfo == '') {
            this.getUserProfile(1)
        } else {
            wx.navigateTo({
                url: '/pages/estimate/estimate',
            })
        }

    },

    //跳转匹配对手
    toMarry() {
        let userInfo = wx.getStorageSync('userInfo')
        if (userInfo == '') {
            this.getUserProfile(2)
        } else {
            wx.navigateTo({
                url: '/pages/marry/marry',
            })
        }
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