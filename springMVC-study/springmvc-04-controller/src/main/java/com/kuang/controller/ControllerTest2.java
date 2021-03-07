package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerTest2 {
    @RequestMapping("/t2")
    public String test2(Model model){
        model.addAttribute("msg","@Controller");
        return "test";
    }

    @RequestMapping("/love")
    public String test3(Model model){
        model.addAttribute("msg","杨红焰love   ");
        return "test";
    }
}
