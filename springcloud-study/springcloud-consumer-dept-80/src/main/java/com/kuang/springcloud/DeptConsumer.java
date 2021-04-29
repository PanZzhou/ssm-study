package com.kuang.springcloud;

import com.kuang.myrule.KuangRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT",configuration = KuangRule.class)
public class DeptConsumer {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer.class,args);
    }
}
