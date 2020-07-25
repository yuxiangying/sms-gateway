package com.bs.pro.utils;


import java.io.UnsupportedEncodingException;

import com.bs.pro.dto.CommonResult;
import com.bs.pro.dto.MessageInfo;
import com.bs.pro.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    public static boolean verify(MessageInfo mi) throws UnsupportedEncodingException {
        String str = java.net.URLEncoder.encode(mi.getContent() +
                mi.getToAddress()
                +
                mi.getType()
                +
                mi.getAppId()
                +
                mi.getAppKey()
                +
                mi.getSendTime()
                + "syswintoon", "utf-8");

        if (!MD5.getMD5Str(str).toLowerCase().equals(mi.getToken().toLowerCase())) {
            return false;
        }

        return true;
    }

    public static CommonResult getSuccessResult() {
        CommonResult cr = new CommonResult();
        cr.setReturnCode(ErrorCode.SUCCESS);
        cr.setReturnMessage("commit success!");
        cr.setReturnStatus(true);
        return cr;
    }

    public static Result getSucResult() {
        Result cr = new Result();
        cr.setCode(ErrorCode.SUCCESS);
        cr.setMessage("commit success!");
        return cr;
    }

}
