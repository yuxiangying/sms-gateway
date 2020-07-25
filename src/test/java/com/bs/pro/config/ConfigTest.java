//package com.syswin.sms.config;
//
//import com.alibaba.fastjson.JSONObject;
//import SmsConstant;
//import com.syswin.sms.properties.PrivateKeyProperties;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * Created by czy on 2019/4/4.
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ConfigTest {
//
//    @Autowired
//    PrivateKeyProperties privateKeyProperties;
//
//    @Test
//    public void privateKeyTest() {
//        Map<String, String> result = new HashMap<>();
//        Map<String, JSONObject> configMap = JSONObject.parseObject(privateKeyProperties.getPrivatekey(), Map.class);
//        System.out.println(configMap.get(SmsConstant.YUNPIAN_KEY));
//        assertThat(configMap.size()).isGreaterThan(0);
//    }
//}
