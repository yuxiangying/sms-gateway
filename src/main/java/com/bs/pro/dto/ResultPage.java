package com.bs.pro.dto;

public class ResultPage <T> {

    private String message;

    private String code;

    private Integer total;

    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static ResultPage getResultInstance(String code, String message){
        ResultPage resultPage = new ResultPage();
        resultPage.setCode(code);
        resultPage.setMessage(message);
        return resultPage;
    }


}
