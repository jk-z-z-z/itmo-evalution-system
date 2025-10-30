package com.itmo.evaluationSystem.model.vo;

import lombok.Data;

@Data
public class CourseVo {
    private Integer id;
    private String name;
    private String englishName;
    private String major;
    private Integer teacherId;
}
