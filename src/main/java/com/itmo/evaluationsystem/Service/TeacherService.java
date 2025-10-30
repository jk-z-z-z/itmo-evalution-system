package com.itmo.evaluationsystem.Service;

import com.itmo.evaluationsystem.Model.PageResult;
import com.itmo.evaluationsystem.Model.dto.teacher.TeacherAddRequest;
import com.itmo.evaluationsystem.Model.dto.teacher.TeacherUpdateRequest;
import com.itmo.evaluationsystem.Model.vo.TeacherVo;


public interface TeacherService {

    void add(TeacherAddRequest teacherAddRequest);

    PageResult getList(String name, Integer page);

    TeacherVo getById(Integer id);

    void deleteById(Integer id);

    void update(TeacherUpdateRequest teacherUpdateRequest);
}
