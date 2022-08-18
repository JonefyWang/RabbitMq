package com.wang.rabbitmq.springbootrabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName MyCallBck
 * @Description
 * 回调接口
 *          回调方法配置类
 * @Author 19285
 * @Date 2022/8/18 23:28
 * @Version 1.0
 */
@Slf4j
@Component
public class MyCallBck implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback{

    /**
     * 注入RabbitTemplate
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        //注入
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }
    /**
     * 1、发送消息  交换机接收到了 回调
     *  1.1 correlationData 保存回调消息的Id及相关消息
     *  1.2 交换机接收到消息  ack=true
     *  1.3 cause null
     * 2、发送消息  交换机接收失败了 回调
     *  2.1 correlationData 保存回调消息的Id及相关消息
     *  2.2 交换机接收到消息  ack=false
     *  2.3 cause 失败的原因
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        String id = correlationData != null ? correlationData.getId():"";
        if (ack){
            log.info("交换机接收到了Id为：{}的消息",id);
        }else {
            log.info("交换机还未接收到了Id为：{}的消息，由于原因：{}",id,cause);
        }
    }


    /**
     * 可以在当消息传递过程中不可达目的地时将消息返回给生产者。
     * 只有可能达不到目的地的时候  才进行回退
     * @param
     */
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.error("消息{}，被交换机{}退回，退回原因：{}，路由Key：{}", new String(returnedMessage.getMessage().getBody()),returnedMessage.getExchange(),returnedMessage.getReplyCode(),returnedMessage.getRoutingKey());
    }
}
