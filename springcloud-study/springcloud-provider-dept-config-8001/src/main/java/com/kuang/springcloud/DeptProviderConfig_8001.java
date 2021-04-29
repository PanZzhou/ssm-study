package com.kuang.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

//启动类
@SpringBootApplication
@EnableEurekaClient  //在服务启动后，自动注册到Eureka中。
@EnableDiscoveryClient  //实现DiscoveryClient功能，controller中的功能
@EnableCircuitBreaker  //添加对熔断的支持,断路器
public class DeptProviderConfig_8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProviderConfig_8001.class,args);
    }
    //HystrixDashboard功能
    //增加一个Servlet
    @Bean
    public ServletRegistrationBean HystrixMetricsStreamServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        return registrationBean;
    }
}
