package com.wang.rabbit.two;

import com.rabbitmq.client.Channel;
import com.wang.rabbit.utis.RabbitMqUtils;

import java.util.Scanner;

/**
 * @ClassName Task01
 * @Description TODO
 * @Author 19285
 * @Date 2022/8/10 22:50
 * @Version 1.0
 */
public class Task01 {
    private static final String QUEUE_NAME="hello";
    public static void main(String[] args) throws Exception {
        try(Channel channel= RabbitMqUtils.getChannel();)
        { channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            //从控制台当中接受信息
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String message = scanner.next();
                channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
                System.out.println("发送消息完成:"+message);
            }
        }
    }
}
