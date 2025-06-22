package com.zhtang.miaosha.controller;

import com.zhtang.miaosha.exception.MyException;
import com.zhtang.miaosha.pojo.Orders;
import com.zhtang.miaosha.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private OrdersService ordersService;

    // 获取秒杀订单信息
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        try {
            Orders order = ordersService.getOrder(id);
            return ResponseEntity.ok(order);
        } catch (MyException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器内部错误：" + e.getMessage());
        }
    }

    // 新建秒杀订单
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Orders order) {
        try {
            ordersService.createOrder(order);
            return ResponseEntity.ok("下单成功");
        } catch (MyException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器内部错误：" + e.getMessage());
        }
    }

    // 更新秒杀订单信息
    @PutMapping
    public ResponseEntity<String> updateOrder(@RequestBody Orders order) {
        try {
            ordersService.updateOrder(order);
            return ResponseEntity.ok("更新成功");
        } catch (MyException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器内部错误：" + e.getMessage());
        }
    }

    // 删除秒杀订单
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        try {
            ordersService.deleteOrder(id);
            return ResponseEntity.ok("删除成功");
        } catch (MyException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器内部错误：" + e.getMessage());
        }
    }
}
