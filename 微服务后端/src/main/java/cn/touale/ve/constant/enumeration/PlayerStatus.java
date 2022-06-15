package cn.touale.ve.constant.enumeration;

/**
 * @author Touale
 * @description MessageTypeEnum
 * @date 2022/6/13 8:48
 */
public enum PlayerStatus {
    IN(-1),
    MATCHING(0),
    PLAYING(1),
    OVER(2),
    ;
    private Integer status;

    PlayerStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }


}
