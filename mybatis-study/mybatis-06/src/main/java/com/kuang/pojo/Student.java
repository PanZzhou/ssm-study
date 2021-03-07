package com.kuang.pojo;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;

    //学生需要关联一个老师,数据库里保存老师id作为外键，而实体类保存teacher对象，需要在xml文件中做映射
    private Teacher teacher;
}
