package cn.touale.ve.entity.battle;

import cn.touale.ve.constant.enumeration.PlayerStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.websocket.Session;

/**
 * @author Touale
 * @description Player
 * @date 2022/6/13 11:24
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "Player", description = "玩家信息")
public class Player {

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @JsonIgnore
    @ApiModelProperty(value = "会话标识")
    private Session session;

    @ApiModelProperty(value = "用户头像")
    private String image;

    @ApiModelProperty(value = "用户状态")
    private PlayerStatus status;

    @ApiModelProperty(value = "用户所在房间ID")
    private Long roomId;

    @ApiModelProperty(value = "对局分数")
    private Integer score;

    @ApiModelProperty(value = "用户名")
    private String userName;
}
