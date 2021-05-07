package com.lwx.statistics.schedu;

import com.lwx.statistics.service.DailyService;
import com.lwx.statistics.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduTask {
    @Autowired
    private DailyService dailyService;

    //在每天凌晨1点，把前一天数据进行数据查询添加
    @Scheduled(cron = "00 30 00 * * ?")
    public void task2() {
        dailyService.registerCountDay(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }


//    @Scheduled(cron = "0/5 * * * * ?")
//    public void task1() {
//        System.out.println("========");
//    }
}
