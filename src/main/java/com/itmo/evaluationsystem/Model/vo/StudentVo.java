package com.itmo.evaluationsystem.Model.vo;

import lombok.Data;

@Data
public class StudentVo {
    private Integer id;
    private String name;
    private String hduId;
    private Integer gender;
    private Integer age;
    private Integer classId;
    private Integer major;
}
