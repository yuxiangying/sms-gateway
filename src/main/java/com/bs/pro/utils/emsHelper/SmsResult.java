package com.bs.pro.utils.emsHelper;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SmsResult {

    private String code;            // 返回码
    private String message;            // 返回信息
    private boolean status = false;    //返回状态 true 正确 false 错误
    private int total;                // 共几条数据
    private Long version;            //时间戳
    private int channel = -1;            //短信通道
    private String callbackId;       // 给云片阿里回调设置的回调ID,云片是uid


}
