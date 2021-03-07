package com.kuang.pojo.demo01;

public class Client {
    public static void main(String[] args) {
        //房东要出租房子
        Host host = new Host();
        //代理角色托管房东房子
        Proxy proxy = new Proxy(host);
        //客户和代理商讨租房事宜
        proxy.rent();
    }
}
