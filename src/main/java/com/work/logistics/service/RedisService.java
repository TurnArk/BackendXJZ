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

    // 添加缓存，若完全相同的 PersonInfo 已存在，则不添加
    public void cachePersonInfo(String key, PersonInfo info) {
        ListOperations<String, Object> ops = redisTemplate.opsForList();

        // 获取所有缓存，判断是否已存在完全相同的对象
        List<Object> cachedList = ops.range(key, 0, -1);
        if (cachedList != null) {
            for (Object obj : cachedList) {
                if (info.equals(obj)) {
                    // 已存在相同，直接返回不添加
                    return;
                }
            }
        }

        // 不存在相同，添加缓存
        Long size = ops.size(key);
        if (size != null && size >= MAX_CACHE_SIZE) {
            ops.rightPop(key); // 移除最旧
        }
        ops.leftPush(key, info); // 添加最新
    }

    // 获取缓存列表
    public List<Object> getPersonCache(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }
}
