package com.zhtang.miaosha.mapper;

import com.zhtang.miaosha.model.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrdersMapper {
    Orders getOrderById(Long id);
    void insertOrder(Orders order);
    void updateOrderAmount(Orders order);
    void deleteOrderById(Long id);

    int getStockByProductId(Long productId);
    int deductStock(Long productId);
}
