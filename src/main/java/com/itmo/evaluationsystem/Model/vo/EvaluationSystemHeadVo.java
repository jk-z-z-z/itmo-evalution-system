package com.itmo.evaluationsystem.Model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class EvaluationSystemHeadVo {
    private Integer id;
    private String headName;
    private BigDecimal proportion;
    private Integer party;
    private List<EvaluationSystemBodyVo> evaluationSystemBodyVoList;


}
