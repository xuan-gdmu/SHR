package com.lwx.management.entity.vo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.common.MyResult;
import com.lwx.management.entity.Dictionary;
import com.lwx.management.service.DictionaryService;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 单例模式
 */
@Data
@Component("springContextUtils")
public class DictionaryVo {

    private DictionaryVo() {
    }

    private static DictionaryVo dictionaryVo = null;

    public static DictionaryVo getInstance() {
        if (dictionaryVo == null) {
            synchronized (DictionaryVo.class) {
                if (dictionaryVo == null) {
                    return new DictionaryVo();
                }
            }
        }
        return dictionaryVo;
    }

    // 定义数组封装需要返回的数据
    private List<Dictionary> staffType;
    private List<Dictionary> staffStatus;
    private List<Dictionary> probation;
    private List<Dictionary> documentType;
    private List<Dictionary> bank;
    private List<Dictionary> politicalStatus;
    private List<Dictionary> education;

    public void refreshList(DictionaryService dictionaryService) {
        staffType = getList(dictionaryService, "员工类型");
        staffStatus = getList(dictionaryService, "员工状态");
        probation = getList(dictionaryService, "试用期");
        documentType = getList(dictionaryService, "证件类型");
        bank = getList(dictionaryService, "开户银行");
        politicalStatus = getList(dictionaryService, "政治面貌");
        education = getList(dictionaryService, "学历");
    }

    private List<Dictionary> getList(DictionaryService dictionaryService, Object str) {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("dic_name", str);
        return dictionaryService.list(dictionaryQueryWrapper);
    }
}
