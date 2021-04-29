package com.kuang.springcloud.service;

import com.kuang.springcloud.pojo.Dept;

import java.util.List;

public interface DeptService {
    boolean addDept(Dept dept);

    Dept queryDeptById(Long id);

    List<Dept> queryAll();
}
