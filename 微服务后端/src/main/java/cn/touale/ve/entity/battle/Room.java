package cn.touale.ve.entity.battle;

import cn.touale.ve.constant.enumeration.RoomStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;

/**
 * @author Touale
 * @description Room
 * @date 2022/6/13 11:22
 */
@Data
@Accessors(chain = true)
public class Room {
    private Long id;
    private RoomStatus status; // 0 匹配中，1 游戏中，2 结束
    private Integer playerNumber;
    private ArrayList<Player> players = new ArrayList<>();
    private GameInfo gameInfo;
    private Integer number;// 题目已完成人数
}
