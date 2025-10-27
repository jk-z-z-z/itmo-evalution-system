package com.itmo.evaluationsystem.Model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;

@Data
public class teacherScoreDetailVo {
    private String teacherName;
    private BigDecimal totalScore;
    private HashMap<String,BigDecimal> scoreInfo;
}
