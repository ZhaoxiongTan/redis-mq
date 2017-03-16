package com.ai.research.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 任务队列Redis实现。
 */
public class TaskQueueRedisImpl implements TaskQueue {

    private StringRedisTemplate stringRedisTemplate;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private final String name;

    public TaskQueueRedisImpl(String name) {
        this.name = name;
    }
        
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void pushTask(String task) {
        //config lpush task
        logger.debug("redis任务队列{}, 新增任务: {}", this.name, task);
        stringRedisTemplate.opsForList().leftPush(this.name, task);
    }

    @Override
    public String popTask() {
        //config rpop task
        String task = stringRedisTemplate.opsForList().rightPop(this.name);
        logger.debug("redis任务队列{}, 获取任务: {}", this.name, task);
        return task;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
}
