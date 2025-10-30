package com.itmo.evaluationSystem.model.vo;

import lombok.Data;

import java.time.LocalDate;
@Data
public class OpinionVo {
    private LocalDate sendTime;
    private String sendName;
    private Integer teacherId;
}
