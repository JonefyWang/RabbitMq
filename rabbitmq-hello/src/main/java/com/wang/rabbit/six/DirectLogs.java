package com.wang.rabbit.six;

import com.rabbitmq.client.Channel;
import com.wang.rabbit.utis.RabbitMqUtils;

import java.util.Scanner;

/**
 * @ClassName DirectLogs
 * @Description TODO
 * @Author 19285
 * @Date 2022/8/14 15:13
 * @Version 1.0
 */
public class DirectLogs {
    /**
     * 交换机名称
     */
    public static final String EXCHANGE_NAME = "DIRECT_LOGS";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        //channel.exchangeDeclare(EXCHANGE_NAME,"fauout");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish(EXCHANGE_NAME,"error",null,message.getBytes("UTF-8"));
            System.out.println("生产者发出消息：" + message);
        }
    }
}
