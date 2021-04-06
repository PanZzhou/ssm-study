package com.kuang.springboot04mannageproject.dao;

import com.kuang.springboot04mannageproject.pojo.Department;
import com.kuang.springboot04mannageproject.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    //模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;
    @Autowired
    DepartmentDao departmentDao;
    static {
        employees = new HashMap<>();
        employees.put(1001,new Employee(1001,"焱焱","3243244124@qq.com",1,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"燕燕","6547636536@qq.com",2,new Department(102,"总裁办")));
        employees.put(1003,new Employee(1003,"丫丫","6543652511@qq.com",1,new Department(103,"研发部")));
        employees.put(1004,new Employee(1004,"嘎嘎","22fw323434@qq.com",2,new Department(104,"运营部")));
        employees.put(1005,new Employee(1005,"红红","223vf23434@qq.com",1,new Department(105,"后勤部")));
    }

    //主键自增
    private static Integer initId = 1006;
    //增加一个员工
    public void save(Employee employee){
        if(employee.getId()==null)
            employee.setId(initId++);
        employee.setDepartment(departmentDao.getDepartMentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }
    //查询全部员工
    public Collection<Employee> getAll(){
        return employees.values();
    }
    //根据Id查询员工
    public Employee getEmById(Integer id){
        return employees.get(id);
    }
    //删除员工
    public void remove(Integer id){
        employees.remove(id);
    }
}
