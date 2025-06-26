package com.work.logistics.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "logistics_track")
public class LogisticsTrack {
    @Id
    private String id;
    private String orderId;           // 订单ID
    private String status;            // 状态：运输中、已签收等
    private String location;          // 地点
    private String locationCoords;    // 经纬度
    private String operator;          // 操作员
    private LocalDateTime updateTime; // 更新时间
}

