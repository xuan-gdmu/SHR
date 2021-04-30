package com.lwx.management.entity.vo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.common.MyResult;
import com.lwx.management.entity.Dictionary;
import com.lwx.management.service.DictionaryService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

    public List<Dictionary> getStaffType(DictionaryService dictionaryService) {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("dic_name", "员工类型");
        List<Dictionary> dictionaryList = dictionaryService.list(dictionaryQueryWrapper);
        return dictionaryList;

    }

    public List<Dictionary> getStaffStatus(DictionaryService dictionaryService) {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("dic_name", "员工状态");
        List<Dictionary> dictionaryList = dictionaryService.list(dictionaryQueryWrapper);
        return dictionaryList;
    }

    public List<Dictionary> getProbation(DictionaryService dictionaryService) {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("dic_name", "试用期");
        List<Dictionary> dictionaryList = dictionaryService.list(dictionaryQueryWrapper);
        return dictionaryList;
    }

    public List<Dictionary> getDocumentType(DictionaryService dictionaryService) {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("dic_name", "证件类型");
        List<Dictionary> dictionaryList = dictionaryService.list(dictionaryQueryWrapper);
        return dictionaryList;
    }

    public List<Dictionary> getBank(DictionaryService dictionaryService) {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("dic_name", "开户银行");
        List<Dictionary> dictionaryList = dictionaryService.list(dictionaryQueryWrapper);
        return dictionaryList;
    }

    public List<Dictionary> getPoliticalStatus(DictionaryService dictionaryService) {
        QueryWrapper<Dictionary> dictionaryQueryWrapper = new QueryWrapper<>();
        dictionaryQueryWrapper.eq("dic_name", "政治面貌");
        List<Dictionary> dictionaryList = dictionaryService.list(dictionaryQueryWrapper);
        return dictionaryList;
    }
}
