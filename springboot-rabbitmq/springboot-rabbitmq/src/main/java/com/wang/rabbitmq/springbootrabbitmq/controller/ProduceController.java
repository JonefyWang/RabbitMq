package com.wang.rabbitmq.springbootrabbitmq.controller;

import com.wang.rabbitmq.springbootrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName ProduceController
 * @Description
 *      发送消息 确认
 * @Author 19285
 * @Date 2022/8/18 22:56
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/confirm")
public class ProduceController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发消息
     */
    @GetMapping("/sendMessage/{message}")
    public void sendMessage(@PathVariable String message){
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,ConfirmConfig.CONFIRM_ROUTING_KEY,message);
        log.info("发送消息内容：{}",message);
    }

}
