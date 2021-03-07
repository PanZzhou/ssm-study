package com.kuang.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

//注解测试，使用Autowired之后就可以省略set方法。
public class People {
    @Autowired //默认按照类型来装配，并非按名字;按照类型找不到之后才再按照名字找。
    @Qualifier(value = "cat1")  //配置文件中配置了两个Cat类，并且两个bean的id都不是cat(一个是cat1，一个是cat11)，此时根据类型还是名字都匹配不到bean
                        //这时就会报错。解决方法是使用@Qualifier注解，人为地给它指定一个合适的bean。
    //@Resource(name="cat1")   //这个注解功能和上面两个组合起来的功能是一样的，只是这是java自带的注解，默认用byName的方式查询bean
    private Cat cat;
    @Autowired(required = false) //定义了required为false，说明这个对象可以为null，否则不允许为空。
    private Dog dog;
    private String name;

    public Cat getCat() {
        return cat;
    }

    public Dog getDog() {
        return dog;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}
