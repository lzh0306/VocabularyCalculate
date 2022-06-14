package cn.touale.ve.entity.battle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.websocket.Session;

/**
 * @author Touale
 * @description User
 * @date 2022/6/14 14:57
 */
@Data
@Accessors(chain = true)
public class User {
    private Integer userId;
    private String image;
    private String userName;
    private Integer score;
}
