package com.itmo.evaluationsystem.Model.dto.course;

import lombok.Data;

@Data
public class CourseUpdateRequest {
    private Integer id;
    private String name;
    private String englishName;
    private String major;
    private Integer teacherId;
}
