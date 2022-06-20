
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
  })
  socketTask.onClose(function (res) {
    socketOpen = false;
    reMsg(10033)
  })
  socketTask.onError(onError => {
    socketOpen = true;
    reMsg(10033)
  })

  // 收到消息
  socketTask.onMessage(function (res) {
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