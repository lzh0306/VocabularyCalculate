package cn.touale.ve.entity.battle;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Touale
 * @description GameInfo
 * @date 2022/6/13 9:50
 */
@Data
@Accessors(chain = true)
public class GameInfo {
    private List<Question> questionsList;
    private Integer index;
}
