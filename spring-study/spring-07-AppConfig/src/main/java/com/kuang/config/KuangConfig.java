package com.kuang.config;

import com.kuang.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration //也是一个component，会被容器接管，放到容器中。代表这是一个配置类，和xml的功能是一样的，主要用来获取context上下文实例
@ComponentScan("com.kuang.pojo")  //扫描bean所在的包，这个不加也可以运行
@Import(KuangConfig2.class) //导入第二个配置类KuangConfig2
public class KuangConfig {
    @Bean  //注册一个bean，相当于bean标签；方法的名字相当于bean id属性；返回值相当于bean class属性
    public User getUser(){
        return new User();
    }

}
