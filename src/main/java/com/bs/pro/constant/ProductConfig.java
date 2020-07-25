package com.bs.pro.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "product-service")
@Data
public class ProductConfig {
    private String url;
    private String port;
}
