package com.wang.rabbit.serve;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wang.rabbit.utis.RabbitMqUtils;

/**
 * @ClassName ReceiveLogsTopic01
 * @Description TODO
 * @Author 19285
 * @Date 2022/8/14 15:35
 * @Version 1.0
 */
public class ReceiveLogsTopic01 {
    /**
     * 交换机名称
     */
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
        //声明队列
        String queueName = "Q!";
        channel.queueDeclare(queueName,false,false,false,null);
        channel.queueBind(queueName,EXCHANGE_NAME,"*.orange.*");
        System.out.println("ReceiveLogsTopic01等待接收消息。。。");
        DeliverCallback deliverCallback = (consumerTage,message) -> {
            System.out.println(new String(message.getBody(),"UTF-8"));
            System.out.println("接收队列：" + queueName + "绑定键：" + message.getEnvelope().getRoutingKey());
        };
        //接收消息
        channel.basicConsume(queueName,true,deliverCallback,consumerTag->{});
    }
}
