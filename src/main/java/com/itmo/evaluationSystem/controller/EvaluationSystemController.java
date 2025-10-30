package com.itmo.evaluationSystem.controller;

import com.itmo.evaluationSystem.model.Result;
import com.itmo.evaluationSystem.model.dto.evaluationsystem.EvaluationSystemAddRequest;
import com.itmo.evaluationSystem.model.dto.evaluationsystem.EvaluationSystemBodyUpdateRequest;
import com.itmo.evaluationSystem.model.dto.evaluationsystem.EvaluationSystemHeadUpdateRequest;
import com.itmo.evaluationSystem.model.vo.EvaluationSystemHeadVo;
import com.itmo.evaluationSystem.service.EvaluationSystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/systems")
public class EvaluationSystemController {
    @Autowired
    private EvaluationSystemService evaluationSystemService;

    @GetMapping
    public Result getByParty(Integer party){
        log.info("getByParty");
        List<EvaluationSystemHeadVo> data=evaluationSystemService.get(party);

        return Result.success(data);
    }
    @PostMapping("/edit")
    public Result add(@RequestBody EvaluationSystemAddRequest request){
        log.info("add");

        evaluationSystemService.add(request);

        return Result.success();
    }
    @DeleteMapping("/edit/head")
    public Result deleteHead(Integer headId){
        log.info("deleteHead");

        evaluationSystemService.deleteHead(headId);

        return Result.success();
    }
    @DeleteMapping("/edit/body")
    public Result deleteBody(Integer bodyId){
        log.info("deleteBody");

        evaluationSystemService.deleteBody(bodyId);

        return Result.success();

    }
    @PutMapping("/edit/head")
    public Result updateHead(@RequestBody EvaluationSystemHeadUpdateRequest request){
        log.info("updateHead");

        evaluationSystemService.updateHead(request);

        return Result.success();
    }
    @PutMapping("/edit/body")
    public Result updateBody(@RequestBody EvaluationSystemBodyUpdateRequest request){
        log.info("updateBody");

        evaluationSystemService.updateBody(request);

        return Result.success();
    }





}
