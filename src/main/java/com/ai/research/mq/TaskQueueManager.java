package com.ai.research.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 *  // 获得队列
 *  TaskQueue tq = TaskQueueManager.get(TaskQueueManager.SMS_QUEUE);
 *
 *  // 添加任务到队列
 *  String task = "task id";
 *  tq.pushTask(task);
 *
 *  // 从队列中取出任务执行
 *  String taskToDo = tq.popTask();
 * </pre>
 */
@Component
public class TaskQueueManager {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    
    private Logger logger = LoggerFactory.getLogger(TaskQueueManager.class);

    private Map<String, TaskQueueRedisImpl> queueMap = new ConcurrentHashMap<>();

    /**
     * 短信队列名。
     */
    public static final String SMS_QUEUE = "SMS_QUEUE";

    /**
     * 规则队列名。
     */
    public static final String RULE_QUEUE = "RULE_QUEUE";

    @PostConstruct
    private void initQueueMap() {
        logger.debug("初始化任务队列...");

        TaskQueueRedisImpl ruleQueueTask = new TaskQueueRedisImpl(RULE_QUEUE);
        ruleQueueTask.setStringRedisTemplate(stringRedisTemplate);
        queueMap.put(RULE_QUEUE, ruleQueueTask);
        logger.debug("建立队列："+RULE_QUEUE);

        TaskQueueRedisImpl smsQueueTask = new TaskQueueRedisImpl(RULE_QUEUE);
        smsQueueTask.setStringRedisTemplate(stringRedisTemplate);
        queueMap.put(SMS_QUEUE, smsQueueTask);
        logger.debug("建立队列："+SMS_QUEUE);
    }

    public TaskQueue get(String name){
        return getRedisTaskQueue(name);
    }

    private TaskQueue getRedisTaskQueue(String name){
        return queueMap.get(name);
    }
}
