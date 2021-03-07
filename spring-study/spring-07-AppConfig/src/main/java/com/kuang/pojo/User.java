package com.kuang.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class User {
    @Value("小明")
    private String name;

    public String getName() {
        return name;
    }
    @Value("小红")  //setName设置优先于属性上设置，故name属性值是小红而不是小明。
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
