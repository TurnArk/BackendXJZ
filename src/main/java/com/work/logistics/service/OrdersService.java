package com.work.logistics.service;

import com.work.logistics.aop.SensitiveOperation;
import com.work.logistics.entity.Orders;
import com.work.logistics.entity.info.PersonInfo;
import com.work.logistics.mapper.OrdersMapper;
import com.work.logistics.mongo.LogisticsTrackRepository;
import com.work.logistics.utils.Distance;
import com.work.logistics.utils.JwtUtils;
import com.work.logistics.utils.MapAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.work.logistics.mongo.LogisticsTrack;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;

@Service
public class OrdersService {

    @Autowired
    private  OrdersMapper ordersMapper;

    @Autowired
    private MapAPI mapAPI;

    @Autowired
    private Distance distanceUtils;

    @Autowired
    private LogisticsTrackService logisticsTrackService;

    @Autowired
    private RedisService redisService;

    // 运费计算
    public Double calculateFee(Double weight, Double distance) {
        double w = weight == null ? 0.0 : weight;
        double d = distance == null ? 0.0 : distance;
        return w * 2.0 + d * 0.02;
    }

    // 新建订单
    @Transactional
    public Orders createOrder(Orders order, String token) {
        String userId = JwtUtils.getUserId(token);
        order.setUserId(userId);

        // 生成订单ID
        String orderId = UUID.randomUUID().toString().replace("-", "");
        order.setId(orderId);

        // 调用高德接口把寄件人地址转为经纬度
        String senderLoc = mapAPI.getLocation(order.getSenderAddress());
        order.setSenderLocation(senderLoc);

        // 调用高德接口把收件人地址转为经纬度
        String receiverLoc = mapAPI.getLocation(order.getReceiverAddress());
        order.setReceiverLocation(receiverLoc);

        // 距离计算
        double distanceKm = distanceUtils.calculateDistance(senderLoc, receiverLoc);

        Double fee = calculateFee(order.getWeight(), distanceKm);
        order.setFee(fee);
        order.setDistance(distanceKm);
        order.setCreateTime(LocalDateTime.now());
        order.setStatus("待揽件");
        ordersMapper.insertOrder(order);

        // 缓存寄件人
        redisService.cachePersonInfo("sender:" + userId,
                new PersonInfo(order.getSenderName(), order.getSenderPhone(), order.getSenderAddress()));

       // 缓存收件人
        redisService.cachePersonInfo("receiver:" + userId,
                new PersonInfo(order.getReceiverName(), order.getReceiverPhone(), order.getReceiverAddress()));
        return order;
    }

    // 查找订单
    public Orders getOrderById(String id) {
        return ordersMapper.selectById(id);
    }

    // 修改订单（不做）
    @SensitiveOperation("修改订单")
    public int updateOrder(Orders order) {
        order.setFee(calculateFee(order.getWeight(), order.getDistance()));
        return ordersMapper.updateOrder(order);
    }

    // 删除订单
    @SensitiveOperation("删除订单")
    public int deleteOrder(String id) {
        return ordersMapper.deleteById(id);
    }

    // 更新订单状态，并记录轨迹
    @Transactional
    @SensitiveOperation("更新订单状态")
    public int updateOrderStatus(String orderId, String newStatus, String location, String deliverymanId) {
        // 修改订单状态
        Orders order = ordersMapper.selectById(orderId);
        if (order == null) return 0;

        order.setStatus(newStatus);
        int updated = ordersMapper.updateOrderInfo(orderId, newStatus, deliverymanId);
        if (updated <= 0) return 0;

        // 插入 MongoDB 轨迹记录
        logisticsTrackService.addTrack(orderId, newStatus, location, deliverymanId);


        return 1;
    }
}
