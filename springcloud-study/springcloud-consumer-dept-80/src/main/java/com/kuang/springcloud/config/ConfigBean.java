package com.kuang.springcloud.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    //配置负载均衡
    @LoadBalanced  //开启Ribbon的负载均衡
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
