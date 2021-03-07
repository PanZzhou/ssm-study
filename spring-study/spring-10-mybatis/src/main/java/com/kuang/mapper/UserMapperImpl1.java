package com.kuang.mapper;

import com.kuang.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl1 extends SqlSessionDaoSupport implements UserMapper{

    @Override
    public List<User> getUsers() {
        //getSqlSession()方法是SqlSessionDaoSupport中的内容
        return getSqlSession().getMapper(UserMapper.class).getUsers();
    }
}
