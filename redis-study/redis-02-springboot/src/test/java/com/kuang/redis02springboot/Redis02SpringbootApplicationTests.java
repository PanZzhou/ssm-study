package com.kuang.redis02springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuang.redis02springboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class Redis02SpringbootApplicationTests {
    @Autowired
    @Qualifier(value="MyRedisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        //RedisTemplate 操作不同的数据类型，api和我们的指令是一样的
        //opsForValue() 操作字符串，类似String
        //opsForList() 操作列表，list
        //。。。
        //opsForHyperLogLog()

        //除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作，比如事务，CRUD，连接
//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushDb();
//        connection.flushAll();

        redisTemplate.opsForValue().set("mykey","潘潘潘");
        System.out.print(redisTemplate.opsForValue().get("mykey"));
    }

    @Test
    void test() throws JsonProcessingException {
        User user = new User("panpan", 20);
        //springboot自带的序列化工具
//        String json = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

}
