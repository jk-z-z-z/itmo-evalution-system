package com.itmo.evaluationSystem.service;

import com.itmo.evaluationSystem.model.PageResult;

public interface ScoreService {
    PageResult getList(Integer evaluationId, Integer page, Integer party);
}
