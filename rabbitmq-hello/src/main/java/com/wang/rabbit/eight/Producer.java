package com.wang.rabbit.eight;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.wang.rabbit.utis.RabbitMqUtils;

/**
 * @ClassName Producer
 * @Description
 *          死信队列
 *          生产者代码
 * @Author 19285
 * @Date 2022/8/14 17:21
 * @Version 1.0
 */
public class Producer {
    /**
     * 普通交换机名称
     */
    public static final String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        //死信消息  设置TTL时间 单位ms
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();

        for (int i = 1; i < 11; i++) {
            String message = "info" + i;
            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",properties,message.getBytes());
        }
    }
}
