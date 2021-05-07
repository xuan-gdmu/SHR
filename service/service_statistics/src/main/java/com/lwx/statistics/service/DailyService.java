package com.lwx.statistics.service;

import com.lwx.common.MyResult;
import com.lwx.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author lwx
 * @since 2021-05-05
 */
public interface DailyService extends IService<Daily> {
    //统计某一天注册人数,生成统计数据
    public void registerCountDay(String day);

    //图表显示，返回两部分数据，日期json数组，数量json数组
    Map<String, Object> getShowData(String type, String begin, String end);
}
