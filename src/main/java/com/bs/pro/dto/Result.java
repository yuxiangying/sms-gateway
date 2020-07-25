package com.bs.pro.dto;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = -7393535371067917688L;

    /**
     * 返回代码,参见代码参照表
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回的数据
     */
    private transient T data;

    public Result() {
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 直接返回错误信息
     *
     * @param code
     * @param message
     * @return
     */
    public static Result getErrorResultInstance(String code, String message) {
        Result result = new Result(code, message);
        return result;
    }

    public static Result getValidatorErrorsResultInstance(BindingResult result) {
        if (result.hasFieldErrors()) {
            // 只取第一个错误信息,返回给调用端
            FieldError error = result.getFieldErrors().get(0);
            StringBuilder message = new StringBuilder(error.getField());
            message.append(error.getDefaultMessage());
            return Result.getErrorResultInstance(error.getCode(), message.toString());

        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
