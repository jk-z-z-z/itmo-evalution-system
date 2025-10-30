package com.itmo.evaluationSystem.service;

import com.itmo.evaluationSystem.model.PageResult;
import com.itmo.evaluationSystem.model.dto.course.CourseAddRequest;
import com.itmo.evaluationSystem.model.dto.course.CourseUpdateRequest;
import com.itmo.evaluationSystem.model.vo.CourseVo;


public interface CourseService {
    void add(CourseAddRequest courseAddRequest);

    PageResult getList(String name, Integer page);

    CourseVo getById(Integer id);

    void update(CourseUpdateRequest courseUpdateRequest);

    void deleteById(Integer id);
}
