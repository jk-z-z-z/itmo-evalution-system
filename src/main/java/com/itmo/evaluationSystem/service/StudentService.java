package com.itmo.evaluationSystem.service;

import com.itmo.evaluationSystem.model.PageResult;
import com.itmo.evaluationSystem.model.dto.student.StudentAddRequest;
import com.itmo.evaluationSystem.model.dto.student.StudentUpdateRequest;
import com.itmo.evaluationSystem.model.vo.StudentVo;

public interface StudentService {

    void add(StudentAddRequest studentAddRequest);

    PageResult getList(String name, Integer page);

    StudentVo getById(Integer id);

    void update(StudentUpdateRequest studentUpdateRequest);

    void deleteById(Integer id);
}
