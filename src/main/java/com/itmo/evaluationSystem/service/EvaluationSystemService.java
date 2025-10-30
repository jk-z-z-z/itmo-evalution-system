package com.itmo.evaluationSystem.service;

import com.itmo.evaluationSystem.model.dto.evaluationsystem.EvaluationSystemAddRequest;
import com.itmo.evaluationSystem.model.dto.evaluationsystem.EvaluationSystemBodyUpdateRequest;
import com.itmo.evaluationSystem.model.dto.evaluationsystem.EvaluationSystemHeadUpdateRequest;
import com.itmo.evaluationSystem.model.vo.EvaluationSystemHeadVo;

import java.util.List;

public interface EvaluationSystemService {
    List<EvaluationSystemHeadVo> get(Integer party);

    void add(EvaluationSystemAddRequest request);

    void deleteHead(Integer headId);

    void deleteBody(Integer bodyId);

    void updateHead(EvaluationSystemHeadUpdateRequest request);

    void updateBody(EvaluationSystemBodyUpdateRequest request);
}
