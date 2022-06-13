package cn.touale.ve.entity.battle;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Touale
 * @description BattleRes
 * @date 2022/6/13 9:32
 */
@Data
@Accessors(chain = true)
public class BattleResult {
    private Integer player1_ID;
    private Integer player2_ID;
    private Integer player1_SCORE;
    private Integer player2_SCORE;
    private String result;
}
