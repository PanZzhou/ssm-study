package com.kuang.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {
    private String id;
    private String title;
    private String author;
    //java中使用驼峰命名法，数据库中是create_time
    //在核心配置文件中的setting标签中mapUnderscoreToCamelCase设置为true，即可自动映射为驼峰命名法
    private Date createTime;
    private int views;
}
