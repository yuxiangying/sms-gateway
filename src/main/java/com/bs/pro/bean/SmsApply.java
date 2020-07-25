package com.bs.pro.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * @author auto create
 * @since 1.0, 2019-07-08 16:26:55
 */
@Data
public class SmsApply implements Serializable {
    private static final long serialVersionUID = 5845292660196965L;

    private Integer id;

    private String appSecret;//秘钥

    private String appId;//秘钥id,标识短信调用方身份,短信网关生成

    private String appName;//应用名称,如apply

    private String appPrivate;//saas或私有标志，如7niu

    private String description;//说明

    private Integer status;  //状态 -1停用 1启用

}
