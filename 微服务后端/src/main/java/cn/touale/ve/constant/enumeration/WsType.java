package cn.touale.ve.constant.enumeration;

/**
 * @author Touale
 * @description WsType
 * @date 2022/6/13 14:33
 */
public enum WsType {
    LOGIN(-1),
    MATCH(0),
    QUESTION(1),
    OVERGAME(2),
    ;

    private Integer status;

    WsType(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }


}
