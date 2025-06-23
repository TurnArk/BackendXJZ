package com.zhtang.miaosha.common.exception;

import com.zhtang.miaosha.common.Result;
import com.zhtang.miaosha.common.Status;
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
        return Result.error(Status.ERROR.getCode(), Status.ERROR.getMessage());
    }
}

