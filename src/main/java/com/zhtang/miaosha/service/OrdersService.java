package com.zhtang.miaosha.service;

import com.zhtang.miaosha.common.exception.MyException;
import com.zhtang.miaosha.pojo.Orders;

public interface OrdersService {
    // 获取秒杀订单信息
    Orders getOrder(Long id) throws MyException;

    // 新建秒杀订单
    boolean createOrder(Orders order) throws MyException;

    // 更新秒杀订单信息
    boolean updateOrder(Orders order) throws MyException;

    // 删除秒杀订单
    boolean deleteOrder(Long id) throws MyException;
}
