package com.itmo.evaluationSystem.service.Impl;

import com.itmo.evaluationSystem.mapper.StudentMapper;
import com.itmo.evaluationSystem.model.PageResult;
import com.itmo.evaluationSystem.model.dto.student.StudentAddRequest;
import com.itmo.evaluationSystem.model.dto.student.StudentUpdateRequest;
import com.itmo.evaluationSystem.model.vo.StudentVo;
import com.itmo.evaluationSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public void add(StudentAddRequest studentAddRequest) {
        studentMapper.add(studentAddRequest);

    }

    private final Integer PAGE_SIZE = 10;

    @Override
    public PageResult getList(String name, Integer page) {
        // 处理分页参数
        int currentPage = (page == null || page <= 0) ? 1 : page;
        int offset = (currentPage - 1) * PAGE_SIZE;

        // 查询数据列表（基于名称搜索后的结果分页）
        List<StudentVo> studentList = studentMapper.selectStudentListByName(name, offset, PAGE_SIZE);

        // 查询总记录数（基于名称搜索后的总数）
        Long totalCount = studentMapper.selectStudentCountByName(name);

        // 计算总页数
        int totalPages = (int) Math.ceil((double) totalCount / PAGE_SIZE);

        return new PageResult(studentList, totalCount, currentPage, PAGE_SIZE, totalPages);
    }

    @Override
    public StudentVo getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(StudentUpdateRequest studentUpdateRequest) {
        studentMapper.update(studentUpdateRequest);
    }

    @Override
    public void deleteById(Integer id) {
        studentMapper.delete(id);
    }
}
