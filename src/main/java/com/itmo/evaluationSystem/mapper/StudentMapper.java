package com.itmo.evaluationSystem.mapper;

import com.itmo.evaluationSystem.model.dto.student.StudentAddRequest;
import com.itmo.evaluationSystem.model.dto.student.StudentUpdateRequest;
import com.itmo.evaluationSystem.model.vo.StudentVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * 添加单个学生
     * @param studentAddRequest
     */
    @Insert("insert into student(hdu_id,name,gender,age,class_id,major) " +
            "values (#{hduId},#{name},#{gender},#{age},#{classId},#{major})")
    void add(StudentAddRequest studentAddRequest);

    List<StudentVo> selectStudentListByName(String name, int offset, Integer pageSize);

    Long selectStudentCountByName(String name);

    @Select("SELECT name,hdu_id,gender,age,class_id,major from student where id=#{id} ")
    StudentVo getById(Integer id);

    @Update("update student " +
            "set name=#{name},hdu_id=#{hduId},gender=#{gender},age=#{age}," +
            "class_id=#{classId},major=#{major} " +
            "where id=#{id} ")
    void update(StudentUpdateRequest studentUpdateRequest);

    @Delete("delete from student where id=#{id}")
    void delete(Integer id);
}
