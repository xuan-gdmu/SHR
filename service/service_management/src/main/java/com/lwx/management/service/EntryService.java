package com.lwx.management.service;

import com.lwx.management.entity.Entry;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-30
 */

public interface EntryService extends IService<Entry> {

    List<Entry> getSelectEntryPage(long current, long limit);
}
