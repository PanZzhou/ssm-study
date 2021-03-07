package com.kuang.pojo.demo02;

public class MyTest {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy();
        proxy.setUserservice(userService);
        proxy.delete();
    }
}
