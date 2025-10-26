package com.itmo.evaluationsystem.Service;

import com.itmo.evaluationsystem.Model.dto.evaluationsystem.EvaluationSystemAddRequest;
import com.itmo.evaluationsystem.Model.dto.evaluationsystem.EvaluationSystemBodyUpdateRequest;
import com.itmo.evaluationsystem.Model.dto.evaluationsystem.EvaluationSystemHeadUpdateRequest;
import com.itmo.evaluationsystem.Model.vo.EvaluationSystemHeadVo;

import java.util.List;

public interface EvaluationSystemService {
    List<EvaluationSystemHeadVo> get(Integer party);

    void add(EvaluationSystemAddRequest request);

    void deleteHead(Integer headId);

    void deleteBody(Integer bodyId);

    void updateHead(EvaluationSystemHeadUpdateRequest request);

    void updateBody(EvaluationSystemBodyUpdateRequest request);
}
