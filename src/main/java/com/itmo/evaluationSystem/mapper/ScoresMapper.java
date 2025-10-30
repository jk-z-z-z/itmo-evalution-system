package com.itmo.evaluationSystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

@Mapper
public interface ScoresMapper {

    @Select("select score from scores where head_id=#{headId} and teacher_id=#{teacherId} and evaluation_id=#{evaluationId}")
    BigDecimal getScoreByHeadIdAndTeacherIdAndEvaluationId(Integer headId, Integer teacherId,Integer evaluationId);

    @Select("select SUM(s.score*eh.proportion) from scores s " +
            "join evaluation_system_head eh on s.head_id=eh.id " +
            "where s.evaluation_id=#{evaluationId} and s.teacher_id=#{teacherId}")
    BigDecimal getTotalScoreByEvaluationIdAndTeacherId(Integer evaluationId, Integer teacherId);


}
