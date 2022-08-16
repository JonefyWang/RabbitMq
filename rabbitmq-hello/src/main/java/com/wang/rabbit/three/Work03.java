package com.wang.rabbit.three;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wang.rabbit.utis.RabbitMqUtils;
import com.wang.rabbit.utis.SleepUtils;

/**
 * @ClassName Work03
 * @Description TODO
 * @Author 19285
 * @Date 2022/8/10 23:40
 * @Version 1.0
 */
public class Work03 {
    private static final String ACK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("C1 等待接收消息处理时间较短");
        //消息消费的时候如何处理消息
        DeliverCallback deliverCallback=(consumerTag,delivery) ->
        {String message= new String(delivery.getBody());
        SleepUtils.sleep(1);
        System.out.println("接收到消息:"+message);
        /**
         * 1.消息标记 tag
         * 2.是否批量应答未应答消息  不批量应答信道中的消息  true：批量
         */
        channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        };
        //设置不公平分发
        //int prefetchCount = 1;
        //设置预选值
        int prefetchCount = 2;
        channel.basicQos(prefetchCount);
        //采用手动应答
        boolean autoAck=false;
        channel.basicConsume(ACK_QUEUE_NAME,autoAck,deliverCallback,(consumerTag)->{
            System.out.println(consumerTag+"消费者取消消费接口回调逻辑");
        });

    }
}
