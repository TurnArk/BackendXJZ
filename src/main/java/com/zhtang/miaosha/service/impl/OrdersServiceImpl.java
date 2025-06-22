package com.zhtang.miaosha.service.impl;

import com.zhtang.miaosha.common.Status;
import com.zhtang.miaosha.common.exception.MyException;
import com.zhtang.miaosha.mapper.OrdersMapper;
import com.zhtang.miaosha.pojo.Orders;
import com.zhtang.miaosha.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    // 获取秒杀订单信息
    @Override
    public Orders getOrder(Long id) throws MyException{
        Orders order = ordersMapper.getOrderById(id);
        if (order == null) {
            log.info("订单不存在");
            throw new MyException(Status.ORDER_NOT_EXIST);
        }
        return order;
    }

    // 新建秒杀订单
    @Transactional
    @Override
    public void createOrder(Orders order) throws MyException{
        int updateNum = ordersMapper.deductStock(order.getProductId());
        if (updateNum <= 0) {
            log.info("库存不足");
            throw new MyException(Status.ORDER_NOT_EXIST);
        }
        ordersMapper.insertOrder(order);
    }

    // 更新秒杀订单信息
    @Transactional
    @Override
    public void updateOrder(Orders order) throws MyException{
        ordersMapper.updateOrderAmount(order);
    }

    // 删除秒杀订单
    @Transactional
    @Override
    public void deleteOrder(Long id) throws MyException{
        ordersMapper.deleteOrderById(id);
    }
}
