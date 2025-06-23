package com.zhtang.miaosha.controller;

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
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        //todo:
        return null;
    }

    // 新建秒杀订单
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Orders order) {
        //todo:
        return null;
    }

    // 更新秒杀订单信息
    @PutMapping
    public ResponseEntity<String> updateOrder(@RequestBody Orders order) {
        //todo:
        return null;
    }

    // 删除秒杀订单
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        //todo:
        return null;
    }
}
