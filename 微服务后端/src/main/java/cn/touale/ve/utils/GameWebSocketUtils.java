package cn.touale.ve.utils;

import cn.touale.ve.config.ResultCode;
import cn.touale.ve.config.ResultDTO;
import cn.touale.ve.entity.battle.*;
import cn.touale.ve.service.battle.BattleServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Touale
 * @description GameWebSocketUtils
 * @date 2022/6/13 11:21
 */
@Slf4j
@Component
public final class GameWebSocketUtils {
    public static final CopyOnWriteArrayList<Room> GAME_ROOMS = new CopyOnWriteArrayList<>();
    public static final CopyOnWriteArrayList<Player> ONLINE_PLAYERS = new CopyOnWriteArrayList<>();
    public static final Integer START_NUMBER = 2;

    @Autowired
    private BattleServer battleServer;
    public static GameWebSocketUtils gameWebSocketUtils;

    @PostConstruct
    public void init() {
        gameWebSocketUtils = this;
        gameWebSocketUtils.battleServer = this.battleServer;
    }

    public static Boolean isCanLogin(Integer userid) {
        for (Player player : ONLINE_PLAYERS) {
            if (player.getUserId().equals(userid)) {
                return false;
            }
        }
        return true;
    }

    public static void sendMessageToRoom(Long roomId, String message) {
        if (roomId == null) return;
        GAME_ROOMS.forEach(room -> {
            if (room.getId().equals(roomId)) {
                room.getPlayers().forEach(player -> {
                    sendMessageToSession(player.getSession(), message);
                });
            }
        });
    }

    public static void sendMessageToUser(Integer userId, String message) {
        if (userId == null) return;
        ONLINE_PLAYERS.forEach(player -> {
            if (player.getUserId().equals(userId)) {
                sendMessageToSession(player.getSession(), message);
            }
        });
    }

    public static void sendMessageToSession(Session session, String message) {

        if (session == null || !session.isOpen()) return;
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) return;

