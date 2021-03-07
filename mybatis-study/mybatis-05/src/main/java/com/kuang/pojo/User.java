package com.kuang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.type.Alias;

//实体类
@Alias("haha")
@Data   //lombok插件的注解，自动生成属性的getter、setter、toString等方法
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    //与数据库中的字段不一致
    private String password;
}
