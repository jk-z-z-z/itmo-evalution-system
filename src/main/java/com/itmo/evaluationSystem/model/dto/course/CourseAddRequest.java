package com.itmo.evaluationSystem.model.dto.course;

import lombok.Data;

@Data
public class CourseAddRequest {
    private String name;
    private String englishName;
    private String major;
    private Integer teacherId;
}
