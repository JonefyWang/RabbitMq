package com.wang.rabbit.three;

import com.rabbitmq.client.Channel;
import com.wang.rabbit.utis.RabbitMqUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @ClassName Task02
 * @Description TODO
 * @Author 19285
 * @Date 2022/8/10 23:34
 * @Version 1.0
 */
public class Task02 {
    private static final String TASK_QUEUE_NAME = "ack_queue";

     public static void main(String[] args) throws Exception{
        try(Channel channel = RabbitMqUtils.getChannel())
        {
            channel.queueDeclare(TASK_QUEUE_NAME,false,false,false,null);
            Scanner sc = new Scanner(System.in);
            while(sc.hasNext()){
                String message = sc.nextLine();
                channel.basicPublish("",TASK_QUEUE_NAME,null,message.getBytes(StandardCharsets.UTF_8));
                System.out.println("生产者发生消息： " + message);
            }
        }
    }
}
