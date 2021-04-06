package com.kuang;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Springboot10RedisApplicationTests {

    @Autowired
    @Qualifier("myRedisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        //操作String
        redisTemplate.opsForValue().set("mykey","羊羊羊");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
        redisTemplate.opsForValue().set("哈哈哈","羊羊羊");
        System.out.println(redisTemplate.opsForValue().get("哈哈哈"));
    }

}
