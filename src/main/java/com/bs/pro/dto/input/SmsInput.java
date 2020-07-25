package com.bs.pro.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel
@Data
public class SmsInput implements Serializable {

    @ApiModelProperty(value = "要发送的短信完整内容", required = false)
    private String content;

    @ApiModelProperty(value = "发送手机号 +8613812345678", required = true)
    private String phone;

    @ApiModelProperty(value = "发送时间", required = true)
    private Long sendTime;

    @ApiModelProperty(value = "发送签名", required = true)
    private String sign;

    @ApiModelProperty(value = "秘钥id", required = true)
    private String appId;

    @ApiModelProperty(value = "短信模板编码", required = true)
    private String templetCode;

    @ApiModelProperty(value = "随机数", required = true)
    private String nonce;

    @ApiModelProperty(value = "用于回调统计，每次请求唯一", required = true)
    private String requestId;

}
