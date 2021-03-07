package com.kuang.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//只要实现了Controller接口，说明这是一个控制器了
//此种方法落后，不推荐使用
//一个类只能写一个方法，方法很多的话，会导致需要新建的页面很多
public class ControllerTest1 implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","ControllerTest implements Controller");
        modelAndView.setViewName("test");
        return modelAndView;
    }
}
