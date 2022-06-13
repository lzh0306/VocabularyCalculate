package cn.touale.ve.entity.battle;

/**
 * @author Touale
 * @description WsType
 * @date 2022/6/13 14:33
 */
public enum WsType {
    MATCH(0),
    QUESTION(1),
    OVERGAME(2),
    ;

    private Integer status;
    WsType(Integer status){
        this.status = status;
    }
    public Integer getStatus(){
        return status;
    }


}
