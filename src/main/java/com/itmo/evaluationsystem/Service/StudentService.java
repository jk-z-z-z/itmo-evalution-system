package com.itmo.evaluationsystem.Service;

import com.itmo.evaluationsystem.Model.PageResult;
import com.itmo.evaluationsystem.Model.dto.student.StudentAddRequest;
import com.itmo.evaluationsystem.Model.dto.student.StudentListGetRequest;
import com.itmo.evaluationsystem.Model.dto.student.StudentUpdateRequest;
import com.itmo.evaluationsystem.Model.vo.StudentVo;

public interface StudentService {

    void add(StudentAddRequest studentAddRequest);

    PageResult<StudentVo> getList(StudentListGetRequest studentListGetRequest);

    StudentVo getById(Integer id);

    void update(StudentUpdateRequest studentUpdateRequest);

    void deleteById(Integer id);
}
