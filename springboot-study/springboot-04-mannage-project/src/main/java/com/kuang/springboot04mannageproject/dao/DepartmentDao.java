package com.kuang.springboot04mannageproject.dao;

import com.kuang.springboot04mannageproject.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//部门数据
@Repository
public class DepartmentDao {
    //模拟数据库中的数据
    private static Map<Integer, Department> departments = null;
    static {
        departments = new HashMap<>();
        departments.put(101,new Department(101,"教学部"));
        departments.put(102,new Department(102,"总裁办"));
        departments.put(103,new Department(103,"研发部"));
        departments.put(104,new Department(104,"运营部"));
        departments.put(105,new Department(105,"后勤部"));
    }
    //获得所有部门信息
    public Collection<Department> getDepartments(){
        return departments.values();
    }

    //通过ID得到部门
    public Department getDepartMentById(int id){
        return departments.get(id);
    }
}
