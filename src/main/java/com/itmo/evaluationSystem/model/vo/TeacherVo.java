package com.itmo.evaluationSystem.model.vo;

import lombok.Data;

@Data
public class TeacherVo {
    private Integer id;
    private String  name;
    private Integer gender;
    private Integer age;
    private Integer positionId;
    private Integer titleId;
    private Integer major;
    private String  email;

}
