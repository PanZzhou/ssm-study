package com.kuang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c1")
public class ControllerTest3 {



    @RequestMapping("/love") //路径成了https:/localhost:8080/c1/t1
    public String test1(Model model){
        model.addAttribute("msg","羊羊羊");
        return "test";
    }
}
