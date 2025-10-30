package com.itmo.evaluationSystem.service;

import com.itmo.evaluationSystem.model.PageResult;
import com.itmo.evaluationSystem.model.dto.evaluation.EvaluationAddRequest;
import com.itmo.evaluationSystem.model.dto.evaluation.EvaluationUpdateRequest;
import com.itmo.evaluationSystem.model.vo.EvaluationDetailVo;
import com.itmo.evaluationSystem.model.vo.EvaluationVo;

public interface EvaluationService {
    void add(EvaluationAddRequest evaluationAddRequest);

    PageResult getList(String name, Integer page);

    EvaluationVo getById(Integer id);

    void deleteById(Integer id);

    void update(EvaluationUpdateRequest evaluationUpdateRequest);

    // 获取测评详情（统计 + 名单）
    EvaluationDetailVo getDetail(Integer id);
}
