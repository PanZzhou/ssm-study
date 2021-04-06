package com.kuang.controller;

import com.kuang.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    //只要接口中返回值中存在实体类，他就会被扫描到swagger中
    @PostMapping("/user")
    public User user(){
        return new User();
    }

    @PostMapping("/helllo")
    @ApiOperation("helllo控制类")
    public String helllo(@ApiParam("用户名") String username){
        return "hello"+username;
    }
}
