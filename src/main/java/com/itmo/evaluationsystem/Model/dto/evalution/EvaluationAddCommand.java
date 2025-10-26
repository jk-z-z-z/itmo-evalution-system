package com.itmo.evaluationsystem.Model.dto.evalution;

import lombok.Data;

import java.time.LocalDate;
@Data
public class EvaluationAddCommand {
    private Integer id;
    private String name;
    private LocalDate createDate;
    private LocalDate startDate;
    private LocalDate endDate;

    public EvaluationAddCommand(EvaluationAddRequest evaluationAddRequest) {
        this.name = evaluationAddRequest.getName();
        this.startDate = evaluationAddRequest.getStartDate();
        this.endDate = evaluationAddRequest.getEndDate();
        this.createDate=LocalDate.now();
    }
}
