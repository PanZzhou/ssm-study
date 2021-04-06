package com.kuang.springboot04mannageproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    //这里的作用和config/MyMvcConfig类中的addViewControllers方法实现的功能一致。
//    @RequestMapping({"/","/index.html"})
//    public String index(){
//        return "index";
//    }
}
