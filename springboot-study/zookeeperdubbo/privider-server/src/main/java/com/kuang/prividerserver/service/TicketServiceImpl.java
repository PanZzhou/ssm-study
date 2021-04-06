package com.kuang.prividerserver.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

//zookeeper:服务注册与发现
@Component  //为了与Dubbo中的Service注解区分，这里不用spring的Service注解，替代的用Component注解。
@Service  //这个是Dubbo的注解，与spring的service长得一样
public class TicketServiceImpl implements TicketService{
    @Override
    public String getTicket() {
        return "这个是服务提供者提供的getTicket服务";
    }
}
