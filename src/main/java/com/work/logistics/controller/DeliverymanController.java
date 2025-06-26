package com.work.logistics.controller;

import com.work.logistics.common.Result;
import com.work.logistics.common.exception.MyException;
import com.work.logistics.entity.Deliveryman;
import com.work.logistics.service.DeliverymanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/delivery")
public class DeliverymanController {

    @Autowired
    private DeliverymanService deliverymanService;

    // 配送员接单
    @PostMapping("/accept")
    public Result<String> acceptOrder(@RequestBody Map<String, String> data) throws MyException {
        String orderId = data.get("orderId");
        String deliverymanId = data.get("deliverymanId");
        int rows = deliverymanService.acceptOrder(orderId, deliverymanId);
        return Result.success("接单成功");
    }

    // 获取配送员信息
    @GetMapping("/{id}")
    public Result<Deliveryman> getById(@PathVariable String id) {
        Deliveryman deliveryman = deliverymanService.getById(id);
        return Result.success(deliveryman);
    }
}
