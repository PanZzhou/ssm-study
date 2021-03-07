package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController{
    @RequestMapping("/hello") //网路请求过来 https://localhost:8080/hello请求
    public String hello(Model model){
        //封装数据, Model可以在jsp中取出并渲染
        model.addAttribute("msg","Annotation Develop springMVC");
        return "hello";//视图的名字，会被视图解析器解析为具体路径 即跳转到/WEB-INF/jsp/hello.jsp页面
    }

}
