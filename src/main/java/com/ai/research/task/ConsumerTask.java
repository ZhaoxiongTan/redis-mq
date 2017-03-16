package com.ai.research.task;

import com.ai.research.mq.TaskQueue;
import com.ai.research.mq.TaskQueueManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * TODO 添加注释
 */
@Component
public class ConsumerTask {

    @Resource
    private TaskQueueManager taskQueueManager;
    
    private static Logger logger = LoggerFactory.getLogger(ConsumerTask.class);
    
    /**
     * 入口方法。
     */
    public void execute()  {
        TaskQueue taskQueue = null;
//        String task = null;
        try {
            taskQueue = taskQueueManager.get(TaskQueueManager.SMS_QUEUE);

            // 非线程安全
//            Set<Serializable> executedTaskSet = new HashSet<Serializable>();

//            task = taskQueue.popTask();
            
            while(true) {
                String task = taskQueue.popTask();
                
                if(task != null) {
                    executeSingleTask(taskQueue,task);
                } else {
                    Thread.sleep(5000);
                }
            }
//            while(task!=null){
//                // 判断是否把所有任务都执行一遍了，避免死循环
////                if(executedTaskSet.contains(task)){
////                    taskQueue.pushTask(task);
////                    break;
////                }
//
//                executeSingleTask(taskQueue,task);
//
//                task = taskQueue.popTask();
//            }
        }catch(Throwable e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
    }


    /**
     * 发送单条短信。
     *
     * 取出任务并执行，如果失败，放回任务列表。
     *
     * @param taskQueue
     * @param task
     */
    private void executeSingleTask(TaskQueue taskQueue, String task) {
        try {
            // do the job
            String smsId = task;
            
            logger.debug("执行任务: {} - {}", taskQueue.getName(), task);
            
//            Map<String,String> sms = new HashMap<>() ;
//            sMap<String,String> sms = smsSendService.getSmsList(smsId);
//            smsSendService.send(sms);
//            smsSendService.updateSmsStatus(task,SmsSendService.STATUS_SENT);

//            String opType = "2";
//            TaskQueueUtil.taskLog(taskQueue.getName(), opType, task);
        } catch (Throwable e) {
            if(task!=null){
                taskQueue.pushTask(task);
//                smsSendService.updateSmsStatus(task,SmsSendService.STATUS_WAIT);
                if(logger.isDebugEnabled()){
                    logger.error("任务{}执行失败：{}，重新放回队列", task, e.getMessage());
                }
            }else {
                e.printStackTrace();
            }
        }
    }
}
