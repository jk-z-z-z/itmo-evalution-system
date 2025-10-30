package com.itmo.evaluationSystem.mapper;

import com.itmo.evaluationSystem.model.vo.StudentVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EvaluationStudentMapper {

    /**
     * 为所有学生创建指定测评的记录，初始 is_evaluate=0
     */
    @Insert("INSERT INTO evaluation_student (student_id, evaluation_id, is_evaluate) " +
            "SELECT  #{evaluationId}, 0 FROM student")
    void insertForAllStudents(Integer evaluationId);

    /** 统计已评人数 */
    @Select("SELECT COUNT(*) FROM evaluation_student WHERE evaluation_id=#{evaluationId} AND is_evaluate=1")
    Long countEvaluated(@Param("evaluationId") Integer evaluationId);

    /** 统计待评人数 */
    @Select("SELECT COUNT(*) FROM evaluation_student WHERE evaluation_id=#{evaluationId} AND is_evaluate=0")
    Long countPending(@Param("evaluationId") Integer evaluationId);

    /** 查询已评学生名单 */
    @Select("SELECT s.id, s.name, s.gender, s.age, s.hdu_id, s.major, s.class_id " +
            "FROM student s JOIN evaluation_student es ON es.student_id = s.id " +
            "WHERE es.evaluation_id=#{evaluationId} AND es.is_evaluate=1")
    List<StudentVo> listEvaluated(@Param("evaluationId") Integer evaluationId);

    /** 查询待评学生名单 */
    @Select("SELECT s.id, s.name, s.gender, s.age, s.hdu_id, s.major, s.class_id " +
            "FROM student s JOIN evaluation_student es ON es.student_id = s.id " +
            "WHERE es.evaluation_id=#{evaluationId} AND es.is_evaluate=0")
    List<StudentVo> listPending(@Param("evaluationId") Integer evaluationId);
}