package com.itmo.evaluationsystem.Model.Entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EvaluationSystemHead {
    private Integer id;
    private String headName;
    private BigDecimal proportion;
    private Integer party;
}
