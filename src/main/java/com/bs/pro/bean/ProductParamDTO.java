package com.bs.pro.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductParamDTO implements Serializable {
    private static final long serialVersionUID = 8128317983497839293L;

    //日期字符串
    private String dateStr;

    //任务单数
    private int orderNum;

    //金额
    private BigDecimal amount;
}
