package com.itmo.evaluationsystem.Controller;

import com.itmo.evaluationsystem.Model.PageResult;
import com.itmo.evaluationsystem.Model.vo.CourseVo;
import com.itmo.evaluationsystem.Service.CourseService;
import com.itmo.evaluationsystem.Model.Result;
import com.itmo.evaluationsystem.Model.dto.course.CourseAddRequest;
import com.itmo.evaluationsystem.Model.dto.course.CourseUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@Slf4j
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 添加
     * @param courseAddRequest
     * @return
     */
    @PostMapping
    public Result add(@RequestBody CourseAddRequest courseAddRequest) {
        log.info("CourseController:add");

        courseService.add(courseAddRequest);

        return Result.success();
    }

    /**
     * 分页查询
     */
    @GetMapping
    public Result getList(@RequestParam(required = false) String name, 
                         @RequestParam(defaultValue = "1") Integer page) {
        log.info("CourseController:Course:getList");

        PageResult<CourseVo> courseList = courseService.getList(name, page);

        return Result.success(courseList);
    }

    /**
     * 根据id查询单个课程信息
     * @param id
     * @return
     */
    @GetMapping("/edit/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("StudentController:Student:getById");

        CourseVo courseVo =courseService.getById(id);

        return Result.success(courseVo);
    }

    /**
     * 更新课程信息
     * @param courseUpdateRequest
     * @return
     */
    @PutMapping("/edit")
    public Result  update(@RequestBody CourseUpdateRequest courseUpdateRequest) {
        log.info("StudentController:Student:update");

        courseService.update(courseUpdateRequest);

        return Result.success();
    }

    /**
     * 根据id删除课程信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("StudentController:Student:deleteById");

        courseService.deleteById(id);
        return Result.success();
    }

}
