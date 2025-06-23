package com.zhtang.miaosha.mapper;

import com.zhtang.miaosha.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * 映射层
 * @author ZHTang
 */
@Mapper
public interface OrdersMapper {
    Orders getOrderById(Long id);
    int insertOrder(Orders order);
    int updateOrderAmount(Orders order);
    int deleteOrderById(Long id);
    int getStockByProductId(Long productId);
    int deductStock(Long productId);
}
