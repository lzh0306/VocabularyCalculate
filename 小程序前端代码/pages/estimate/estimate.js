// pages/estimate/estimate.js
import request from '../../utils/request'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        word: '',
        isShow: "1",
        time: 0,
        wordsList: '',
        index: '0',
        result: [],
        width: "0",
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        wx.showToast({
            title: '正在获取词汇',
            icon: 'loading',
            duration: 15000,
            mask: true
        })
        this.getWords("/preply/getInitVe")
    },

    //获取单词
    async getWords(url, data = {}, method = 'GET',CType='application/json') {
        let result = await request(url, data, method,CType)
        wx.hideToast()
        if (result.code == 1) {
            let word = result.data[0].word
            let time = this.data.time
            time++
            this.setData({
                word,
                wordsList: result.data,
                time
            })
        }
    },

    //提交数据并进行第二次申请词汇
    async commit(url, data = {}, method = 'GET') {
        let result = await request(url, data, method)
        if (result.code == 1) {
            this.setData({
                width: "0",
                wordsList:'',
                result:[]
            })
            let data = "midpoint=" + result.data
            this.getWords("/preply/getSecondVe", data, "POST","application/x-www-form-urlencoded")
        }
    },

    //获取词汇估算结果，并进入下一个词汇
    next(e) {
        let known = e.currentTarget.dataset.known
        let index = this.data.index
        let wordsList = this.data.wordsList
        let result = this.data.result
        let width = ((index + 1) / wordsList.length) * 100
        let add = {
            "known": known,
            "wordId": wordsList[index].id
        }
        result.push(add)
        index++
        if (index < wordsList.length) {
            this.setData({
                index,
                result,
                word: wordsList[index].word,
                width: width + "%"
            })
        } else {
            this.setData({
                width: width + "%",
                index: 0,
            })
            let time = this.data.time
            if (time < 2) {
                wx.showToast({
                    title: '正在获取词汇',
                    icon: 'loading',
                    duration: 15000,
                    mask: true
                })
                this.commit("/preply/getResult", result, "POST")
            } else {

                this.setData({
                    isShow: 0
                })
            }

        }
    },
    //返回首页
    back() {
        wx.switchTab({
            url: '/pages/index/index',
        })
    },

    //再来一次
    oneMore() {
        console.log("one more time")
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