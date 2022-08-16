package com.wang.rabbit.two;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wang.rabbit.utis.RabbitMqUtils;

/**
 * @ClassName Worker01
 * @Description TODO
 * @Author 19285
 * @Date 2022/8/10 22:38
 * @Version 1.0
 */
public class Worker01 {
    private static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        DeliverCallback deliverCallback = (consumerTag,delivery) -> {
          String reciveMessage = new String(delivery.getBody());
          System.out.println("接收到的消息：" + reciveMessage);
        };
        CancelCallback cancelCallback = (consumerTag) ->{
            System.out.println(consumerTag + "消费者取消消费接口回调逻辑");
        };
        System.out.println("C1 消费者启动等待消费.................. ");
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }

}
