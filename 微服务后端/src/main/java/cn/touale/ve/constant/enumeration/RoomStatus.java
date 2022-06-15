package cn.touale.ve.constant.enumeration;

/**
 * @author Touale
 * @description RoomStatus
 * @date 2022/6/13 11:55
 */
public enum RoomStatus {
    MATCHING(0),
    PLAYING(1),
    OVER(2),
    ;
    private Integer status;

    RoomStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }


}
