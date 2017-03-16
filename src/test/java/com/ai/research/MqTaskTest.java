package com.ai.research;

import com.ai.research.task.ConsumerTask;
import com.ai.research.task.ProducerTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 任务生产和消费测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqTaskTest {
    
    @Resource
    private ProducerTask producerTask;
    
    @Resource
    private ConsumerTask consumerTask;
    
    @Test
    public void producerTaskTest() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            producerTask.addTask("测试任务-" + i);
            
            Thread.sleep(2000);
        }
    }
    
    @Test
    public void consumerTaskTest() {
        consumerTask.execute();
        
    }
    
}
