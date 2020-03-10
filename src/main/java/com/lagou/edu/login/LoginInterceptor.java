package com.lagou.edu.login;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(LoginSession.getLoginSession().m.containsKey("admin")){

            return true;
        }else {
            response.sendRedirect("/resume/loginPage");
            return false;
        }
    }
}
