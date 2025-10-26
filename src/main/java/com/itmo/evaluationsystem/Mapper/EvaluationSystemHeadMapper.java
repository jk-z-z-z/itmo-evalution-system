package com.itmo.evaluationsystem.Mapper;

import com.itmo.evaluationsystem.Model.Entity.EvaluationSystemHead;
import com.itmo.evaluationsystem.Model.dto.evaluationsystem.EvaluationSystemHeadUpdateRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EvaluationSystemHeadMapper {

    @Select("select id,head_name,proportion,party from evaluation_system_head where party=#{party}")
    List<EvaluationSystemHead> getHeadList(Integer party);

    @Insert("insert evaluation_system_head(head_name,proportion,party) values(#{headName},#{proportion},#{party})")
    void add(EvaluationSystemHead head);

    @Delete("delete from evaluation_system_head where id=#{headId}")
    void delete(Integer headId);

    @Update("update evaluation_system_head set head_name=#{headName},proportion=#{proportion} where id=#{id}")
    void update(EvaluationSystemHeadUpdateRequest request);
}
