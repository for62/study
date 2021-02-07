package com.study.redis;

import com.alibaba.fastjson.JSONObject;
import com.study.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;


    @Test
    void contextLoads() {
    }

    @Test
    void testRedis() {
        User user = new User("for62", "123456");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", user);
        redisTemplate.opsForValue().set("user", jsonObject.toJSONString());
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

}
