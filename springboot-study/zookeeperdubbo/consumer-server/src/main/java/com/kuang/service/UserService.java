package com.kuang.service;

import com.kuang.prividerserver.service.TicketService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service  //这里是spring的注解，表示放到spring容器中
public class UserService {

    @Reference
    TicketService ticketService;

    public void buyTicket(){
        String ticket = ticketService.getTicket();
        System.out.println("在注册中心拿到"+ticket);
    }
}
