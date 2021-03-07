package com.kuang.service;

import com.kuang.dao.UserDao;
import com.kuang.dao.UserDaoImpl;
import com.kuang.dao.UserDaoMysqlImpl;

public class UserServiceImpl implements  UserService{
    //private UserDao userDao = new UserDaoImpl();
    //private UserDao userDao = new UserDaoOracleImpl();
    //用户要用Mysql的功能，就必须在这里改动程序
    //private UserDao userDao = new UserDaoMysqlImpl();
    private UserDao userDao;
    @Override
    public void getUser() {
        userDao.getUser();
    }

    //为了解决上述弊端，可以增加set函数来动态配置UserDao
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
}
