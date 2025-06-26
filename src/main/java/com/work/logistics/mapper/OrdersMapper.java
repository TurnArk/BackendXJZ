package com.work.logistics.mapper;

import com.work.logistics.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper {
    int insertOrder(Orders order);
    Orders selectById(String id);
    int updateOrder(Orders order);
    int deleteById(String id);
    int updateOrderInfo(String orderId, String status, String deliverymanId);
}
