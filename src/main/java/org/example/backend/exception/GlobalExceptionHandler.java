package org.example.backend.exception;

import org.example.backend.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 捕获 ServiceException 并返回错误信息
    @ExceptionHandler(ServiceException.class)
    public Result<String> handleServiceException(ServiceException e) {
        return Result.error(400, e.getMessage());
    }
}