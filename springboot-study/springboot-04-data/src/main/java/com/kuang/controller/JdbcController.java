package com.kuang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JdbcController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据库所有信息
    //没有实体类，数据库中的东西，怎么获取？答案是使用map
    @GetMapping("/ListInfo")
    public List<Map<String,Object>> userList(){
        String sql = "select * from user";
        List<Map<String, Object>> list_map = jdbcTemplate.queryForList(sql);
        return list_map;
    }
    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id){
        String sql = "update mybatis.user set name=?,pwd=? where id=?";
        Object[] objects = new Object[3];
        objects[0]="yangyang";
        objects[1]="123456777";
        objects[2]=id;
        jdbcTemplate.update(sql,objects);
        return "updateUser-success";
    }
}
