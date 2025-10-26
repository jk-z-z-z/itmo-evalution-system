package com.itmo.evaluationsystem.Service;

import com.itmo.evaluationsystem.Model.PageResult;
import com.itmo.evaluationsystem.Model.dto.evalution.EvaluationAddRequest;
import com.itmo.evaluationsystem.Model.dto.evalution.EvaluationListGetRequest;
import com.itmo.evaluationsystem.Model.dto.evalution.EvaluationUpdateRequest;
import com.itmo.evaluationsystem.Model.dto.teacher.TeacherAddRequest;
import com.itmo.evaluationsystem.Model.dto.teacher.TeacherListGetRequest;
import com.itmo.evaluationsystem.Model.dto.teacher.TeacherUpdateRequest;
import com.itmo.evaluationsystem.Model.vo.EvaluationDetailVo;
import com.itmo.evaluationsystem.Model.vo.EvaluationVo;
import com.itmo.evaluationsystem.Model.vo.TeacherVo;

public interface EvaluationService {
    void add(EvaluationAddRequest evaluationAddRequest);

    PageResult<EvaluationVo> getList(EvaluationListGetRequest evaluationListGetRequest);

    EvaluationVo getById(Integer id);

    void deleteById(Integer id);

    void update(EvaluationUpdateRequest evaluationUpdateRequest);

    // 获取测评详情（统计 + 名单）
    EvaluationDetailVo getDetail(Integer id);
}
