package com.itmo.evaluationsystem.Service.Impl;

import com.itmo.evaluationsystem.Mapper.EvaluationSystemHeadMapper;
import com.itmo.evaluationsystem.Mapper.ScoresMapper;
import com.itmo.evaluationsystem.Mapper.TeacherMapper;
import com.itmo.evaluationsystem.Model.PageResult;
import com.itmo.evaluationsystem.Model.vo.teacherScoreDetailVo;
import com.itmo.evaluationsystem.Service.EvaluationService;
import com.itmo.evaluationsystem.Service.ScoreService;
import com.itmo.evaluationsystem.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoresMapper scoresMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private EvaluationSystemHeadMapper evaluationSystemHeadMapper;

    private final Integer PAGE_SIZE = 10;



    @Override
    public PageResult<List<teacherScoreDetailVo>> getList(Integer evaluationId, Integer page, Integer party) {
        // 处理分页参数
        int currentPage = (page == null || page <= 0) ? 1 : page;
        int offset = (currentPage - 1) * PAGE_SIZE;

        List<Integer> teacherIdList=teacherMapper.getPageIdListByParty(party,offset,PAGE_SIZE);
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

        // 查询总记录数（基于名称搜索后的总数）
        Long totalCount =teacherMapper.selectTeacherCountByParty(party);

        // 计算总页数
        int totalPages = (int) Math.ceil((double) totalCount / PAGE_SIZE);

        return new PageResult<>(totalTeacherList, totalCount, currentPage, PAGE_SIZE, totalPages);
    }
}
