package com.kuang.mybatis.controller;

import com.kuang.mybatis.mapper.UserMapper;
import com.kuang.mybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUsers")
    public List<User> queryAllUser(){
        List<User> users = userMapper.queryUserList();
        return users;
    }
}
