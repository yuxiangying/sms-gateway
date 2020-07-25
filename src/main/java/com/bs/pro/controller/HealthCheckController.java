package com.bs.pro.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查接口
 *
 * @author: csj
 * @date: 2018年7月12日 下午3:39:16
 */
@Api(tags = "health check", description = "健康检查")
@RestController
public class HealthCheckController {


    @ApiOperation(value = "健康检查接口", response = String.class, consumes = "application/json", httpMethod = "GET")
    @GetMapping("/healthchk")
    public String healthCheck() {
        return "health";
    }
}
