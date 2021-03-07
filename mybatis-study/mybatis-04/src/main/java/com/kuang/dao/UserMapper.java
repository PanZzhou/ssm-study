package com.kuang.dao;

import com.kuang.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    User getUserById(int id);
    //分页查询
    List<User> getUsersByLimit(Map<String, Integer> map);
    List<User> getUsersByRowBounds();
}
