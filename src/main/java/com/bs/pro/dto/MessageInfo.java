package com.bs.pro.dto;

public class MessageInfo {

    private Integer retry;
    private String content;
    private String toAddress;
    private Integer type;
    private String appId;
    private String appKey;
    private Long sendTime;
    private String token;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "retry=" + retry +
                ", content='" + content + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", type=" + type +
                ", appId='" + appId + '\'' +
                ", appKey='" + appKey + '\'' +
                ", sendTime=" + sendTime +
                ", token='" + token + '\'' +
                '}';
    }
}
