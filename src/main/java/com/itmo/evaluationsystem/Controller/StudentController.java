package com.itmo.evaluationsystem.Controller;

import com.itmo.evaluationsystem.Model.PageResult;
import com.itmo.evaluationsystem.Model.Result;
import com.itmo.evaluationsystem.Model.dto.student.StudentAddRequest;
import com.itmo.evaluationsystem.Model.dto.student.StudentUpdateRequest;
import com.itmo.evaluationsystem.Model.vo.StudentVo;
import com.itmo.evaluationsystem.Service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 添加
     * @param studentAddRequest
     * @return
     */
    @PostMapping
    public Result add(@RequestBody StudentAddRequest studentAddRequest) {
        log.info("StudentController:add");

        studentService.add(studentAddRequest);

        return Result.success();
    }

    /**
     * 分页查询
     */
    @GetMapping
    public Result getList(@RequestParam(required = false) String name, 
                         @RequestParam(defaultValue = "1") Integer page) {
        log.info("StudentController:Student:getList");

        PageResult<StudentVo> studentList = studentService.getList(name, page);

        return Result.success(studentList);
    }

    /**
     * 根据id查询单个学生信息
     * @param id
     * @return
     */
    @GetMapping("/edit/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("StudentController:Student:getById");

        StudentVo studentVo=studentService.getById(id);

        return Result.success(studentVo);
    }

    /**
     * 更新学生信息
     * @param studentUpdateRequest
     * @return
     */
    @PutMapping("/edit")
    public Result  update(@RequestBody StudentUpdateRequest studentUpdateRequest) {
        log.info("StudentController:Student:update");

        studentService.update(studentUpdateRequest);

        return Result.success();
    }

    /**
     * 根据id删除学生信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("StudentController:Student:deleteById");

        studentService.deleteById(id);
        return Result.success();
    }

}
