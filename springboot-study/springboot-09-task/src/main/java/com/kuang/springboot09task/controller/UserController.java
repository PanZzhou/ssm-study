package com.kuang.springboot09task.controller;

import com.kuang.springboot09task.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    AsyncService userService;

    @RequestMapping("/service")
    public String proce(){
        userService.doService();//停止三秒
        return "Ok";
    }
}
