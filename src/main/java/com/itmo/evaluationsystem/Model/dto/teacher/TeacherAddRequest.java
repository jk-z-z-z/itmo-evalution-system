package com.itmo.evaluationsystem.Model.dto.teacher;


import lombok.Data;


@Data

public class TeacherAddRequest {
    private String name;
    private Integer gender;
    private Integer age;
    private Integer positionId;
    private Integer titleId;
    private Integer major;
    private String email;
}
