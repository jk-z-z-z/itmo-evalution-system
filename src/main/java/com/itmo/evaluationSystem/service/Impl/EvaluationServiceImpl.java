package com.itmo.evaluationSystem.service.Impl;

import com.itmo.evaluationSystem.mapper.EvaluationMapper;
import com.itmo.evaluationSystem.mapper.EvaluationStudentMapper;
import com.itmo.evaluationSystem.model.PageResult;
import com.itmo.evaluationSystem.model.dto.evaluation.EvaluationAddCommand;
import com.itmo.evaluationSystem.model.dto.evaluation.EvaluationAddRequest;
import com.itmo.evaluationSystem.model.dto.evaluation.EvaluationUpdateRequest;
import com.itmo.evaluationSystem.model.vo.EvaluationDetailVo;
import com.itmo.evaluationSystem.model.vo.EvaluationVo;
import com.itmo.evaluationSystem.model.vo.StudentVo;
import com.itmo.evaluationSystem.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private EvaluationStudentMapper evaluationStudentMapper;

    @Override
    public void add(EvaluationAddRequest evaluationAddRequest) {
        EvaluationAddCommand evaluationAddCommand = new EvaluationAddCommand(evaluationAddRequest);
        evaluationMapper.add(evaluationAddCommand);
        // 发布测评后，为所有学生创建评测关系，初始未评测
        evaluationStudentMapper.insertForAllStudents(evaluationAddCommand.getId());
    }

    @Override
    public EvaluationDetailVo getDetail(Integer id) {
        EvaluationVo evaluationVo = evaluationMapper.getById(id);
        List<StudentVo> evaluated = evaluationStudentMapper.listEvaluated(id);
        List<StudentVo> pending = evaluationStudentMapper.listPending(id);
        Long evaluatedCount = evaluationStudentMapper.countEvaluated(id);
        Long pendingCount = evaluationStudentMapper.countPending(id);
        EvaluationDetailVo vo = new EvaluationDetailVo();
        vo.setEvaluation(evaluationVo);
        vo.setEvaluatedStudents(evaluated);
        vo.setPendingStudents(pending);
        vo.setEvaluatedCount(evaluatedCount);
        vo.setPendingCount(pendingCount);
        vo.setTotalCount(evaluatedCount + pendingCount);
        return vo;
    }

    private final Integer PAGE_SIZE = 10;

    @Override
    public PageResult getList(String name, Integer page) {
        // 处理分页参数
        int currentPage = (page == null || page <= 0) ? 1 : page;
        int offset = (currentPage - 1) * PAGE_SIZE;

        // 查询数据列表（基于名称搜索后的结果分页）
        List<EvaluationVo> evaluationList = evaluationMapper.selectEvaluationListByName(name, offset, PAGE_SIZE);

        // 查询总记录数（基于名称搜索后的总数）
        Long totalCount = evaluationMapper.selectEvaluationCountByName(name);

        // 计算总页数
        int totalPages = (int) Math.ceil((double) totalCount / PAGE_SIZE);

        return new PageResult(evaluationList, totalCount, currentPage, PAGE_SIZE, totalPages);
    }

    @Override
    public EvaluationVo getById(Integer id) {
        return evaluationMapper.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        evaluationMapper.delete(id);
    }

    @Override
    public void update(EvaluationUpdateRequest teacherUpdateRequest) {
        evaluationMapper.update(teacherUpdateRequest);
    }
}