        synchronized (session) {
            try {
                basic.sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void login(Integer userid, Session session) {
        log.info("用户登录：{}", userid);
        if (!isCanLogin(userid)) {
            ResultDTO res = new ResultDTO();
            sendMessageToSession(session, res.buildFail("禁止重复登录").toJsonString());
            log.warn("用户登录失败：{}", userid);
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        Player player = new Player()
                .setUserId(userid)
                .setSession(session)
                .setStatus(PlayerStatus.IN)
                .setScore(0);
        ONLINE_PLAYERS.add(player);
    }

    public static Player findPlayerByUserid(Integer userid) {
        for (Player player : ONLINE_PLAYERS) {
            if (player.getUserId().equals(userid)) {
                return player;
            }
        }
        return null;

    }

    public static Room findRoomByRoomId(Long roomId) {
        if (roomId == null) return null;
        for (Room room : GAME_ROOMS) {
            if (room.getId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    public static void match(Integer userId, String image, String userName) {
        ResultDTO result = new ResultDTO();
        Room myRoom = null;
        Player player = findPlayerByUserid(userId);
        if (player == null) {
            sendMessageToUser(userId, result.buildError(ResultCode.MSG, "匹配异常，请重新加入游戏").toJsonString());
            return;
        }
        player.setImage(image).setUserName(userName);
        if (!player.getStatus().equals(PlayerStatus.IN)) {
            sendMessageToUser(userId, result.buildError(ResultCode.MSG, "匹配失败，已在对局中").toJsonString());
            return;
        }

        for (Room room : GAME_ROOMS) {
            if (room.getStatus().equals(RoomStatus.MATCHING)) {
                log.info(player.getUserName() + "进入房间" + room.getId());
                myRoom = room;
                myRoom.getPlayers().add(player);
                myRoom.setPlayerNumber(myRoom.getPlayerNumber() + 1);
                player.setRoomId(myRoom.getId());
                sendMessageToRoom(myRoom.getId(), result.buildSucc(ResultCode.MSG,
                        player.getUserName() + "加入房间" + myRoom.getId(),
                        null,
                        WsType.MATCH).toJsonString());
                player.setStatus(PlayerStatus.PLAYING);

                if (myRoom.getPlayers().size() >= START_NUMBER) {
                    myRoom.setStatus(RoomStatus.PLAYING);
                    // 通知开始游戏了
                    sendMessageToRoom(myRoom.getId(), result.buildSucc(ResultCode.MSG, "游戏即将开始，请选手做好准备",
                            null,
                            WsType.MATCH).toJsonString());
                    sendQuestionToRoom(myRoom);
                    return;
                }

            }
        }

        if (myRoom == null) {
            myRoom = new Room()
                    .setId(System.currentTimeMillis())
                    .setPlayerNumber(1)
                    .setStatus(RoomStatus.MATCHING)
                    .setGameInfo(new GameInfo()
                            .setQuestionsList(gameWebSocketUtils.battleServer.getQuestionsList(5))
                            .setIndex(0));

            myRoom.getPlayers().add(player);
            player.setRoomId(myRoom.getId());
            player.setStatus(PlayerStatus.PLAYING);
            GAME_ROOMS.add(myRoom);
            log.info(userId + "创建房间" + myRoom.getId());
            sendMessageToUser(userId, result.buildSucc(ResultCode.MSG, "匹配中...", null, WsType.MATCH).toJsonString());
            return;
        }
        sendMessageToUser(userId, result.buildSucc(ResultCode.TIMEOUT, "匹配失败...", null, WsType.MATCH).toJsonString());
    }

    public static void sendQuestionToRoom(Room room) {
        ResultDTO res = new ResultDTO();
        Integer currentIndex = room.getGameInfo().getIndex();
        if (currentIndex >= room.getGameInfo().getQuestionsList().size()) {
            // 对局结束
            sendResultToRoom(room);
            return;
        }

        Question question = room.getGameInfo().getQuestionsList().get(currentIndex);
        room.getGameInfo().setIndex(currentIndex + 1);
        room.setNumber(0);
        CopyOnWriteArrayList<Player> players = getAllPlayers();


        res.buildSucc("第" + (currentIndex + 1) + "题", new PlayInfo()
                        .setQuestion(question)
                //.setPlayerList(players)
                , WsType.QUESTION);
        log.info(res.toJsonString());

        sendMessageToRoom(room.getId(), res.toJsonString());
    }

    public static void play(Integer userId, Boolean res) {
        ResultDTO result = new ResultDTO();
        Player player = findPlayerByUserid(userId);
        if (player == null) {
            sendMessageToUser(userId, result.buildSucc(ResultCode.MSG, "游戏异常，请重新加入游戏",
                    null,
                    WsType.MATCH).toJsonString());
            return;
        }
        Room myRoom = findRoomByRoomId(player.getRoomId());
        if (myRoom == null) {
            sendMessageToUser(userId, result.buildSucc(ResultCode.MSG, "房间异常，请重新加入游戏",
                    null,
                    WsType.MATCH).toJsonString());
            return;
        }

        Integer currentScore = player.getScore();
        player.setScore(currentScore + (res ? 1 : -1));
        myRoom.setNumber(myRoom.getNumber() + 1);

        if (myRoom.getNumber().equals(2)) {
            sendQuestionToRoom(myRoom);
            return;
        }
        sendMessageToUser(userId, result.buildSucc(ResultCode.MSG, "等待对面玩家答题",
                null,
                WsType.QUESTION).toJsonString());

    }

    public static void sendResultToRoom(Room room) {
        ResultDTO res = new ResultDTO();
        Player a = room.getPlayers().get(0);
        Player b = room.getPlayers().get(1);
        res.buildSucc("对局结束", new BattleResult()
                .setPlayer1_ID(a.getUserId())
                .setPlayer2_ID(b.getUserId())
                .setPlayer1_SCORE(a.getScore())
                .setPlayer2_SCORE(b.getScore())
                .setResult((a.getScore() > b.getScore() ?
                        "恭喜用户" + a.getUserName() + "胜利"
                        :
                        "恭喜用户" + b.getUserName() + "胜利")
                ), WsType.OVERGAME);
        log.info("对局结果：{}", res);
        sendMessageToRoom(room.getId(), res.toJsonString());
        // 清理房间
        GAME_ROOMS.remove(room);
        ONLINE_PLAYERS.remove(a);
        ONLINE_PLAYERS.remove(b);
        try {
            a.getSession().close();
            b.getSession().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runWay(Integer userId) {
        Player temp = findPlayerByUserid(userId);
        if (temp == null) return;
        try {
            Long roomId = temp.getRoomId();
            if (roomId != null) {
                Room room = findRoomByRoomId(roomId);
                for (Player player : room.getPlayers()) {
                    ONLINE_PLAYERS.remove(player);
                    player.getSession().close();
                }
                ResultDTO res = new ResultDTO();
                res.buildSucc("用户" + temp.getUserName() + "离开，房间已解散", null, WsType.OVERGAME);
                sendMessageToRoom(room.getId(), res.toJsonString());
                GAME_ROOMS.remove(room);
                log.info("对局结果：{}", res);

            } else {
                temp.getSession().close();
                ONLINE_PLAYERS.remove(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CopyOnWriteArrayList<Room> getAllRoom() {
        return GAME_ROOMS;
    }

    public static CopyOnWriteArrayList<Player> getAllPlayers() {
        return ONLINE_PLAYERS;
    }
}
