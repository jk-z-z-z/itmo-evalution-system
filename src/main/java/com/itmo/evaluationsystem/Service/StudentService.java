package com.itmo.evaluationsystem.Service;

import com.itmo.evaluationsystem.Model.PageResult;
import com.itmo.evaluationsystem.Model.dto.student.StudentAddRequest;
import com.itmo.evaluationsystem.Model.dto.student.StudentUpdateRequest;
import com.itmo.evaluationsystem.Model.vo.StudentVo;

public interface StudentService {

    void add(StudentAddRequest studentAddRequest);

    PageResult getList(String name, Integer page);

    StudentVo getById(Integer id);

    void update(StudentUpdateRequest studentUpdateRequest);

    void deleteById(Integer id);
}
