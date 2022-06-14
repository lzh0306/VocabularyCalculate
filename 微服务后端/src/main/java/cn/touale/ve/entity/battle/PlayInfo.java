package cn.touale.ve.entity.battle;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Touale
 * @description PlayInfo
 * @date 2022/6/14 8:46
 */
@Data
@Accessors(chain = true)
public class PlayInfo {
    private Question question;
    private ArrayList<User> users;
    private Long roomId;
    private String result;
}
