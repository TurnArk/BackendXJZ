package com.work.logistics.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public static <T> Result<T> success(T obj) {
        return Result.<T>builder()
                .code(Status.SUCCESS.getCode())
                .msg(Status.SUCCESS.getMessage())
                .data(obj)
                .build();
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static Result<String> error(Integer code, String msg) {
        return Result.<String>builder()
                .code(code)
                .msg(Optional.ofNullable(msg).orElse(Status.ERROR.getMessage()))
                .build();
    }

    public static Result<String> error(String msg) {
        return error(Status.ERROR.getCode(), msg);
    }

    public static Result<String> error(Status status) {
        return error(status.getCode(), status.getMessage());
    }
}
