package com.kuang;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {
    //继承了BaseMapper，所有的方法都来自自己的父类，我们也可以编写自己的扩展方法
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
    @Test
    public void testInsert(){
        User user = new User();
        user.setAge(12);
        user.setName("panpan");
        user.setEmail("hahaha");
        userMapper.insert(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(2L);
        user.setName("panpan");
        user.setAge(22);

        userMapper.updateById(user);
    }
    //乐观锁测试成功
    @Test
    public void testOptimisticLocker(){
        User user = userMapper.selectById(2L);
        user.setName("zhoupan");
        user.setEmail("fjdksjk");
        userMapper.updateById(user);
    }
    @Test
    public void testSelect(){
        //单个查询
        User user = userMapper.selectById(1L);
        System.out.println(user);
        //批量查询
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
        //条件查询之一，使用map
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","panpan");
        userMapper.selectByMap(map);
    }
    @Test
    public void testPage(){
        //参数一：当前页
        //参数二：页面大小
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getSize());
    }
    @Test
    public void testDelete(){
        //id删除
        userMapper.deleteById(1404648530473099267L);
        //id批量删除
        userMapper.deleteBatchIds(Arrays.asList(1404648530473099266L,1404648530473099265L));
        //条件删除
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","panpan");
        userMapper.deleteByMap(map);
    }
    @Test
    public void testLogicDelete(){
        userMapper.deleteById(8L);
    }
}
