package com.kuang.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//等价于<bean id="user" class="com.kuang.pojo.User"/>，配置文件中定义了自动扫描的包，之后会自动装配
@Component
@Scope("prototype")  //表示这个bean是原型而非单例。
public class User {
    public String name = "张三";
    @Value("18")  //相当<property name="age" value="18">，从而给age属性注入“18”的值。
    public String age;
}
