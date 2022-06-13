package cn.touale.ve.entity.battle;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Touale
 * @description Question
 * @date 2022/6/13 9:50
 */
@Data
@Accessors(chain =true)
public class Question {
    private Integer wordId;
    private String question;
    private String A;
    private String B;
    private String C;
    private String D;
    private Integer answer;
}
