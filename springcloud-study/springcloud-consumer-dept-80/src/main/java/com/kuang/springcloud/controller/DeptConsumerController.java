package com.kuang.springcloud.controller;

import com.kuang.springcloud.pojo.Dept;
import com.kuang.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {
    //理解：消费之，不应该有service层
    //RestTemplate  供我们直接调用就可以了，注册到Spring中
    //(url,实体：map，Class<T> responseType)
    @Autowired
    private RestTemplate restTemplate;

    //我们这里的地址，应该是一个变量，通过服务名来访问。
//    private String URL = "http://localhost:8001";
    private String URL = "http://SPRINGCLOUD-PROVIDER-DEPT";  //Ribbon方法，通过写死服务名来拿


    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(URL+"/dept/get/"+id,Dept.class);
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(URL+"/dept/add/",dept,boolean.class);
    }
    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(URL+"/dept/list",List.class);
    }
}
