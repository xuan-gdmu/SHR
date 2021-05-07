package com.lwx.statistics.controller;


import com.lwx.common.MyResult;
import com.lwx.statistics.service.DailyService;
import org.ehcache.core.spi.service.StatisticsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author lwx
 * @since 2021-05-05
 */
@RestController
@RequestMapping("/statistics/daily")
public class DailyController {
    @Autowired
    private DailyService dailyService;

    /**
     * 统计人数，生成数据
     * @param day
     * @return
     */
    @PostMapping("registerCount/{day}")
    public MyResult registerCount(@PathVariable String day){
        dailyService.registerCountDay(day);
        return MyResult.ok();
    }

    //图表显示，返回两部分数据，日期json数组，数量json数组
    @GetMapping("showData/{type}/{begin}/{end}")
    public MyResult showData(@PathVariable String type,@PathVariable String begin,
                      @PathVariable String end) {
        Map<String,Object> map = dailyService.getShowData(type,begin,end);
        return MyResult.ok().data(map);
    }


}

