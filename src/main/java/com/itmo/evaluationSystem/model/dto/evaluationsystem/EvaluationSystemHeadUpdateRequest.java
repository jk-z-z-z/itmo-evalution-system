package com.itmo.evaluationSystem.model.dto.evaluationsystem;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class EvaluationSystemHeadUpdateRequest {
    private Integer id;
    private String headName;
    private BigDecimal proportion;
}
