package com.itmo.evaluationsystem.Model.dto.teacher;

import lombok.Data;

@Data
public class TeacherListGetRequest {
    private String name;
    private Integer page;
}
