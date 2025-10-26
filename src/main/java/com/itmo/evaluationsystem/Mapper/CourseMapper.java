package com.itmo.evaluationsystem.Mapper;

import com.itmo.evaluationsystem.Model.dto.course.CourseAddRequest;
import com.itmo.evaluationsystem.Model.dto.course.CourseUpdateRequest;
import com.itmo.evaluationsystem.Model.vo.CourseVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {
    /**
     * 添加单个学生
     * @param courseAddRequest
     */
    @Insert("insert into course(name,English_name,major,teacher_id) " +
            "values (#{name},#{englishName},#{major},#{teacherId})")
    void add(CourseAddRequest courseAddRequest);

    List<CourseVo> selectCourseListByName(String name, int offset, Integer pageSize);

    Long selectCourseCountByName(String name);

    @Select("SELECT name,English_name,major,teacher_id from course where id=#{id} ")
    CourseVo getById(Integer id);

    @Update("update course " +
            "set name=#{name},English_name=#{englishName}," +
            "major=#{major},teacher_id=#{teacherId} " +
            "where id=#{id} ")
    void update(CourseUpdateRequest courseUpdateRequest);

    @Delete("delete from course where id=#{id}")
    void delete(Integer id);

}
