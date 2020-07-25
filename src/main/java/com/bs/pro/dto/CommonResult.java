package com.bs.pro.dto;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;

public class CommonResult<T> implements Serializable {
    private static final long serialVersionUID = -7393535371067917688L;

    /**
     * 返回代码,参见代码参照表
     */
    private String returnCode;
    /**
     * 返回消息
     */
    private String returnMessage;
    /**
     * 返回状态 true正确 false出错
     */
    private boolean returnStatus;
    /**
     * 返回对象总数,只有当data是list时才有值
     */
    private int total;
    /**
     * 返回的数据
     */
    private transient T data;

    public CommonResult() {
    }

    public CommonResult(String returnCode, String returnMessage) {
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    /**
     * 直接返回错误信息
     *
     * @param returnCode
     * @param returnMessage
     * @return
     */
    public static CommonResult getErrorCommonResultInstance(String returnCode, String returnMessage) {
        CommonResult commonResult = new CommonResult(returnCode, returnMessage);
        commonResult.setReturnStatus(false);
        return commonResult;
    }

    public static CommonResult getValidatorErrorsCommonResultInstance(BindingResult result) {
        if (result.hasFieldErrors()) {
            // 只取第一个错误信息,返回给调用端
            FieldError error = result.getFieldErrors().get(0);
            StringBuilder message = new StringBuilder(error.getField());
            message.append(error.getDefaultMessage());
            return CommonResult.getErrorCommonResultInstance(error.getCode(), message.toString());

        }
        return null;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public boolean getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(boolean returnStatus) {
        this.returnStatus = returnStatus;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
