package com.work.logistics.service;

import com.work.logistics.common.Status;
import com.work.logistics.common.exception.MyException;
import com.work.logistics.entity.Deliveryman;
import com.work.logistics.entity.Orders;
import com.work.logistics.mapper.DeliverymanMapper;
import com.work.logistics.mapper.OrdersMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliverymanService {

    @Autowired
    private DeliverymanMapper deliverymanMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private LogisticsTrackService logisticsTrackService;

    // 配送员状态更改
    public void updateStatus(String id, String status) {
         deliverymanMapper.updateStatus(id, status);
    }

    // 配送员接单
    @Transactional
    public int acceptOrder(String orderId, String deliverymanId) throws MyException {
        if(!"空闲".equals(deliverymanMapper.getStatusById(deliverymanId)))
            throw new MyException(Status.DELIVERYMAN_NOT_AVAILABLE);
        Orders order = ordersMapper.selectById(orderId);
        // 更新订单状态与配送员ID
        int updated = ordersMapper.updateOrderInfo(orderId, "已揽收", deliverymanId);
        // 更改配送员状态
        updateStatus(deliverymanId,"配送中");
        // 写入 MongoDB 轨迹记录
        String location = order.getSenderAddress();
        logisticsTrackService.addTrack(orderId, "已揽收", location, deliverymanId);

        return updated;
    }

    // 获取配送员信息
    public Deliveryman getById(String id) {
        return deliverymanMapper.selectById(id);
    }
}
