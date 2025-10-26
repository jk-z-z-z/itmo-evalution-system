package com.itmo.evaluationsystem.Model.dto.evaluationsystem;

import com.itmo.evaluationsystem.Model.Entity.EvaluationSystemBody;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class EvaluationSystemAddRequest {
    private String headName;
    private BigDecimal proportion;
    private Integer party;
    private List<EvaluationSystemBody> evaluationSystemBodyList;
}
