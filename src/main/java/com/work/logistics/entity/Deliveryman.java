package com.work.logistics.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Deliveryman {

    /**
     * 配送员编号
     */
    @Id(keyType = KeyType.Generator)
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 当前状态（空闲 / 配送中 / 离线）
     */
    private String status;

    /**
     * 完成订单总数
     */
    private Integer totalOrders;

    /**
     * 注册时间
     */
    private LocalDateTime createTime;
}
