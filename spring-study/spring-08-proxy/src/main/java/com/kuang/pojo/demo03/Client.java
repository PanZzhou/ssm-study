package com.kuang.pojo.demo03;

import com.kuang.pojo.demo02.UserService;
import com.kuang.pojo.demo02.UserServiceImpl;

public class Client {
    public static void main(String[] args) {
        //真实对象
        UserServiceImpl userservice = new UserServiceImpl();
        //代理类生成中介
        ProxyInvocationHandler ph = new ProxyInvocationHandler();
        ph.setTarget(userservice);
        //获取代理对象
        UserService us = (UserService)ph.getProxy();
        //调用方法
        us.add();

    }
}
