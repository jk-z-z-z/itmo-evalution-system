package com.itmo.evaluationSystem.model.dto.evaluation;

import lombok.Data;

import java.time.LocalDate;
@Data
public class EvaluationAddRequest {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}
