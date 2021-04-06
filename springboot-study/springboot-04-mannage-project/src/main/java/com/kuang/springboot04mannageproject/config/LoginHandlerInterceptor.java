package com.kuang.springboot04mannageproject.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//拦截器
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //登录成功后,应该在session中有用户信息
        Object loginUser = request.getSession().getAttribute("loginUser");

        if(loginUser==null){//没有登陆
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("index.html").forward(request,response);
            return false;
        }else{
            return true;
        }
    }
}
