package com.work.logistics.common;

import lombok.Getter;

@Getter
public enum Status {
    // 通用请求状态
    SUCCESS(200, "成功"),
    ERROR(400, "未知错误"),

    // 用户
    LOGIN_FAILED(401, "用户名或密码错误"),


    // 通用异常
    REQUEST_BODY_MISSING(400, "请求体缺失"),

    // 配送员状态
    DELIVERYMAN_NOT_AVAILABLE(400, "当前配送员状态不可接单"),

    ORDER_LIST_EMPTY(400, "订单列表为空");




    private final Integer code;
    private final String message;

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
