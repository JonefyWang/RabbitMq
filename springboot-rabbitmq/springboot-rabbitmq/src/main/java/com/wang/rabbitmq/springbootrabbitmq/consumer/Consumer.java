package com.wang.rabbitmq.springbootrabbitmq.consumer;

import com.wang.rabbitmq.springbootrabbitmq.config.ConfirmConfig;
import com.wang.rabbitmq.springbootrabbitmq.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName Consumer
 * @Description TODO
 * @Author 19285
 * @Date 2022/8/18 23:13
 * @Version 1.0
 */
@Slf4j
@Component
public class Consumer {
    /**
     * 监听消息
     */
    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE_NAME)
    public void receiveDelayQueue(Message message){
        String msg = new String(message.getBody());
        log.info("接收到的队列confirm.queue消息：{}",msg);
    }
}
