package com.itmo.evaluationsystem.Mapper;

import com.itmo.evaluationsystem.Model.dto.evalution.EvaluationAddCommand;
import com.itmo.evaluationsystem.Model.dto.evalution.EvaluationUpdateRequest;
import com.itmo.evaluationsystem.Model.vo.EvaluationVo;
import org.apache.ibatis.annotations.*;


import java.util.List;
@Mapper
public interface EvaluationMapper {
    /**
     * 添加单个学生
     * @param evaluationAddCommand
     */
    @Insert("insert into evaluation(name,create_date,start_date,end_date) " +
            "values (#{name},#{createDate},#{startDate},#{endDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(EvaluationAddCommand evaluationAddCommand);

    List<EvaluationVo> selectEvaluationListByName(String name, int offset, Integer pageSize);

    Long selectEvaluationCountByName(String name);

    @Select("SELECT name,create_date,start_date,end_date,status from evaluation where id=#{id} ")
    EvaluationVo getById(Integer id);

    @Update("update evaluation " +
            "set name=#{name}," +
            "start_date=#{startDate},end_date=#{endDate} " +
            "where id=#{id} ")
    void update(EvaluationUpdateRequest evaluationUpdateRequest);

    @Delete("delete from evaluation where id=#{id}")
    void delete(Integer id);
}
