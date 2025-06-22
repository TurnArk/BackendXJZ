package com.zhtang.miaosha.service;

import com.zhtang.miaosha.pojo.Orders;

public interface OrdersService {
    // 获取秒杀订单信息
    Orders getOrder(Long id);

    // 新建秒杀订单
    void createOrder(Orders order);

    // 更新秒杀订单信息
    void updateOrder(Orders order);

    // 删除秒杀订单
    void deleteOrder(Long id);
}
