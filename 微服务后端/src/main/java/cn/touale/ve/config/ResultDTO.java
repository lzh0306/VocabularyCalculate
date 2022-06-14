package cn.touale.ve.config;
import cn.touale.ve.constant.enumeration.ResultCode;
import cn.touale.ve.constant.enumeration.WsType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;



/**
 * @author Touale
 * @description ResultDTO
 * @date 2022/6/11 15:25
 */
@Data
public class ResultDTO<T> {

    private String code;

    private T data;

    private String msg;

    private WsType type;

    public ResultDTO() {
    }

    /**
     * 判断成功与否。
     *
     * @return
     */
    @JSONField(serialize = false)
    public boolean isSucc() {
        if (ResultCode.SUCC.getCode().equals(this.code)) {
            return true;
        }
        return false;
    }

    public ResultDTO<T> succ() {
        this.code = ResultCode.SUCC.getCode();
        return this;
    }

    public ResultDTO<T> fail(ResultCode code) {
        this.code = code.getCode();
        return this;
    }

    public ResultDTO<T> fail(String code) {
        this.code = code;
        return this;
    }

    public ResultDTO<T> succ(ResultCode code) {
        this.code = code.getCode();
        return this;
    }


    public ResultDTO<T> data(T data) {
        this.setData(data);
        return this;
    }

    public ResultDTO<T> type(WsType type) {
        this.setType(type);
        return this;
    }

    public ResultDTO<T> msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    /**
     * 开始构建成功返回方法。
     *
     * @param msg
     * @param data
     * @return
     */
    public ResultDTO<T> buildSucc(String msg, T data) {
        this.succ().msg(msg).data(data);
        return this;
    }

    public ResultDTO<T> buildSucc(String msg, T data,WsType type) {
        this.succ().msg(msg).data(data).type(type);
        return this;
    }

    public ResultDTO<T> buildSucc(ResultCode code,String msg, T data,WsType type) {
        this.succ(code).msg(msg).data(data).type(type);
        return this;
    }

    /**
     * 开始构建成功返回方法。
     *
     * @return
     */
    public ResultDTO<T> buildSucc() {
        buildSucc("succ", null);
        return this;
    }

    /**
     * 开始构建成功返回方法。
     *
     * @return
     */
    public ResultDTO<T> buildSucc(T data) {
        buildSucc("调用成功", data);
        return this;
    }

    /**
     * 开始构建成功返回方法。
     *
     * @param msg
     * @return
     */
    public ResultDTO<T> buildSucc(String msg) {
        buildSucc(msg, null);
        return this;
    }

    /**
     * 开始构建失败返回方法。
     *
     * @return
     */
    public ResultDTO<T> buildFail() {
        buildError(ResultCode.ERROR, "fail");
        return this;
    }

    /**
     * 开始构建失败返回方法。
     *
     * @return
     */
    public ResultDTO<T> buildFail(String msg) {
        buildError(ResultCode.ERROR, msg);
        return this;
    }

    /**
     * 开始构建失败返回方法。
     *
     * @param msg
     * @return
     */
    public ResultDTO<T> buildFail(ResultCode code, String msg) {
        this.buildFail(code, msg, null);
        return this;
    }

    /**
     * 开始构建失败返回方法。
     *
     * @param msg
     * @return
     */
    public ResultDTO<T> buildFail(String code, String msg) {
        this.buildFail(code, msg, null);
        return this;
    }

    /**
     * 开始构建失败返回方法。
     *
     * @param msg
     * @param data
     * @return
     */
    public ResultDTO<T> buildFail(ResultCode code, String msg, T data) {
        this.fail(code).msg(msg).data(data);
        return this;
    }

    public ResultDTO<T> buildFail(String msg, T data) {
        this.fail(ResultCode.ERROR).msg(msg).data(data);
        return this;
    }

    /**
     * 开始构建失败返回方法。
     *
     * @param msg
     * @param data
     * @return
     */
    public ResultDTO<T> buildFail(String code, String msg, T data) {
        this.fail(code).msg(msg).data(data);
        return this;
    }

    /**
     * 开始构建返回异常方法。
     *
     * @param msg
     * @return
     */
    public ResultDTO<T> buildError(ResultCode code, String msg) {
        this.code = code.getCode();
        this.msg = msg;
        return this;
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }
}