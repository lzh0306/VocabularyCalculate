// pages/marry/marry.js
let websocket = require('../../utils/webSocket.js')
var count
Page({

    /**
     * 页面的初始数据
     */
    data: {
        isMarry: '1',
        isResult: '1',
        isConnect: 0,
        title: '',
        isAnswer: 0,
        isExit: 1,
        color: ["white", "white", "white", "white"],
        userInfo: [],
        anr: {},
        msg: "暂无公告",
        result: '',
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        websocket.ws_connect(data => {
            console.log(data) //打印常链接返回的更新内容 
            if (data.type == "LOGIN") {
                this.setData({
                    isConnect: 1,
                })
            } else if (data.type == "QUESTION") {
                if (data.code == "1") {
                    this.question(data)
                    this.count(1)
                } else {
                    this.setData({
                        msg: data.msg
                    })
                }
            } else if (data.type == "OVERGAME") {
                this.backAnimation()
                this.overGame(data)
            } else if (data == "10033") {
                if (this.data.isExit) {
                    this.exit("连接异常，房间已解散")
                }
            }
        })
        for (let i = 1; i <= 5; i++) {
            setTimeout(this.send, i * 1000)
        }
    },

    //
    closeMarry(){
        this.exit("已取消，退出房间")
    },
    //处理question数据
    question(data) {
        wx.hideLoading()
        wx.showToast({
            title: '加载中',
            icon: "loading",
            duration: 100
        })
        let that = this
        setTimeout(function timer() {
            let userId = wx.getStorageSync('userId')
            let userInfo = []
            if (userId == data.data.users[0].userId) {
                userInfo = data.data.users
            } else {
                userInfo.push(data.data.users[1])
                userInfo.push(data.data.users[0])
            }
            let title = data.data.question
            that.setData({
                title,
                isMarry: '',
                userInfo,
                isAnswer: 0,
                msg: data.msg,
                color: ["white", "white", "white", "white"]
            })
            that.goAnimation()
        }, 100)
        this.backAnimation()
    },

    //处理overGame数据
    overGame(data) {
        if (data.code == "1") {
            wx.showToast({
                title: data.msg,
                icon: "none",
                duration: 1000
            })
            this.setData({
                isExit: 0,
                msg: data.msg
            })
            let that = this
            setTimeout(function timer() {
                that.setData({
                    isResult: '',
                    result: data.data
                })
            }, 1100)
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

    //进度条动画
    goAnimation() {
        let option = {
            duration: 10000, // 动画执行时间
            timingFunction: 'ease-in' // 动画执行效果
        };
        var anr = wx.createAnimation(option); // 创建动画  
        anr.width("100%").step();
        this.setData({
            anr: anr.export()
        });
    },
    backAnimation() {
        let option = {
            duration: 10, // 动画执行时间
            timingFunction: 'ease-in' // 动画执行效果
        };
        var anr = wx.createAnimation(option); // 创建动画  
        anr.width("0%").step();
        this.setData({
            anr: anr.export()
        });

    },

    //10秒倒计时计数
    count(st) {
        if (st) {
            count = setTimeout(this.start, 10000)
        } else {
            clearTimeout(count)
        }
    },
    start() {
        this.backAnimation()
        this.send("play", false)
    },

    //获取选择答案提交
    choose(e) {
        if (this.data.isAnswer) {} else {
            this.count(0)
            this.backAnimation()
            let color = ["white", "white", "white", "white"]
            let answer = e.currentTarget.dataset.answer
            switch (answer) {
                case "a": {
                    answer = 0
                    break
                }
                case "b": {
                    answer = 1
                    break
                }
                case "c": {
                    answer = 2
                    break
                }
                case "d": {
                    answer = 3
                    break
                }
            }
            if (answer == this.data.title.answer) {
                color[answer] = "#28BFA0"
                let that = this
                setTimeout(function timer() {
                    that.send('play')
                }, 1000)

            } else {
                color[answer] = "#FE935B"
                let that = this
                setTimeout(function timer() {
                    that.send('play', false)
                }, 1000)

            }
            this.setData({
                color,
                isAnswer: 1
            })
        }
    },

    //退出房间
    exit(msg) {
        this.setData({
            isExit: 0
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

    //返回首页
    back() {
        wx.switchTab({
            url: '/pages/index/index',
        })
    },

    //再来一局
    oneMore() {
        this.setData({
            isMarry: "1",
            isResult: "1",
            isConnect: 0
        })
        this.onLoad()
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
        if (this.data.isExit) {
            this.exit("房间已解散")
        }
    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {
        if (this.data.isExit) {
            this.exit("房间已解散")
        }
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