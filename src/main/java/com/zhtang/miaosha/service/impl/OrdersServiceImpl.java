package com.zhtang.miaosha.service.impl;

import com.zhtang.miaosha.exception.MyException;
import com.zhtang.miaosha.mapper.OrdersMapper;
import com.zhtang.miaosha.model.Orders;
import com.zhtang.miaosha.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    // 获取秒杀订单信息
    @Override
    public Orders getOrder(Long id) {
        Orders order = ordersMapper.getOrderById(id);
        if (order == null) {
            MyException.throwError("订单不存在", 404);
        }
        return order;
    }

    // 新建秒杀订单
    @Transactional
    @Override
    public void createOrder(Orders order) {
        int updateNum = ordersMapper.deductStock(order.getProductId());
        if (updateNum <= 0) {
            MyException.throwError("商品已售完", 400);
        }
        ordersMapper.insertOrder(order);
    }

    // 更新秒杀订单信息
    @Transactional
    @Override
    public void updateOrder(Orders order) {
        ordersMapper.updateOrderAmount(order);
    }

    // 删除秒杀订单
    @Transactional
    @Override
    public void deleteOrder(Long id) {
        ordersMapper.deleteOrderById(id);
    }
}
