package com.bs.pro.dto.input;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel
@Data
public class ItemInput implements Serializable {
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

}
