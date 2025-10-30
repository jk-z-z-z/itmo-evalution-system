package com.itmo.evaluationSystem.model.vo;

import lombok.Data;

import java.util.Map;
@Data
public class ChartBasicInfoVo {
    private Map<String,Long> chinaTeacherGenderInfo;
    private Map<String,Long> russiaTeacherGenderInfo;
    private Map<String,Long> chinaTeacherTitleInfo;
    private Map<String,Long> russiaTeacherTitleInfo;

}
