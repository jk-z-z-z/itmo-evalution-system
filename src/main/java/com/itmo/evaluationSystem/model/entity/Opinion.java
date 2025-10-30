package com.itmo.evaluationSystem.model.entity;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Opinion {
    private Integer id;
    private LocalDate sendDate;
    private String sendName;
    private Integer teacherId;
    private Integer evaluationId;
    private String content;
}
