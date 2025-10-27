package com.itmo.evaluationsystem.Model.vo;

import lombok.Data;

import java.util.Map;
@Data
public class ChartBasicInfoVo {
    private Map<String,Long> chineseTeacherGenderInfo;
    private Map<String,Long> russiaTeacherGenderInfo;
    private Map<String,Long> chinaTeacherTitleInfo;
    private Map<String,Long> russiaTeacherTitleInfo;

}
