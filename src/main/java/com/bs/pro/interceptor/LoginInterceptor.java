package com.bs.pro.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by czy on 2019/7/15.
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String userCode = (String)session.getAttribute("usercode");
        if (StringUtils.isEmpty(userCode)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }

}
