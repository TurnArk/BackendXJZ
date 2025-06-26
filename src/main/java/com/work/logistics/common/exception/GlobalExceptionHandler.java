package com.work.logistics.common.exception;

import com.work.logistics.common.Result;
import com.work.logistics.common.Status;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 捕获你自定义的 MyException
    @ExceptionHandler(MyException.class)
    public Result<String> handleMyException(MyException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    // 捕获请求体缺失或格式错误异常
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return Result.error(Status.REQUEST_BODY_MISSING);
    }

    // 捕获其他未知异常
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        // 打印自定义异常
        //  return Result.error(Status.ERROR.getCode(), Status.ERROR.getMessage());
        // 打印系统原始异常
        return Result.error(500, "系统异常：" + e.getMessage());
    }
}

