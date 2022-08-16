package com.wang.rabbit.utis;

/**
 * @ClassName SleepUtils
 * @Description TODO
 * @Author 19285
 * @Date 2022/8/10 23:32
 * @Version 1.0
 */
public class SleepUtils {
    public static void sleep(int second){
        try {
            Thread.sleep(1000 * second);
        } catch (InterruptedException _ignored)
        {Thread.currentThread().interrupt();
        }

    }
}
