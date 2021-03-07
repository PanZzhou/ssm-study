package com.kuang.pojo.demo01;

public class Host implements Rent{
    public Host() {
        System.out.println("房东打算出租房子");
    }

    @Override
    public void rent() {
        System.out.println("房东把房子托管给中介");
    }
}
