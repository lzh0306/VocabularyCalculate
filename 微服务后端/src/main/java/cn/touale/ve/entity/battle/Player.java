package cn.touale.ve.entity.battle;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.websocket.Session;

/**
 * @author Touale
 * @description Player
 * @date 2022/6/13 11:24
 */
@Data
@Accessors(chain =true)
public class Player {
    private Integer userId;
    private Session session;
    private PlayerStatus status;
    private Long roomId;
    private Integer score;
}
