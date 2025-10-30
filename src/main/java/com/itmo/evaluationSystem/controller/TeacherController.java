package com.itmo.evaluationSystem.controller;

import com.itmo.evaluationSystem.model.PageResult;
import com.itmo.evaluationSystem.model.dto.teacher.TeacherAddRequest;
import com.itmo.evaluationSystem.model.dto.teacher.TeacherUpdateRequest;
import com.itmo.evaluationSystem.model.vo.TeacherVo;
import com.itmo.evaluationSystem.service.TeacherService;
import com.itmo.evaluationSystem.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping("/teachers")
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;


    /**
     * 添加
     */

    @PostMapping
    public Result add(@RequestBody TeacherAddRequest teacherAddRequest) {
        log.info("TeacherController:Teacher:add");

        teacherService.add(teacherAddRequest);

        return Result.success();
    }

    /**
     * 分页查询
     */
    @GetMapping
    public Result getList(@RequestParam(required = false) String name, 
                         @RequestParam(defaultValue = "1") Integer page) {
        log.info("TeacherController:Teacher:getList");

        PageResult teacherList = teacherService.getList(name, page);

        return Result.success(teacherList);
    }

    /**
     * 根据id查询单个教师信息
     * @param id
     * @return
     */
    @GetMapping("/edit/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("TeacherController:Teacher:getById");

        TeacherVo teacherVo=teacherService.getById(id);

        return Result.success(teacherVo);
    }

    /**
     * 更新教师信息
     * @param teacherUpdateRequest
     * @return
     */
    @PutMapping("/edit")
    public Result  update(@RequestBody TeacherUpdateRequest teacherUpdateRequest) {
        log.info("TeacherController:Teacher:update");

        teacherService.update(teacherUpdateRequest);

        return Result.success();
    }

    /**
     * 根据id删除教师信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("TeacherController:Teacher:deleteById");

        teacherService.deleteById(id);
        return Result.success();
    }
}
