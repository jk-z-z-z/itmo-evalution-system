package com.itmo.evalutionsystem.Service;

import com.itmo.evalutionsystem.Model.Entity.Teacher;

import java.util.List;

public interface TeacherService {

    void addTeacher(Teacher teacher);

    void deleteTeacher(int id);

    void updateTeacher(Teacher teacher);


    List<Teacher> getTeacher();
}
