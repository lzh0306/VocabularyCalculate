// pages/rank/rank.js
import request from '../../utils/request'
import config from '../../utils/config'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        rankings: [],
        userInfo: '',
        isRefresh: '',
        rank: '',
        score: '0',
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        let rankings = wx.getStorageSync('rankings')
        if (rankings == "") {
            this.getRankings()
        } else {
            this.setData({
                rankings
            })
        }
        this.getUserRank()
    },

    //获取用户自身排名
    getUserRank() {
        let rankings = this.data.rankings
        let userInfo = wx.getStorageSync('userInfo')
        for (var i = 0; i < rankings.length; i++) {
            if (rankings[i].userName == userInfo.nickName) {
                let rank = i + 1
                this.setData({
                    userInfo: rankings[i],
                    rank
                })
            }
            if (this.data.rank == '') {
                let rank = 100
                userInfo.image = userInfo.avatarUrl
                userInfo.userName = userInfo.nickName
                userInfo.score = 0
                this.setData({
                    userInfo,
                    rank
                })
            }
        }
    },

    //获取排行榜
    async getRankings() {
        let url = 1
        let data = {
            size: 20
        }
        wx.showToast({
            title: '正在加载',
            icon: 'loading',
            duration: 15000
        })
        let result = await request(config.url[url].getRankings, data, "GET")
        wx.hideToast()
        this.setData({
            rankings: result
        })
        this.getUserRank()
        wx.setStorageSync('rankings', result)
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
        this.getRankings()
        this.setData({
            isRefresh: false
        })
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