package com.kuang.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuang.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping(value="/j1")
    public String test() throws JsonProcessingException {
        User user = new User("杨红焰", 2, "男");
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(user);
        return s;
    }

    @RequestMapping(value="/fast")
    public String test1() throws JsonProcessingException {
        User user = new User("杨红焰", 2, "男");
        String s = JSON.toJSONString(user);
        return s;
    }
}
