package com.itmo.evaluationSystem.model.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EvaluationSystemHead {
    private Integer id;
    private String headName;
    private BigDecimal proportion;
    private Integer party;
}
