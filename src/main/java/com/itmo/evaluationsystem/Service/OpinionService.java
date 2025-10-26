package com.itmo.evaluationsystem.Service;

import com.itmo.evaluationsystem.Model.dto.opinion.OpinionAddRequest;
import com.itmo.evaluationsystem.Model.vo.OpinionGetListVo;
import org.springframework.stereotype.Service;

@Service
public interface OpinionService {

    void add(OpinionAddRequest request);

    OpinionGetListVo getList();
}
