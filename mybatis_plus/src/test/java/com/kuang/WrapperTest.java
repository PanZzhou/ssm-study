package com.kuang;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //查询name不为空的用户，并且邮箱不为空，年龄大于等于12
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age",19);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }
    @Test
    void test2() {
        //查询name为panpan的其中一个数据
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","panpan");
        //查询一个数据。要查询多个数据时使用LIst或者Map
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }
    @Test
    void test3() {
        //查询20岁到30岁之间的用户数
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",20,30);
        Integer i = userMapper.selectCount(wrapper);//查询结果数
        System.out.println(i);
    }
    @Test
    void test4(){
        //模糊查询，查询名字中不包含z的用户，邮箱中以t开头
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.notLike("name","z")
                .likeRight("email","t");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }
    @Test
    void test5(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //id在子查询中查出来,内敛查询
        wrapper.inSql("id","select id from user where id<3");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }
    @Test
    void test6(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //通过id进行排序
        wrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
