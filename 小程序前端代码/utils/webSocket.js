
// const host = config.websocketServer; // websocket服务器baseUrl
let sotk = null;
let socketOpen =false;
function ws_connect(reMsg){
  sotk = wx.connectSocket({
    url: "ws://121.40.165.18:8800",
    header: {
      'content-type': 'application/json'
    }
  })

  sotk.onOpen(res => {
    socketOpen = true;
    console.log('监听 WebSocket 连接打开事件。', res);
  })
  sotk.onClose(onClose => {
    socketOpen = false;
    console.log('监听 WebSocket 连接关闭事件。', onClose)
  })
  sotk.onError(onError => {
    socketOpen = true;
    console.log('监听 WebSocket 错误。错误信息', onError)
  })

  // 收到消息
  sotk.onMessage(onMessage => {
    // var data = JSON.parse(onMessage.data);
    reMsg(onMessage);
  })
}

function sendMsg(msg,success){
  if (socketOpen) {
    console.log('通过 WebSocket 连接发送数据', JSON.stringify(msg))
    sotk.send({
      data: JSON.stringify(msg)
    }, function (res) {
      success(res)
    })
  }
}

module.exports.ws_connect = ws_connect;
module.exports.sendMsg = sendMsg;