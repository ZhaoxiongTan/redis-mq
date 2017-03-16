package com.ai.research.mq;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 缓存管理类
 */
@Component
public class RedisManager {
    
    @Resource
    private RedisTemplate redisTemplate;
    
    
}
