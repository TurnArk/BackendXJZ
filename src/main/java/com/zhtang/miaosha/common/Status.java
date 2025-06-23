package com.zhtang.miaosha.common;

import lombok.Getter;

@Getter
public enum Status {
    // 通用请求状态
    SUCCESS(200, "成功"),
    ERROR(400, "未知错误"),

    // 商品请求状态
    PRODUCT_NOT_FOUND(400, "商品不存在"),
    PRODUCT_DELETE_ERROR(400, "商品删除失败"),
    PRODUCT_ALREADY_EXISTS(400, "商品已存在"),
    PRODUCT_INSERT_ERROR(400, "添加商品失败,请检查"),

    // 通用异常
    REQUEST_BODY_MISSING(400, "请求体缺失"),

    // 订单请求状态
    ORDER_NOT_NULL(400, "订单不存在"),
    ORDER_NOT_EXIST(400, "订单不存在"),
    ORDER_STOCK_SHORTAGE(400, "库存不足"),
    ORDER_CREATE_FAILED(400, "订单创建失败"),

    ORDER_LIST_EMPTY(400, "订单列表为空");




    private final Integer code;
    private final String message;

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
