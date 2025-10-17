package com.itmo.evalutionsystem.Mapper;

import com.itmo.evalutionsystem.Model.Entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface TeacherMapper {

    @Insert("insert into teacher(name,gender,age,position_id,title_id,major,email) " +
            "values (#{name},#{gender},#{age},#{positionId},#{titleId},#{major},#{email})")
    void add(Teacher teacher);

    @Delete("delete from teacher where id=#{id}")
    void delete(int id);

    @Update("update teacher " +
            "set name=#{name}," +
            "gender=#{gender},age=#{age}," +
            "position_id=#{positionId},title_id=#{titleId}," +
            "major=#{major},email=#{email} where id=#{id}")
    void update(Teacher teacher);

    @Select("select id, name, gender, age, position_id, title_id, major, email from teacher")
    List<Teacher> getAll();
}
