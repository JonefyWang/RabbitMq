package com.wang.rabbit.eight;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wang.rabbit.utis.RabbitMqUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Consumer01
 * @Description
 *      死信队列实战
 *      消费者2
 * @Author 19285
 * @Date 2022/8/14 16:40
 * @Version 1.0
 */
public class Consumer02 {
    /**
     * 普通交换机名称
     */
    public static final String NORMAL_EXCHANGE = "normal_exchange";

    /**
     * 死信交换机名称
     */
    public static final String DEAD_EXCHANGE = "dead_exchange";

    /**
     * 普通队列名称
     */
    public static final String NORMAL_QUEUE = "normal_queue";

    /**
     * 死信队列名称
     */
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("等待接收消息。。。。。。。。。。。");

        //接收消息
        DeliverCallback deliverCallback = (consumerTag,message)->{
            System.out.println("Consumer02接收的消息是：" + new String(message.getBody(),"UTF-8"));
        };

        channel.basicConsume(DEAD_QUEUE,true,deliverCallback,consumerTag->{});
    }
}
