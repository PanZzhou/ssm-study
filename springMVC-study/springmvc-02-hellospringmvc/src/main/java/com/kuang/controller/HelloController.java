package com.kuang.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        //模型和视图
        ModelAndView modelAndView = new ModelAndView();
        //封装对象，放在modelAndView中。Model
        modelAndView.addObject("msg","HelloStringMVC");
        //封装要跳转的视图，放在modelAndView中
        modelAndView.setViewName("hello"); //  /WEB-INF/jsp/hello.jsp  spring会自动合成这个视图路径
        return modelAndView;
    }
}
