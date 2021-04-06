package com.kuang.service;

import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
