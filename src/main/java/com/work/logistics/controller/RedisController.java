package com.work.logistics.controller;

import com.work.logistics.common.Result;
import com.work.logistics.service.RedisService;
import com.work.logistics.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    // 获取用户缓存的寄件人和收件人信息
    @GetMapping("/cache-info")
    public Result<Map<String, List<Object>>> getUserCacheInfo(@RequestHeader("Authorization") String token) {
        String userId = JwtUtils.getUserId(token);

        List<Object> senderList = redisService.getPersonCache("sender:" + userId);
        List<Object> receiverList = redisService.getPersonCache("receiver:" + userId);

        Map<String, List<Object>> data = new HashMap<>();
        data.put("senderHistory", senderList);
        data.put("receiverHistory", receiverList);

        return Result.success(data);
    }
}
