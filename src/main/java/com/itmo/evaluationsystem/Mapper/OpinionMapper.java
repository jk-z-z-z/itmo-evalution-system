package com.itmo.evaluationsystem.Mapper;

import com.itmo.evaluationsystem.Model.dto.opinion.OpinionAddRequest;
import com.itmo.evaluationsystem.Model.vo.OpinionVo;
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
