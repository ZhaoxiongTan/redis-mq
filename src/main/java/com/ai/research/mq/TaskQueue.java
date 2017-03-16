package com.ai.research.mq;

/**
 * TODO 添加注释
 */
public interface TaskQueue {

    /**
     * 获取队列名
     * @return
     */
    String getName();

    /**
     * 往队列中添加任务
     * @param task
     */
    void pushTask(String task);

    /**
     * 从队列中取出一个任务
     * @return
     */
    String popTask();
    
}
