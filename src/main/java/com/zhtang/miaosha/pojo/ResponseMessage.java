package com.zhtang.miaosha.pojo;

import org.springframework.http.HttpStatus;

public class ResponseMessage<T> {
    private int code;
    private String message;
    private T data;

    public ResponseMessage(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //接口请求成功
    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<>(HttpStatus.OK.value(), "success", data);
    }

    //接口请求失败
    public static <T> ResponseMessage<T> fail(int code, String message) {
        return new ResponseMessage<>(code, message, null);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
