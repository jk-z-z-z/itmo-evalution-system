package com.itmo.evaluationSystem.controller;

import com.itmo.evaluationSystem.model.Result;
import com.itmo.evaluationSystem.model.dto.opinion.OpinionAddRequest;
import com.itmo.evaluationSystem.model.vo.OpinionGetListVo;
import com.itmo.evaluationSystem.service.OpinionService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/opinions")
public class OpinionController {
    @Autowired
    public OpinionService opinionService;
    @PostMapping
    public Result add(OpinionAddRequest request){
        log.info("add");

        opinionService.add(request);

        return Result.success();
    }
    @GetMapping
    public Result getList(){
        log.info("getList");

        OpinionGetListVo data=opinionService.getList();

        return Result.success(data);
    }



}
