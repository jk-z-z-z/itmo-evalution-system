package com.itmo.evaluationsystem.Model.dto.teacher;

import lombok.Data;

@Data
public class TeacherUpdateRequest {
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private Integer positionId;
    private Integer titleId;
    private String major;
    private String email;
}
