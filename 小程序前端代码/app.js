// app.js
App({
    onLaunch() {

        wx.cloud.init({
            env: "prod-6gk0tj7ef34798e3",
        });
        wx.cloud.init({
            traceUser: true
        })
        // 展示本地存储能力
        const logs = wx.getStorageSync('logs') || []
        logs.unshift(Date.now())
        wx.setStorageSync('logs', logs)

        wx.loadFontFace({
            global:true,
            family: 'webfont',
            source: 'url("//6869-hitcard-4ghxgbsqeeb0b3b7-1307749871.tcb.qcloud.la/YuWeiShuFaLiShuJianTi-1.ttf?sign=c4f09e3d3ddfd694699b48f581478d68&t=1655705278")',
            success: function (res) {
                console.log("succ-------------------")
                console.log(res.status) //  loaded
            },
            fail: function (res) {
                console.log("error-------------------")
                console.log(res.status) //  error
            },
            complete: function (res) {
                console.log("ok-------------------")
                console.log(res.status);
            }
        });

        // 登录
        wx.login({
            success: res => {
                // 发送 res.code 到后台换取 openId, sessionKey, unionId
            }
        })
    },
    globalData: {
        userInfo: null
    }
})