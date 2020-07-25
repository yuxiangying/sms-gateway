package com.bs.pro.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author auto create
 * @since 1.0, 2019-07-08 16:26:55
 */
@Data
public class ProItemHistory implements Serializable {
    private static final long serialVersionUID = 5845292660196965L;

    private Integer id;
    private String code;
    private String name;
    private String model;
    private Integer concernLevel;
    private String chargeMan;
    private String members;
    private String startTime;
    private String endTime;

    private String content;
    private String schemeTime;
    private String schemeMan;
    private String phoneTime;
    private String phoneMan;
    private String prototypeTime;
    private String prototypeMan;
    private String testTime;
    private String testMan;
    private String publishTime;
    private String publishMan;
    private String modTime;

    private String schemeRealTime;
    private String schemeProgress;
    private String phoneRealTime;
    private String phoneProgress;
    private String prototypeRealTime;
    private String prototypeProgress;
    private String testRealTime;
    private String testProgress;
    private String publishRealTime;
    private String publishProgress;

    private String reason;
    private Integer status;
}
