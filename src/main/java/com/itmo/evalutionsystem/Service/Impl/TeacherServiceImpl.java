package com.itmo.evalutionsystem.Service.Impl;

import com.itmo.evalutionsystem.Mapper.TeacherMapper;
import com.itmo.evalutionsystem.Service.TeacherService;
import com.itmo.evalutionsystem.Model.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void addTeacher(Teacher teacher) {
        teacherMapper.add(teacher);
    }

    @Override
    public void deleteTeacher(int id) {
        teacherMapper.delete(id);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherMapper.update(teacher);
    }

    @Override
    public List<Teacher> getTeacher() {

        return teacherMapper.getAll();
    }


}
