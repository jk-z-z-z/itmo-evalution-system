package com.itmo.evaluationSystem.exception;

import com.itmo.evaluationSystem.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handlerException(Exception e) {
        log.error(e.getMessage());
        return Result.error(e.getMessage());

    }
}
