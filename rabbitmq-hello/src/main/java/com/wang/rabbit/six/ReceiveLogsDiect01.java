package com.wang.rabbit.six;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wang.rabbit.utis.RabbitMqUtils;

/**
 * @ClassName ReceiveLogsDiect01
 * @Description TODO
 * @Author 19285
 * @Date 2022/8/13 17:22
 * @Version 1.0
 */
public class ReceiveLogsDiect01 {
    /**
     * 交换机名称
     */
    public static final String EXCHANGE_NAME = "DIRECT_LOGS";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //声明一个队列
        channel.queueDeclare("console",false,false,false,null);
        /**
         * 绑定交换机与队列
         * bind
         */
        channel.queueBind("console",EXCHANGE_NAME,"info");
        channel.queueBind("console",EXCHANGE_NAME,"warning");
        //接收消息
        DeliverCallback deliverCallback = (counsumerTag, message) ->{
            System.out.println("ReceiveLogsDiect01控制台打印接收到的消息：" + new String(message.getBody(),"UTF-8"));
        };
        //消费者取消消息时回调接口
        channel.basicConsume("console",true,deliverCallback,consumerTag->{});
    }
}
