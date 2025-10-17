package com.itmo.evalutionsystem.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private  Integer id;
    private String name;
    private Integer gender;
    private Integer age;
    private Integer positionId;
    private Integer titleId;
    private Integer major;
    private String email;
}
