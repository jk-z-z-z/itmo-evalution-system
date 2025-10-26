package com.itmo.evaluationsystem.Model.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EvaluationVo {
    private Integer id;
    private String name;
    private LocalDate createDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer status;
}
