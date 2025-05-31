package org.example.backend.exception;

import lombok.extern.log4j.Log4j2;
import org.example.backend.result.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.support.DefaultMessageSourceResolvable;



import java.util.stream.Collectors;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 捕获不合理的输入参数导致的异常(通过 @valid注解来实现)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
        log.error("输入数据不合规: {}", errorMsg);
        return Result.error("输入数据不合规！" + errorMsg);
    }

    // 捕获一般性的ServiceException 并返回错误信息
    @ExceptionHandler(ServiceException.class)
    public Result<String> handleServiceException(ServiceException ex) {
        log.error("服务层业务异常: {}", ex.getMessage()); // 不记录异常堆栈,调试时可加入ex参数
        return Result.error(ex.getMessage());
    }
}