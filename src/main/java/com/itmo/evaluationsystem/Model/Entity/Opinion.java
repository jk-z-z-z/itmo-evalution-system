package com.itmo.evaluationsystem.Model.Entity;

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
