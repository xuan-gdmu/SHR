package com.lwx.management.entity.vo;

import javafx.geometry.Pos;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lwx
 * @Create 2021-04-01-9:40
 * @Version V
 */
@Data
public class DeptVo {
    private String id;

    private String dname;

    private List<PostVo> children= new ArrayList<>();
}
