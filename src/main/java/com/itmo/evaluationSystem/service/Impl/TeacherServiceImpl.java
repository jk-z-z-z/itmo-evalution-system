package com.itmo.evaluationSystem.service.Impl;

import com.itmo.evaluationSystem.mapper.TeacherMapper;
import com.itmo.evaluationSystem.model.PageResult;
import com.itmo.evaluationSystem.model.dto.teacher.TeacherAddRequest;
import com.itmo.evaluationSystem.model.dto.teacher.TeacherUpdateRequest;
import com.itmo.evaluationSystem.model.vo.TeacherVo;
import com.itmo.evaluationSystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void add(TeacherAddRequest teacherAddRequest) {

        teacherMapper.add(teacherAddRequest);

    }

    private final Integer PAGE_SIZE = 10;

    @Override
    public PageResult getList(String name, Integer page) {
        // 处理分页参数
        int currentPage = (page == null || page <= 0) ? 1 : page;
        int offset = (currentPage - 1) * PAGE_SIZE;

        // 查询数据列表（基于名称搜索后的结果分页）
        List<TeacherVo> teacherList = teacherMapper.selectTeacherListByName(name, offset, PAGE_SIZE);

        // 查询总记录数（基于名称搜索后的总数）
        Long totalCount = teacherMapper.selectTeacherCountByName(name);

        // 计算总页数
        int totalPages = (int) Math.ceil((double) totalCount / PAGE_SIZE);

        return new PageResult(teacherList, totalCount, currentPage, PAGE_SIZE, totalPages);
    }

    @Override
    public TeacherVo getById(Integer id) {
        return teacherMapper.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        teacherMapper.delete(id);
    }

    @Override
    public void update(TeacherUpdateRequest teacherUpdateRequest) {
        teacherMapper.update(teacherUpdateRequest);
    }
}
