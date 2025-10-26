package com.itmo.evaluationsystem.Model.dto.evalution;

import lombok.Data;


@Data
public class EvaluationListGetRequest {
    private Integer page;
    private String name;
}
