package com.bs.pro.dto;

import lombok.Data;

/**
 * @ClassName:MaterialTopDto
 * @Description:
 * @Author:DN
 * @Date:2019/5/30 14:02
 **/
@Data
public class MaterialTopDto {

    //物料id
    private Integer materialId;
    //物料名称
    private String materialName;
    //物料代码
    private String materialCode;
    //金额
    private Double amount;
    //金额占比
    private Double amountRate;
    //标签
    private Integer amountTag;

    //task for a month
    private Integer monthtask;
    private Integer year;
    private Integer month;
    private Integer day;


}
