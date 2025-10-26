package com.itmo.evaluationsystem.Controller;

import com.itmo.evaluationsystem.Model.PageResult;
import com.itmo.evaluationsystem.Model.Result;
import com.itmo.evaluationsystem.Model.dto.evalution.EvaluationAddRequest;
import com.itmo.evaluationsystem.Model.dto.evalution.EvaluationListGetRequest;
import com.itmo.evaluationsystem.Model.dto.evalution.EvaluationUpdateRequest;
import com.itmo.evaluationsystem.Model.vo.EvaluationVo;
import com.itmo.evaluationsystem.Service.EvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    /**
     * 添加
     * @param evaluationAddRequest
     * @return
     */
    @PostMapping
    public Result add(@RequestBody EvaluationAddRequest evaluationAddRequest) {
        log.info("EvaluationController:add");

        evaluationService.add(evaluationAddRequest);

        return Result.success();
    }

    /**
     * 分页查询
     */
    @GetMapping
    public Result getList(@RequestBody EvaluationListGetRequest evaluationListGetRequest) {
        log.info("EvaluationController:Evaluation:getList");

        PageResult<EvaluationVo> evaluationList=evaluationService.getList(evaluationListGetRequest);

        return Result.success(evaluationList);
    }

    /**
     * 根据id查询单个测评信息
     * @param id
     * @return
     */
    @GetMapping("/edit/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("EvaluationController:Evaluation:getById");

        EvaluationVo evaluationVo =evaluationService.getById(id);

        return Result.success(evaluationVo);
    }

    /**
     * 测评详情（统计 + 名单）
     */
    @GetMapping("/detail/{id}")
    public Result getDetail(@PathVariable Integer id) {
        log.info("EvaluationController:Evaluation:getDetail");
        return Result.success(evaluationService.getDetail(id));
    }

    /**
     * 更新课程信息
     * @param evaluationUpdateRequest
     * @return
     */
    @PutMapping("/edit")
    public Result  update(@RequestBody EvaluationUpdateRequest evaluationUpdateRequest) {
        log.info("EvaluationController:Evaluation:update");

        evaluationService.update(evaluationUpdateRequest);

        return Result.success();
    }

    /**
     * 根据id删除测评信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("EvaluationController:Evaluation:deleteById");

        evaluationService.deleteById(id);
        return Result.success();
    }

}

