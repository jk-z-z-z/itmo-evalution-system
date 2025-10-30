package com.itmo.evaluationSystem.service;

import com.itmo.evaluationSystem.model.PageResult;
import com.itmo.evaluationSystem.model.dto.teacher.TeacherAddRequest;
import com.itmo.evaluationSystem.model.dto.teacher.TeacherUpdateRequest;
import com.itmo.evaluationSystem.model.vo.TeacherVo;


public interface TeacherService {

    void add(TeacherAddRequest teacherAddRequest);

    PageResult getList(String name, Integer page);

    TeacherVo getById(Integer id);

    void deleteById(Integer id);

    void update(TeacherUpdateRequest teacherUpdateRequest);
}
