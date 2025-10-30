package com.itmo.evaluationsystem.Service;

import com.itmo.evaluationsystem.Model.PageResult;

public interface ScoreService {
    PageResult getList(Integer evaluationId, Integer page, Integer party);
}
