package com.itmo.evaluationSystem.mapper;

import com.itmo.evaluationSystem.model.entity.EvaluationSystemBody;
import com.itmo.evaluationSystem.model.dto.evaluationsystem.EvaluationSystemBodyUpdateRequest;
import com.itmo.evaluationSystem.model.vo.EvaluationSystemBodyVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EvaluationSystemBodyMapper {
    @Select("select id,body_name from evaluation_system_body where head_id=#{id}")
    List<EvaluationSystemBodyVo> getBodyList(Integer id);

    @Insert("insert into evaluation_system_body(body_name,head_id) values(#{bodyName},#{headId})")
    void add(EvaluationSystemBody body);

    @Delete("delete from evaluation_system_body where head_id=#{headId}")
    void delete(Integer headId);

    @Update("update evaluation_system_body set body_name=#{bodyName} where id=#{id}")
    void update(EvaluationSystemBodyUpdateRequest request);
}
