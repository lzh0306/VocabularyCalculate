package cn.touale.ve.constant.enumeration;

/**
 * @author Touale
 * @description ResultConfig
 * @date 2022/6/11 15:24
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCC("1", "", "成功"),
    /**
     * 失败
     */
    ERROR("0", "", "失败"),
    /**
     * 超时
     */
    TIMEOUT("-1", "", "超时"),
    MSG("2", "", "消息");

    /**
     * 错误码
     */
    private String code;
    /**
     * 国际化Key
     */
    private String iKey;
    /**
     * 描述
     */
    private String desc;

    ResultCode(String code, String iKey, String desc) {
        this.code = code;
        this.iKey = iKey;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getiKey() {
        return iKey;
    }

    public String getDesc() {
        return desc;
    }
}
