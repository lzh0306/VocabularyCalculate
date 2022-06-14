package cn.touale.ve.controller;


import cn.touale.ve.config.ResultCode;
import cn.touale.ve.config.ResultDTO;
import cn.touale.ve.entity.battle.WsType;
import cn.touale.ve.utils.GameWebSocketUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;


import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


/**
 * websocket的处理类。
 * 作用相当于HTTP请求
 * 中的controller
 */
@Component
@Slf4j
@ServerEndpoint("/battle/{userId}")
public class BattleController {
    private Integer userId;

    /**
     * 连接建立成
     * 功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        try {
            log.info("用户连接-userId: {}", userId);
            GameWebSocketUtils.login(Integer.valueOf(userId), session);
            this.userId = Integer.valueOf(userId);
            GameWebSocketUtils.sendMessageToSession(session, new ResultDTO().buildSucc(ResultCode.SUCC,"登录成功",null, WsType.LOGIN).toJsonString());
        } catch (Exception e) {
            log.error("{}建立连接失败", userId);
            e.printStackTrace();
        }
    }

    /**
     * 连接关闭
     * 调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        GameWebSocketUtils.runWay(userId);
        log.info("连接关闭{}", userId);
    }

    /**
     * 收到客户端消
     * 息后调用的方法
     *
     * @param message 客户端发送过来的消息
     **/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info(userId + "发送消息:" + message);
        JSONObject jobj;
        String type;

        // type: ping 心跳
        //       match 匹配
        //       play 回答问题

        // answerResult ： 题目回答结果 boolean
        // image： 头像
        // userName : 用户名

        try {
            jobj = JSON.parseObject(message);
            type = jobj.getString("type");
        } catch (Exception e) {
            e.printStackTrace();
            GameWebSocketUtils.sendMessageToSession(session, "error");
            return;
        }


        if (type.equals("ping")) {
            GameWebSocketUtils.sendMessageToSession(session, "pong");
            return;
        }

        if (type.equals("match")) {
            String image = jobj.getString("image");
            String userName = jobj.getString("userName");
            GameWebSocketUtils.match(userId,image,userName);
            return;
        }

        if (type.equals("play")) {
            Boolean answerResult =  jobj.getBoolean("answerResult");
            GameWebSocketUtils.play(userId, answerResult); // ToDo 题目真实结果
            return;
        }

        GameWebSocketUtils.sendMessageToSession(session, "error");
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        GameWebSocketUtils.runWay(userId);
        log.error(session.getId(), error.getMessage());
        error.printStackTrace();
    }
}

