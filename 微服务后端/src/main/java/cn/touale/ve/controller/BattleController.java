package cn.touale.ve.controller;


import cn.touale.ve.utils.GameWebSocketUtils;
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
            GameWebSocketUtils.sendMessageToSession(session, "欢迎用户" + userId);
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

        String type = message;

        if (type.equals("ping")) {
            GameWebSocketUtils.sendMessageToSession(session, "pong");
            return;
        }

        if (type.equals("match")) {
             GameWebSocketUtils.match(userId);
            return;
        }

        if (type.equals("play")) {
            GameWebSocketUtils.play(userId, true); // ToDo 题目真实结果
            return;
        }

        if (type.equals("get")) {
            GameWebSocketUtils.play(userId, true); // ToDo 题目真实结果
            return;
        }


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

