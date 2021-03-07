package com.kuang.pojo.demo01;

//静态代理模式例子：代理对象除了要做被代理对象的操作外，还要做额外的操作。
public class Proxy implements Rent{
    //代理房东的事情
    private Host host;

    public Proxy(Host host) {
        this.host = host;
    }

    @Override
    public void rent() {
        host.rent();
        showHouse();
        doContract();
    }
    //签合同
    public void doContract(){
        System.out.println("中介和客户签合同");
    }
    //看房
    public void showHouse(){
        System.out.println("中介带客户看房");
    }
}
