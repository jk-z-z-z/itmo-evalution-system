package com.itmo.evaluationsystem.Service;

import com.itmo.evaluationsystem.Model.PageResult;
import com.itmo.evaluationsystem.Model.dto.course.CourseAddRequest;
import com.itmo.evaluationsystem.Model.dto.course.CourseUpdateRequest;
import com.itmo.evaluationsystem.Model.vo.CourseVo;


public interface CourseService {
    void add(CourseAddRequest courseAddRequest);

    PageResult getList(String name, Integer page);

    CourseVo getById(Integer id);

    void update(CourseUpdateRequest courseUpdateRequest);

    void deleteById(Integer id);
}
