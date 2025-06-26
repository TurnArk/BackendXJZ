package com.work.logistics.service;

import com.work.logistics.entity.info.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final int MAX_CACHE_SIZE = 3;

    // 添加缓存
    public void cachePersonInfo(String key, PersonInfo info) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();

        // 如果已有3条记录，移除最后一条（最旧）
        Long size = ops.size(key);
        if (size != null && size >= MAX_CACHE_SIZE) {
            ops.rightPop(key);
        }

        // 添加最新记录到最前面
        ops.leftPush(key, info);
    }

    // 获取缓存
    public List<Object> getPersonCache(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }
}
