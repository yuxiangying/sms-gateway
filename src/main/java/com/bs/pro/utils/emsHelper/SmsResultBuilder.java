package com.bs.pro.utils.emsHelper;


public class SmsResultBuilder {

    //	private String code;    			// 返回码
//	private String message;    		// 返回信息
//	private boolean status = false;	//返回状态 true 正确 false 错误
//	private int total;        				// 共几条数据
//	private Long version;
    private SmsResult result = new SmsResult();

    public SmsResultBuilder() {
        result.setVersion(System.currentTimeMillis());
    }

    public SmsResultBuilder setChannel(int channel) {
        result.setChannel(channel);
        return this;
    }

    public SmsResultBuilder setCode(String code) {
        result.setCode(code);
        return this;
    }

    public SmsResultBuilder setMessage(String message) {
        result.setMessage(message);
        return this;
    }

    public SmsResultBuilder setStatus(boolean status) {
        result.setStatus(status);
        return this;
    }

    public SmsResultBuilder setTotal(int total) {
        result.setTotal(total);
        return this;
    }

    public SmsResultBuilder setVersion(Long version) {
        result.setVersion(version);
        return this;
    }

    public SmsResultBuilder setCallbackId(String callbackId) {
        result.setCallbackId(callbackId);
        return this;
    }

    public SmsResult build() {
        return result;
    }
}
