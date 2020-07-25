package com.bs.pro.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by czy on 2019/4/3.
 */
@Service
@Scope("singleton")
//@ConfigurationProperties
@RefreshScope
public class ProProperties {

    @Value("${app.keys}")
    private String keys;

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

}
