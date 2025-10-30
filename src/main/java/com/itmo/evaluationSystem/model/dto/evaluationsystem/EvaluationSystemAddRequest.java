package com.itmo.evaluationSystem.model.dto.evaluationsystem;

import com.itmo.evaluationSystem.model.entity.EvaluationSystemBody;
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
