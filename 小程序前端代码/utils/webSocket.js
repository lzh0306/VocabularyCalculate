
// const host = config.websocketServer; // websocket服务器baseUrl
let socketOpen =false;
var copySto;

async function ws_connect(reMsg){
 let userId = Math.floor((Math.random()*10000000)+1);
 wx.setStorageSync('userId', userId)
 let path = "/battle/"+userId
 const  {socketTask}  = await wx.cloud.connectContainer({
    config: {
      env: 'prod-6gk0tj7ef34798e3',  // 微信云托管的环境ID
    },
    service: 'vocabularycalculate',        // 服务名
    path: path            // 不填默认根目录
  })
  copySto = socketTask;

  socketTask.onOpen(function (res) {
    socketOpen = true;
    console.log('【WEBSOCKET】', '链接成功！')
  })
  socketTask.onClose(function (res) {
    socketOpen = false;
    reMsg(10033)
    console.log('【WEBSOCKET】链接关闭！')
  })
  socketTask.onError(onError => {
    socketOpen = true;
    console.log('监听 WebSocket 错误。错误信息', onError)
    reMsg(10033)
  })

  // 收到消息
  socketTask.onMessage(function (res) {
    console.log('【WEBSOCKET】', res.data)
    let data = JSON.parse(res.data)
    reMsg(data);
  })
}

function close(){
  if(socketOpen){
    copySto.close()
  }
}

function sendMsg(msg){
  if (socketOpen) {
    console.log('通过 WebSocket 连接发送数据', JSON.stringify(msg))
    copySto.send({
      data: JSON.stringify(msg)
    }, function (res) {
      success(res)
    })
  }
}

module.exports.ws_connect = ws_connect;
module.exports.sendMsg = sendMsg;
module.exports.close = close