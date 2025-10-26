package com.itmo.evaluationsystem.Model.dto.course;

import lombok.Data;

@Data
public class CourseListGetRequest {
    private String name;
    private Integer page;
}
