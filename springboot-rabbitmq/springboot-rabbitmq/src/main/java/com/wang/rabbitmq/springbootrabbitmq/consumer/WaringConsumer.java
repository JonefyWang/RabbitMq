package com.wang.rabbitmq.springbootrabbitmq.consumer;

import com.wang.rabbitmq.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName WaringConsumer
 * @Description TODO
 * @Author 19285
 * @Date 2022/8/21 15:31
 * @Version 1.0
 */
@Slf4j
@Component
public class WaringConsumer {

    /**
     * 接收报警消息
     */
    @RabbitListener(queues = ConfirmConfig.WARNING_QUEUE_NAME)
    public void receiveWarningMsg(Message message){
        String msg = new String(message.getBody());
        log.error("报警发现不可路由消息：{}",msg);
    }
}
