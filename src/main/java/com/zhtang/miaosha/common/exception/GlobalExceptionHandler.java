package com.zhtang.miaosha.common.exception;

import com.zhtang.miaosha.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 捕获你自定义的 MyException
    @ExceptionHandler(MyException.class)
    public Result<String> handleMyException(MyException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    // 捕获其他未知异常
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        return Result.error(500, "系统异常：" + e.getMessage());
    }
}

