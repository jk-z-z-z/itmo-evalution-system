package com.itmo.evaluationsystem.Mapper;


import com.itmo.evaluationsystem.Model.dto.teacher.TeacherAddRequest;
import com.itmo.evaluationsystem.Model.dto.teacher.TeacherUpdateRequest;
import com.itmo.evaluationsystem.Model.vo.TeacherVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {

    /**
     * 添加单个教师
     * @param teacherAddRequest
     */
    @Insert("insert into teacher(name,gender,age,position_id,title_id,major,email) " +
            "values (#{name},#{gender},#{age},#{positionId},#{titleId},#{major},#{email})")
    void add(TeacherAddRequest teacherAddRequest);

    @Delete("delete from teacher where id=#{id}")
    void delete(int id);

    @Update("update teacher " +
            "set name=#{name}," +
            "gender=#{gender},age=#{age}," +
            "position_id=#{positionId},title_id=#{titleId}," +
            "major=#{major},email=#{email} where id=#{id}")
    void update(TeacherUpdateRequest teacherUpdateRequest);


    /**
     * 根据姓名模糊查询教师列表（分页）
     * @param name 教师姓名，可为空（查询全部）
     * @param offset 偏移量
     * @param pageSize 每页大小
     * @return 教师列表
     */
    List<TeacherVo> selectTeacherListByName(String name, Integer offset, Integer pageSize);

    /**
     * 根据姓名模糊查询教师总数
     * @param name 教师姓名，可为空（查询全部）
     * @return 总记录数
     */
    Long selectTeacherCountByName(String name);

    @Select("select id,name,gender,age,position_id,title_id,major,email from teacher where id=#{id}")
    TeacherVo getById(Integer id);

    @Select("select name from teacher where party=#{party}")
    List<String> getListByParty(Integer party);
}
