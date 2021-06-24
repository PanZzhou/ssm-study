package com.kuang.redis02springboot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    //编写自己的RedisTemplate
    @Bean(name="MyRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        //Json序列化配置
        Jackson2JsonRedisSerializer jsjrs = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jsjrs.setObjectMapper(om);
        //String序列化
        StringRedisSerializer stringSerializer = new StringRedisSerializer();

        //key采用String的序列化方式
        template.setKeySerializer(stringSerializer);
        //hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringSerializer);
        //value的序列化方式采用jackson
        template.setValueSerializer(jsjrs);
        //hash的value序列化方式采用jackson
        template.setHashValueSerializer(jsjrs);
        template.afterPropertiesSet();



        return template;
    }
}
