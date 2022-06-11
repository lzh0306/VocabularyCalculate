// pages/rank/rank.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        index: '0',//排行榜标识
        color: ['red', 'black'],
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

    },

    //点击切换排行榜
    tarClick(e) {
        let index = e.currentTarget.dataset.index
        this.setData({
            index
        })
        this.changeColor(index)
    },
    //滑动切换排行榜
    changeTab(e) {
        let index = e.detail.current
        this.setData({
            index
        })
        this.changeColor(index)
    },
    //切换标题色彩
    changeColor(index){
        var color=[]
        if(index==0){
            color = ['red', 'black']
        }else{
            color = ['black', 'red']
        }
        this.setData({
            color
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