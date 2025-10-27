package com.itmo.evaluationsystem.Service;

import com.itmo.evaluationsystem.Model.PageResult;
import com.itmo.evaluationsystem.Model.vo.teacherScoreDetailVo;

import java.util.List;

public interface ScoreService {
    PageResult<List<teacherScoreDetailVo>> getList(Integer evaluationId, Integer page, Integer party);
}
