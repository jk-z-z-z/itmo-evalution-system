package com.itmo.evaluationsystem.Model.dto.student;

import lombok.Data;

@Data
public class StudentListGetRequest {
    private String name;
    private Integer page;
}
