package com.wang.rabbitmq.springbootrabbitmq.consumer;

import com.wang.rabbitmq.springbootrabbitmq.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName DelayQueueConsumer
 * @Description
 *          消费者  基于插件的的延迟消息
 * @Author 19285
 * @Date 2022/8/17 23:25
 * @Version 1.0
 */
@Slf4j
@Component
public class DelayQueueConsumer {
    /**
     * 监听消息
     */
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void receiveDelayQueue(Message message){
        String msg = new String(message.getBody());
        log.info("当前时间：{}，收到延迟队列消息：{}",new Date().toString(),msg);
    }
}
