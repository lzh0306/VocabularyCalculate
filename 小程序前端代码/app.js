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
