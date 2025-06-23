package com.zhtang.miaosha.controller;

import com.zhtang.miaosha.common.Result;
import com.zhtang.miaosha.common.Status;
import com.zhtang.miaosha.common.exception.MyException;
import com.zhtang.miaosha.pojo.Orders;
import com.zhtang.miaosha.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 订单模块功能
 * 1.查询单个订单
 * 2.新建订单
 * 3.更新订单
 * 4.删除订单
 */
@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private OrdersService ordersService;

    // 获取秒杀订单信息
    @GetMapping("/{id}")
    public Result<Orders> getOrder(@PathVariable Long id) throws MyException{
        Orders order = ordersService.getOrder(id); // 若为空应抛出 MyException
        return Result.success(order);
    }

    // 新建秒杀订单
    @PostMapping
    public Result<String> createOrder(@RequestBody Orders order) throws MyException {
        ordersService.createOrder(order);
        return Result.success();
    }

    // 更新秒杀订单信息
    @PutMapping
    public Result<String> updateOrder(@RequestBody Orders order) throws MyException {
        ordersService.updateOrder(order);
        return Result.success();
    }

    // 删除秒杀订单
    @DeleteMapping("/{id}")
    public Result<String> deleteOrder(@PathVariable Long id) throws MyException {
        ordersService.deleteOrder(id);
        return Result.success();
    }
}

