package com.kuang.mybatis.mapper;

import com.kuang.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper  //这个注解表示是mybatis的mapper接口
@Repository  //这个是spring的注解
public interface UserMapper {

    List<User> queryUserList();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
