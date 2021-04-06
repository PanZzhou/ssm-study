package com.kuang.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")//绑定配置
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean
    //后台监控功能
    public ServletRegistrationBean statViewServlet(){
        //访问localhost:8080/druid时就会自动调到内置的监控页面！
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        //后台需要有人登录，账号密码
        HashMap<String,String> initParaments = new HashMap<>();
        //增加配置
        initParaments.put("loginUsername","admin");//登录key，是固定的loginUsername
        initParaments.put("loginPassword","123456");//登录key，loginPassword
        //允许谁可以访问
        initParaments.put("allow","localhost");//允许本机访问，"localhost"改为""就是所有人能访问
        //禁止谁访问
//        initParaments.put("deny","172.31.23.45");

        bean.setInitParameters(initParaments);//设置初始化参数
        return bean;
    }

    @Bean
    //filter
    public FilterRegistrationBean f(){
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean(new WebStatFilter());

        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("exclusions","*.js,*.css,/druid/*");//这些东西不进行统计。
        bean.setInitParameters(initParameters);
        return bean;
    }
}
