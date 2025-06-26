package com.work.logistics.controller;

import com.work.logistics.mongo.PageResult;
import org.springframework.data.domain.Page;
import com.work.logistics.common.Result;
import com.work.logistics.entity.Orders;
import com.work.logistics.mongo.LogisticsTrack;
import com.work.logistics.service.LogisticsTrackService;
import com.work.logistics.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private LogisticsTrackService logisticsTrackService;

    // 查询某订单的所有物流轨迹
    @GetMapping("/track/{orderId}")
    public Result<PageResult<LogisticsTrack>> getTracksPageSimple(@PathVariable String orderId,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        PageResult<LogisticsTrack> result = logisticsTrackService.getTracksPageSimple(orderId, page, size);
        return Result.success(result);
    }

    // 删除某订单的所有轨迹记录
    @DeleteMapping("/track/{orderId}")
    public Result<String> deleteTracksByOrderId(@PathVariable String orderId) {
        logisticsTrackService.deleteTracksByOrderId(orderId);
        return Result.success("删除成功");
    }

    // 创建订单
    @PostMapping("/create")
    public Result<Orders> createOrder(@RequestBody Orders order,
                                       @RequestHeader("Authorization") String token) {
        ordersService.createOrder(order, token);
        return Result.success(order);
    }

    // 查询订单
    @GetMapping("/{id}")
    public Result<Orders> getOrderById(@PathVariable String id) {
        Orders order = ordersService.getOrderById(id);
        return Result.success(order);
    }

    // 删除订单
    @DeleteMapping("/{id}")
    public Result<String> deleteOrder(@PathVariable String id) {
        int rows = ordersService.deleteOrder(id);
        return Result.success("删除成功");
    }

    // 修改订单状态并记录轨迹
    @PostMapping("/updateStatus")
    public Result<String> updateOrderStatus(@RequestBody Map<String, String> data) {
        String orderId = data.get("orderId");
        String status = data.get("status");
        String location = data.get("location");
        String deliverymanId = data.get("deliverymanId");
        int result = ordersService.updateOrderStatus(orderId, status, location, deliverymanId);
        return Result.success("更新成功");
    }
}

