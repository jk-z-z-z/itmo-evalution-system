package com.itmo.evaluationSystem.service;

import com.itmo.evaluationSystem.model.dto.opinion.OpinionAddRequest;
import com.itmo.evaluationSystem.model.vo.OpinionGetListVo;
import org.springframework.stereotype.Service;

@Service
public interface OpinionService {

    void add(OpinionAddRequest request);

    OpinionGetListVo getList();
}
