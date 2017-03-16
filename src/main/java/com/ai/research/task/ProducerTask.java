package com.ai.research.task;

import com.ai.research.mq.TaskQueue;
import com.ai.research.mq.TaskQueueManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 生产任务, 放入队列中
 */
@Component
public class ProducerTask {
    
    @Resource
    private TaskQueueManager taskQueueManager;
    
    public void addTask(String task) {
        
        String smsMessageId = task;
        
        TaskQueue tq = taskQueueManager.get(TaskQueueManager.SMS_QUEUE);
        tq.pushTask(smsMessageId);
    }
}
