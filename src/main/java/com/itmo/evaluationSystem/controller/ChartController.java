package com.itmo.evaluationSystem.controller;

import com.itmo.evaluationSystem.model.Result;
import com.itmo.evaluationSystem.model.vo.ChartBasicInfoVo;
import com.itmo.evaluationSystem.model.vo.teacherScoreDetailVo;
import com.itmo.evaluationSystem.service.ChartService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RequestMapping("/charts")
@RestController
public class ChartController {

    @Autowired
    private ChartService chartService;

    @GetMapping
    public Result getBasicInfo(){

        log.info("getBasicInfo");
        ChartBasicInfoVo staticInfo = chartService.getBasicInfo();
        return Result.success(staticInfo);
    }

    @GetMapping("/teacher/detail")
    public Result getRadarChartInfo(Integer teacherId,Integer party,Integer evaluationId){
        log.info("getChinaRadarChartInfo");
        HashMap<String, BigDecimal> date=chartService.getDetailByTeacher(teacherId,party,evaluationId);
        return Result.success(date);
    }

    @GetMapping("/teacher/totalScore/prediction")
    public Result getTotalScorePredictionTable(Integer teacherId){
        log.info("getTotalScorePredictionTable");
        HashMap<String,BigDecimal> date=chartService.getTotalScorePredictionTableByTeacher(teacherId);
        return Result.success(date);
    }

    @GetMapping("/teacher/rank")
    public Result getRank(Integer party,Integer evaluationId){
        log.info("getRank");
        List<teacherScoreDetailVo> rank=chartService.getRankInfo(party,evaluationId);
        return Result.success(rank);

    }


}
