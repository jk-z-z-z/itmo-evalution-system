package com.itmo.evaluationsystem.Service.Impl;

import com.itmo.evaluationsystem.Mapper.EvaluationMapper;
import com.itmo.evaluationsystem.Mapper.EvaluationStudentMapper;
import com.itmo.evaluationsystem.Model.PageResult;
import com.itmo.evaluationsystem.Model.dto.evalution.EvaluationAddCommand;
import com.itmo.evaluationsystem.Model.dto.evalution.EvaluationAddRequest;
import com.itmo.evaluationsystem.Model.dto.evalution.EvaluationUpdateRequest;
import com.itmo.evaluationsystem.Model.vo.EvaluationDetailVo;
import com.itmo.evaluationsystem.Model.vo.EvaluationVo;
import com.itmo.evaluationsystem.Model.vo.StudentVo;
import com.itmo.evaluationsystem.Service.EvaluationService;
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
