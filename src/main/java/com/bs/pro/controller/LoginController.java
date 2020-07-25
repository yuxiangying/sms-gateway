package com.bs.pro.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "login", description = "login")
@Controller
@Slf4j
public class LoginController {

    @RequestMapping("/login/verify")
    public ModelAndView loginVerify(String usercode,String password) {
        ModelAndView modelAndView = new ModelAndView();
        if ("admin".equals(usercode)&&"bs2020".equals(password)) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpSession httpSession = servletRequestAttributes.getRequest().getSession();
            httpSession.setAttribute("usercode", usercode);
            modelAndView.setViewName("item");
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("msg", "用户名或密码错误!");
        }
        return  modelAndView;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession httpSession = servletRequestAttributes.getRequest().getSession();
        httpSession.removeAttribute("usercode");
        return "login";
    }

}
