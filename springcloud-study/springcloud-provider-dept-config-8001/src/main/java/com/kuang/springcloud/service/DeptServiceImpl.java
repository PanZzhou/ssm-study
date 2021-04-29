package com.kuang.springcloud.service;

import com.kuang.springcloud.mapper.DeptMapper;
import com.kuang.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    public boolean addDept(Dept dept) {
        return deptMapper.addDept(dept);
    }

    public Dept queryDeptById(Long id) {
        return deptMapper.queryDeptById(id);
    }

    public List<Dept> queryAll() {
        return deptMapper.queryAll();
    }
}
