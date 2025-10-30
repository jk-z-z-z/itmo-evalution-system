package com.itmo.evaluationSystem.service.Impl;

import com.itmo.evaluationSystem.mapper.EvaluationMapper;
import com.itmo.evaluationSystem.mapper.EvaluationSystemHeadMapper;
import com.itmo.evaluationSystem.mapper.ScoresMapper;
import com.itmo.evaluationSystem.mapper.TeacherMapper;
import com.itmo.evaluationSystem.model.vo.ChartBasicInfoVo;
import com.itmo.evaluationSystem.model.vo.teacherScoreDetailVo;
import com.itmo.evaluationSystem.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class ChartServiceImpl implements ChartService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private EvaluationSystemHeadMapper evaluationSystemHeadMapper;

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private ScoresMapper scoresMapper;

    @Override
    public ChartBasicInfoVo getBasicInfo() {
        ChartBasicInfoVo chartBasicInfoVo = new ChartBasicInfoVo();

        HashMap<String,Long> russiaGenderTeacherNumMap = new HashMap<>();
        Long russiaMenTeacherNum=teacherMapper.getNumByPartyAndGender(1,1);
        Long russiaWomenTeacherNum=teacherMapper.getNumByPartyAndGender(1,0);
        russiaGenderTeacherNumMap.put("男",russiaMenTeacherNum);
        russiaGenderTeacherNumMap.put("女",russiaWomenTeacherNum);
        chartBasicInfoVo.setRussiaTeacherGenderInfo(russiaGenderTeacherNumMap);

        HashMap<String,Long> chinaGenderTeacherNumMap = new HashMap<>();
        Long chinaMenTeacherNum=teacherMapper.getNumByPartyAndGender(0,1);
        Long chinaWomenTeacherNum=teacherMapper.getNumByPartyAndGender(0,0);
        chinaGenderTeacherNumMap.put("男",chinaMenTeacherNum);
        chinaGenderTeacherNumMap.put("女",chinaWomenTeacherNum);
        chartBasicInfoVo.setChinaTeacherGenderInfo(chinaGenderTeacherNumMap);

        HashMap<String,Long> russiaTitleTeacherNumMap = new HashMap<>();
        Long russiaProfessorNum=teacherMapper.getNumByPartyAndTitle(1,0);
        Long russiaAssociateNum=teacherMapper.getNumByPartyAndTitle(1,1);
        Long russiaAssistantNum=teacherMapper.getNumByPartyAndTitle(1,2);
        russiaTitleTeacherNumMap.put("教授",russiaProfessorNum);
        russiaTitleTeacherNumMap.put("副教授，",russiaAssociateNum);
        russiaTitleTeacherNumMap.put("助教",russiaAssistantNum);
        chartBasicInfoVo.setRussiaTeacherTitleInfo(russiaTitleTeacherNumMap);

        HashMap<String,Long> chinaTitleTeacherNumMap = new HashMap<>();
        Long chinaProfessorNum=teacherMapper.getNumByPartyAndTitle(0,0);
        Long chinaAssociateNum=teacherMapper.getNumByPartyAndTitle(0,1);
        Long chinaAssistantNum=teacherMapper.getNumByPartyAndTitle(0,2);
        chinaTitleTeacherNumMap.put("教授",chinaProfessorNum);
        chinaTitleTeacherNumMap.put("副教授，",chinaAssociateNum);
        chinaTitleTeacherNumMap.put("助教",chinaAssistantNum);
        chartBasicInfoVo.setChinaTeacherTitleInfo(chinaTitleTeacherNumMap);
        return chartBasicInfoVo;
    }

    @Override
    public HashMap<String,BigDecimal> getDetailByTeacher(Integer teacherId,Integer party,Integer evaluationId) {

        List<Integer> headIdList=evaluationSystemHeadMapper.getHeadIdByParty(party);
        HashMap<String, BigDecimal> radarInfoMap = new HashMap<>();
        for(Integer headId:headIdList){
            radarInfoMap.put(evaluationSystemHeadMapper.getNameById(headId),scoresMapper.getScoreByHeadIdAndTeacherIdAndEvaluationId(headId,teacherId,evaluationId));
        }

        return radarInfoMap;
    }

    @Override
    public HashMap<String, BigDecimal> getTotalScorePredictionTableByTeacher(Integer teacherId) {

        List<Integer> evaluationIdList=evaluationMapper.getIdList();
        HashMap<String,BigDecimal> totalScoresMap=new HashMap<>();
        for(Integer evaluationId:evaluationIdList){
            BigDecimal totalScore=scoresMapper.getTotalScoreByEvaluationIdAndTeacherId(evaluationId,teacherId);

            if(totalScore!=null){
                totalScoresMap.put(evaluationMapper.getNameById(evaluationId),totalScore);
            }
        }
        return totalScoresMap;
    }

    @Override
    public List<teacherScoreDetailVo> getRankInfo(Integer party, Integer evaluationId) {
        List<Integer> teacherIdList=teacherMapper.getIdListByParty(party);
        List<Integer> headIdList=evaluationSystemHeadMapper.getHeadIdByParty(party);
        List<teacherScoreDetailVo> totalTeacherList=new ArrayList<>();
        for(Integer teacherId:teacherIdList){
            HashMap<String, BigDecimal> scoreMap = new HashMap<>();
            BigDecimal totalScore=new BigDecimal(0);
            BigDecimal score;
            for(Integer headId:headIdList){
                score=scoresMapper.getScoreByHeadIdAndTeacherIdAndEvaluationId(headId,teacherId,evaluationId);
                scoreMap.put(evaluationSystemHeadMapper.getNameById(headId),score);
                totalScore=totalScore.add(score);
            }
            teacherScoreDetailVo chartTeacherRankVo=new teacherScoreDetailVo();
            chartTeacherRankVo.setTeacherName(teacherMapper.getNameById(teacherId));
            chartTeacherRankVo.setScoreInfo(scoreMap);
            chartTeacherRankVo.setTotalScore(totalScore);
            totalTeacherList.add(chartTeacherRankVo);
        }


        return totalTeacherList.stream()
                .sorted((a, b) -> b.getTotalScore().compareTo(a.getTotalScore()))
                .limit(10)
                .toList();
    }

}
