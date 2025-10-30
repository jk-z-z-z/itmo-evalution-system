package com.itmo.evaluationSystem.controller;

import com.itmo.evaluationSystem.model.PageResult;
import com.itmo.evaluationSystem.model.Result;
import com.itmo.evaluationSystem.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/scores")
@RestController
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result getList(@RequestParam(defaultValue = "1") Integer evaluationId,
                          @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue ="1")Integer party) {
        log.info("getList");

        PageResult scoresListPageResult=scoreService.getList(evaluationId,page,party);


        return Result.success(scoresListPageResult);
    }



}
