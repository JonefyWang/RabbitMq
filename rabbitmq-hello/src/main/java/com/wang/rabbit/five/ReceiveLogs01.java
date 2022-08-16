package com.wang.rabbit.five;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wang.rabbit.utis.RabbitMqUtils;

/**
 * @ClassName ReceiveLogs01
 * @Description
 *         消息接收
 * @Author 19285
 * @Date 2022/8/13 16:45
 * @Version 1.0
 */
public class ReceiveLogs01 {
    /**
     * 交换机名称
     */
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        /**
         * 声明一个临时的队列
         * 当消费者断开与队列连接时，队列自动删除
         */
        String queueName = channel.queueDeclare().getQueue();
        /**
         * 绑定交换机与队列
         * bind
         */
        channel.queueBind(queueName,EXCHANGE_NAME,"");
        System.out.println("等待接收消息，把接受的消息打印到屏幕上。。。。。");

        //接收消息
        DeliverCallback deliverCallback = (counsumerTag, message) ->{
            System.out.println("ReceiveLogs01控制台打印接收到的消息：" + new String(message.getBody(),"UTF-8"));
        };
        //消费者取消消息时回调接口
        channel.basicConsume(queueName,true,deliverCallback,consumerTag->{});
    }
}
