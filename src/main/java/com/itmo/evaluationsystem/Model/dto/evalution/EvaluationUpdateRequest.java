package com.itmo.evaluationsystem.Model.dto.evalution;

import lombok.Data;

import java.time.LocalDate;
@Data
public class EvaluationUpdateRequest {
    private Integer id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}
