package com.itmo.evaluationsystem.Model.dto.evaluationsystem;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class EvaluationSystemHeadUpdateRequest {
    private Integer id;
    private String headName;
    private BigDecimal proportion;
}
