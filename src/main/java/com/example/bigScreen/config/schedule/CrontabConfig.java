package com.example.bigScreen.config.schedule;

import com.example.bigScreen.server.WebSocketServer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: SuKai
 * @date: 2022/6/8
 * @time: 10:09
 * @description:
 */
@Component
public class CrontabConfig {

    public int a = 0;

    @Scheduled(cron = "*/5 * * * * ?")//设置定时任务执行时间每5秒执行一次
    //@Scheduled注解用来设置定时任务执行时间
    public void crontabDemo(){
        a+=100;
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sm.format(new Date());
        System.out.println("时间："+date+"   "+a);

        WebSocketServer.sendInfo("aaaa","1");
    }
}
