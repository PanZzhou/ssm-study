package com.kuang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuang.pojo.User;
import org.springframework.stereotype.Repository;

//再对应的Mapper上面集成基本的类BaseMapper
@Repository//代表持久层
public interface UserMapper extends BaseMapper<User> {
    //所有的CRUD操作都已经完成了，不需要再写xml文件
}
