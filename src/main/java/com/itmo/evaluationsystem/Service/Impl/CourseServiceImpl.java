package com.itmo.evaluationsystem.Service.Impl;

import com.itmo.evaluationsystem.Mapper.CourseMapper;
import com.itmo.evaluationsystem.Model.PageResult;
import com.itmo.evaluationsystem.Model.dto.course.CourseAddRequest;
import com.itmo.evaluationsystem.Model.dto.course.CourseUpdateRequest;
import com.itmo.evaluationsystem.Model.vo.CourseVo;
import com.itmo.evaluationsystem.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public void add(CourseAddRequest courseAddRequest) {
        courseMapper.add(courseAddRequest);

    }

    private final Integer PAGE_SIZE = 10;

    @Override
    public PageResult getList(String name, Integer page) {
        // 处理分页参数
        int currentPage = (page == null || page <= 0) ? 1 : page;
        int offset = (currentPage - 1) * PAGE_SIZE;

        // 查询数据列表（基于名称搜索后的结果分页）
        List<CourseVo> courseList = courseMapper.selectCourseListByName(name, offset, PAGE_SIZE);

        // 查询总记录数（基于名称搜索后的总数）
        Long totalCount = courseMapper.selectCourseCountByName(name);

        // 计算总页数
        int totalPages = (int) Math.ceil((double) totalCount / PAGE_SIZE);

        return new PageResult(courseList, totalCount, currentPage, PAGE_SIZE, totalPages);
    }

    @Override
    public CourseVo getById(Integer id) {
        return courseMapper.getById(id);
    }

    @Override
    public void update(CourseUpdateRequest courseUpdateRequest) {
        courseMapper.update(courseUpdateRequest);
    }

    @Override
    public void deleteById(Integer id) {
        courseMapper.delete(id);
    }
}
