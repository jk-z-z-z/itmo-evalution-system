package com.itmo.evalutionsystem.Controller;

import com.itmo.evalutionsystem.Service.TeacherService;
import com.itmo.evalutionsystem.Model.Entity.Teacher;
import com.itmo.evalutionsystem.Model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("teacher")
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 添加
     * @param teacher
     * @return
     */
    @PostMapping
    public Result update(@RequestBody Teacher teacher) {
        log.info("TeacherController:Teacher:add");

        teacherService.addTeacher(teacher);

        return Result.success();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping
    public Result delete(Integer id) {
        log.info("TeacherController:Teacher:delete");

        teacherService.deleteTeacher(id);

        return Result.success();
    }

    /**
     * 编辑
     * @param teacher
     * @return
     */
    @PutMapping
    public Result get(@RequestBody Teacher teacher) {
        log.info("TeacherController:Teacher:get");

        teacherService.updateTeacher(teacher);

        return Result.success();
    }

    /**
     * 查询全部
     * @return
     */
    @GetMapping
    public Result getAll() {
        log.info("TeacherController:Teacher:getAll");

        List<Teacher> teacherInfo=teacherService.getTeacher();

        return Result.success(teacherInfo);
    }

    /*@GetMapping
    public Result getAllByPage(@RequestParam int page, @RequestParam int size) {
        return new Result()
    }*/

}
