package com.bs.pro.exception;

import lombok.Data;

@Data
public class WithLogInfoException extends RuntimeException {

    private String address;
    private String title;
    private String msg;
    private int type;
    private int channel;

    public WithLogInfoException(String address, String title, String msg, int type, int channel) {
        this.address = address;
        this.title = title;
        this.msg = msg;
        this.type = type;
        this.channel = channel;
    }
}
