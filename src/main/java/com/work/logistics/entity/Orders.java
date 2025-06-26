package com.work.logistics.entity;

import lombok.Data;
import java.time.LocalDateTime;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;

@Data
public class Orders {

    /**
     * 订单号
     */
    @Id(keyType = KeyType.Generator)
    private String id;

    /**
     * 寄件人用户ID
     */
    private String userId;

    /**
     * 寄件人姓名
     */
    private String senderName;

    /**
     * 寄件人手机号
     */
    private String senderPhone;

    /**
     * 寄件人详细地址
     */
    private String senderAddress;

    /**
     * 寄件人经纬度，格式： "经度,纬度"
     */
    private String senderLocation;

    /**
     * 收件人姓名
     */
    private String receiverName;

    /**
     * 收件人手机号
     */
    private String receiverPhone;

    /**
     * 收件人详细地址
     */
    private String receiverAddress;

    /**
     * 收件人经纬度，格式： "经度,纬度"
     */
    private String receiverLocation;

    /**
     * 包裹重量（kg）
     */
    private Double weight;

    /**
     * 距离（km）
     */
    private Double distance;

    /**
     * 运费
     */
    private Double fee;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 配送员编号
     */
    private String deliverymanId;
}

