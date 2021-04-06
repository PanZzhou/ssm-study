package com.kuang.springboot04mannageproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private int id;
    private String departmentName;

    public int getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
