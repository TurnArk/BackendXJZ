package com.work.logistics.mapper;

import com.work.logistics.entity.Deliveryman;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeliverymanMapper {
    void updateStatus(String id, String status);
    String getStatusById(String id);
    Deliveryman selectById(String id);
}
