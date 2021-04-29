package com.kuang.springcloud.controller;

import com.kuang.springcloud.pojo.Dept;
import com.kuang.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptConsumerController {
    @Qualifier("com.kuang.springcloud.service.DeptClientService")
    @Autowired
    //改造springcloud-consumer-dept-80，使用Feign来调用服务，这样更清爽。
    private DeptClientService service = null;

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get1(@PathVariable("id") Long id){
        return service.queryById(id);
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add1(Dept dept){
        return service.addDept(dept);
    }
    @RequestMapping("/consumer/dept/list")
    public List<Dept> list1(){
        return service.queryAll();
    }
}
