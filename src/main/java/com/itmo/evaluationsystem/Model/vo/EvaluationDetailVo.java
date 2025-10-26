package com.itmo.evaluationsystem.Model.vo;

import lombok.Data;
import java.util.List;

@Data
public class EvaluationDetailVo {
    private EvaluationVo evaluation;
    private Long totalCount;
    private Long evaluatedCount;
    private Long pendingCount;
    private List<StudentVo> evaluatedStudents;
    private List<StudentVo> pendingStudents;
}