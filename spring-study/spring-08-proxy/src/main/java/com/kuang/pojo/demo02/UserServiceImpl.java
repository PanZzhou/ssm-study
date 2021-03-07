package com.kuang.pojo.demo02;

public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("UserService add 方法");
    }

    @Override
    public void delete() {
        System.out.println("UserService delete 方法");
    }

    @Override
    public void update() {
        System.out.println("UserService update 方法");
    }

    @Override
    public void search() {
        System.out.println("UserService search 方法");
    }
    //改动原有的代码，是项目开发过程中的大忌
}
