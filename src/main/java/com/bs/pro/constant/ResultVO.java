package com.bs.pro.constant;

import lombok.Data;

import java.io.Serializable;

/**

 **/
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private T data;

    /**
     * 功能描述:
     * 〈无惨构造〉
     *
     * @return :
     * @author : zhl
     * @date : 2019/3/18 0018 上午 11:23
     */
    public ResultVO(){}

    /**
     * 功能描述: 
     * 〈构造方法〉
     * 
     * @param code 1
     * @param message 2
     * @return : ResultVO
     * @author : zhl
     * @date : 2019/3/18 0018 上午 11:22
     */
    public ResultVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVO(ResultCode rs) {
        this.code = rs.getCode();
        this.message = rs.getDesc();
    }

    public ResultVO(ResultCode rs, Object data) {
        this.code = rs.getCode();
        this.message = rs.getDesc();
        this.setData((T)data);
    }

    /**
     * 功能描述:
     * 〈操作成功〉
     *
     * @param  1
     * @return : void
     * @author : zhl
     * @date : 2019/3/16 0016 下午 16:49
     */
    public void success() {
        setCode(ResultCode.SUCCESS.getCode());
        setMessage(ResultCode.SUCCESS.getDesc());
    }

    /**
     * 功能描述:
     * 〈操作成功〉
     *
     * @param data 1
     * @return : void
     * @author : zhl
     * @date : 2019/3/16 0016 下午 16:50
     */
    public void success(Object data) {
        this.success();
        this.setData((T)data);
    }

    public void setSuccess(T data) {
        this.success();
        this.setData(data);
    }

    /**
     * 功能描述:
     * 〈操作异常〉
     *
     * @param  1
     * @return : void
     * @author : zhl
     * @date : 2019/3/16 0016 下午 16:50
     */
    public void exception() {
        setCode(ResultCode.BUSINESS_ERROR.getCode());
        setMessage(ResultCode.BUSINESS_ERROR.getDesc());
    }

    /**
     * 功能描述:
     * 〈操作异常〉
     *
     * @param  1
     * @return : void
     * @author : zhl
     * @date : 2019/3/16 0016 下午 16:50
     */
    public void exception(Integer code, String message) {
        setCode(code);
        setMessage(message);
    }

    /**
     * 功能描述:
     * 〈操作失败〉
     *
     * @param  1
     * @return : void
     * @author : zhl
     * @date : 2019/3/16 0016 下午 16:50
     */
    public void fail() {
        setCode(ResultCode.FAIL.getCode());
        setMessage(ResultCode.FAIL.getDesc());
    }

    /**
     * 功能描述:
     * 〈操作失败〉
     *
     * @param code 1
     * @param message 2
     * @return : void
     * @author : zhl
     * @date : 2019/3/16 0016 下午 16:50
     */
    public void fail(Integer code, String message) {
        setCode(code);
        setMessage(message);
    }
}
