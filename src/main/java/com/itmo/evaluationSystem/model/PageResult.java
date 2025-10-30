package com.itmo.evaluationSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
    private Object data;
    private Long totalCount;
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPages;

}
