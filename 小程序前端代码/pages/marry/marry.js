// pages/marry/marry.js
let websocket = require('../../utils/webSocket.js')
Page({

    /**
     * 页面的初始数据
     */
    data: {
        isMarry: '1',
        isConnect: 0,
        title: '',
        isAnswer:0,
        isExit:1,
        color:["greenyellow","greenyellow","greenyellow","greenyellow"],
        a:''
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

        
        websocket.ws_connect(data => {
            console.log(data) //打印常链接返回的更新内容
            let a = JSON.stringify(data)
            this.setData({
                a
            })
            if (data.type == "LOGIN") {
                this.setData({
                    isConnect: 1,
                })
            } else if (data.type == "QUESTION") {
                if (data.code == "1") {
                    wx.hideLoading()
                    wx.showToast({
                        title: '加载中',
                        icon:"loading",
                        duration:100
                      })
                      let that = this
                      setTimeout(function timer(){
                          let title = data.data.question
                          that.setData({
                        title,
                        isMarry: '',
                        isAnswer:0,
                        color:["greenyellow","greenyellow","greenyellow","greenyellow"]
                    })
                      },100)
                } else {
                    wx.showLoading({
                        title:"等待对手答题"
                    })
                }
            } else if (data.type == "MATCH") {
                // if (data.code == "1") {
                //     this.setData({
                //         isMarry: '',
                //     })
                // }
            } else if (data.type == "OVERGAME") {
                this.exit("对手离开，房间已解散")
            } else if (data.code == 1007||data.code == 1000||data.code == 1006) {
                if(this.data.isExit){
                    this.exit("连接异常，房间已解散")
                }
                
            }
        })
        for (let i = 1; i <= 5; i++) {
            setTimeout(this.send, i * 1000)
        }

    },

    //发送匹配消息
    send(type = "match", answerResult = true) {

        if (this.data.isConnect || type != "match") {
            let userInfo = wx.getStorageSync('userInfo')
            let msg = {
                "type": type,
                "answerResult": answerResult,
                "image": userInfo.avatarUrl,
                "userName": userInfo.nickName
            }
            this.setData({
                isConnect: 0
            })
            console.log(msg)
            websocket.sendMsg(msg)
        }
    },

    //取消匹配
    closeMarry() {
        websocket.close()
        this.exit("房间已解散")
    },

    //获取选择答案提交
    choose(e) {
        if(this.data.isAnswer){

        }else{
            let color = []
            let answer = e.currentTarget.dataset.answer
            console.log(answer)
            switch (answer){
                case "a":{
                    color = ["red","greenyellow","greenyellow","greenyellow"]
                    break
                }
                case "b":{
                    color = ["greenyellow","red","greenyellow","greenyellow"]
                    break
                }
                case "c":{
                    color = ["greenyellow","greenyellow","red","greenyellow"]
                    break
                }
                case "d":{
                    color = ["greenyellow","greenyellow","greenyellow","red"]
                    break
                }
            }
            this.setData({
                color,
                isAnswer:1
            })
            if (answer == this.data.title.answer) {
                this.send("play")
            } else {
                this.send("play", false)
            }
        }
    },

    //退出房间
    exit(msg) {
        this.setData({
            isExit:0
        })
        wx.showToast({
            title: msg,
            icon: "none",
            duration: 2000
        })
        websocket.close()
        setTimeout(function timer() {
            wx.switchTab({
                url: '/pages/index/index',
            })
        }, 2100)
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
        this.closeMarry()
    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {
        this.closeMarry()
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