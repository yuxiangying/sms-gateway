package com.bs.pro.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by czy on 2019/7/15.
 */
@Api(tags = "home", description = "home")
@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home() {

        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
