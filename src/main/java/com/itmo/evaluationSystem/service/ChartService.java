package com.itmo.evaluationSystem.service;

import com.itmo.evaluationSystem.model.vo.ChartBasicInfoVo;
import com.itmo.evaluationSystem.model.vo.teacherScoreDetailVo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


public interface ChartService {
    ChartBasicInfoVo getBasicInfo();

    HashMap<String, BigDecimal> getDetailByTeacher(Integer teacherId, Integer party, Integer evaluationId);

    HashMap<String, BigDecimal> getTotalScorePredictionTableByTeacher(Integer teacherId);

    List<teacherScoreDetailVo> getRankInfo(Integer party, Integer evaluationId);
}
