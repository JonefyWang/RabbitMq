package com.wang.rabbit.serve;

import com.rabbitmq.client.Channel;
import com.wang.rabbit.utis.RabbitMqUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName EmmitLogTopic
 * @Description 生产者
 * @Author 19285
 * @Date 2022/8/14 15:54
 * @Version 1.0
 */
public class EmmitLogTopic {
    /**
     * 交换机名称
     */
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMqUtils.getChannel();
        /**
         * 下图的绑定关系如下
         * Q1->绑定的是
         *          中间带3个单词的字符串（*.orange.*）
         * Q1->绑定的是
         *          最后一个单词是rabbit的3个单词（*.*.rabbbit
         *          第一个单词是lazy的多个单词（lazy.#）
         */
        Map<String,String > bindingKeyMap = new HashMap<>();
        bindingKeyMap.put("quick.orange.rabbit","被队列 Q1Q2 接收到");
        bindingKeyMap.put("lazy.orange.elephant","被队列 Q1Q2 接收到");
        bindingKeyMap.put("quick.orange.fox","被队列 Q1 接收到");
        bindingKeyMap.put("lazy.brown.fox","被队列 Q2 接收到");
        bindingKeyMap.put("lazy.pink.rabbit","虽然满足两个绑定但只被队列 Q2 接收一次");
        bindingKeyMap.put("quick.brown.fox","不匹配任何绑定不会被任何队列接收到会被丢弃");
        bindingKeyMap.put("quick.orange.male.rabbit","是四个单词不匹配任何绑定会被丢弃");
        bindingKeyMap.put("lazy.orange.male.rabbit","是四个单词但匹配 Q2");

        for (Map.Entry<String, String> bindingKeyEntry : bindingKeyMap.entrySet()) {
            String routingKey = bindingKeyEntry.getKey();
            String message = bindingKeyEntry.getValue();
            channel.basicPublish(EXCHANGE_NAME,routingKey,null,message.getBytes("UTF-8"));
            System.out.println("生产者发出消息：" + message);
        }
    }
}
