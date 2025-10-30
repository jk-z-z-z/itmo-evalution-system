package com.itmo.evaluationSystem.model.dto.opinion;

import lombok.Data;

import java.time.LocalDate;
@Data
public class OpinionAddRequest {
    private Integer evaluationId;
    private String sendName;
    private LocalDate sendTime;
    private Integer teacherId;
    private String content;
}
