package com.ai.research;

import com.ai.research.model.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisMqApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    
    @Resource
    private RedisTemplate<String, User> redisTemplate;
    
    private String REDIS_PREFIX = "TEST_KEY_";
    
    @Test
    public void contextLoads() {
    }

    @Test
    public void setGetTest() {
        String key = "test-key1";
        String value = "test-value1";
        stringRedisTemplate.opsForValue().set(key, value);
        Assert.assertEquals(value, stringRedisTemplate.opsForValue().get(key));
    }

    @Test
    public void setGetUserTest() {
        
        User user = User.UserBuilder.create()
                .withId(12345678)
                .withName("tanzx")
                .withAddress("address")
                .withPhone("18612341234")
                .withSex("男")
                .build();
        
        String key = REDIS_PREFIX + user.getId();
        
        redisTemplate.opsForValue().set(key, user);
        User userGet = redisTemplate.opsForValue().get(key);

        System.out.println("原始数据" + ToStringBuilder.reflectionToString(user));
        System.out.println("获取数据" + ToStringBuilder.reflectionToString(userGet));
        
        Assert.assertTrue(user.getId() == userGet.getId());

    }
    
}
