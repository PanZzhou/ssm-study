package com.kuang.springboot04mannageproject.controller;

import com.kuang.springboot04mannageproject.dao.DepartmentDao;
import com.kuang.springboot04mannageproject.dao.EmployeeDao;
import com.kuang.springboot04mannageproject.pojo.Department;
import com.kuang.springboot04mannageproject.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list.html";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        employeeDao.save(employee);

        return "redirect:/emps";
    }
    //去员工修改页面
    @GetMapping("/edit")
    public String toUpdatePage(int id,Model model){
        Employee employee = employeeDao.getEmById(id);
        model.addAttribute("emp",employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/update";
    }
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    @GetMapping("/delete")
    public String deleteEmp(int id){
        employeeDao.remove(id);
        return "redirect:/emps";
    }
}
