package com.kuang.pojo.service;

public class UserServiceImpl implements UserService{

    @Override
    public void add() {
        System.out.println("add方法");
    }

    @Override
    public void delete() {
        System.out.println("delete方法");
    }

    @Override
    public void update() {
        System.out.println("update方法");
    }

    @Override
    public void query() {
        System.out.println("query方法");
    }
}
