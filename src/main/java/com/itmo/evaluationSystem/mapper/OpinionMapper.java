package com.itmo.evaluationSystem.mapper;

import com.itmo.evaluationSystem.model.dto.opinion.OpinionAddRequest;
import com.itmo.evaluationSystem.model.vo.OpinionVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OpinionMapper {

    @Insert("INSERT into opinion(send_time,send_name,teacher_id,evaluation_id,content) " +
            "values(#{sendTime},#{sendName},#{teacherId},#{evaluationId},#{content})")
    void add(OpinionAddRequest request);

    @Select("select send_time,send_name,teacher_id from opinion")
    List<OpinionVo> getList();
}
