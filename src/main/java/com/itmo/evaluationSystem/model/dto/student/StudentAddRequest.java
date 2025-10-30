package com.itmo.evaluationSystem.model.dto.student;

import lombok.Data;

@Data
public class StudentAddRequest {
    private String name;
    private String hduId;
    private Integer gender;
    private Integer age;
    private Integer classId;
    private Integer major;
}
