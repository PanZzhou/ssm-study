package com.kuang.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;


//在template目录下的文件需要Controller跳转
//这个需要模板引擎来支持，Thymeleaf
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("msg","Hello Thymeleaf");
        model.addAttribute("users", Arrays.asList("qaaaa","pappaap","yayaya"));
        return "test";
    }
}
