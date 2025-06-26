package com.work.logistics.service;

import com.work.logistics.mongo.PageResult;
import com.work.logistics.utils.MapAPI;
import org.springframework.data.domain.Page;
import com.work.logistics.mongo.LogisticsTrack;
import com.work.logistics.mongo.LogisticsTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogisticsTrackService {

    @Autowired
    private LogisticsTrackRepository repository;

    @Autowired
    private MapAPI mapAPI;

    // 添加物流轨迹记录
    public void addTrack(String orderId, String status, String location, String operator) {
        LogisticsTrack track = new LogisticsTrack();
        track.setOrderId(orderId);
        track.setStatus(status);
        track.setLocation(location);
        track.setLocationCoords(mapAPI.getLocation(location));
        track.setOperator(operator);
        track.setUpdateTime(LocalDateTime.now());
        repository.save(track);
    }

    // 查询某订单轨迹，分页
    public PageResult<LogisticsTrack> getTracksPageSimple(String orderId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("updateTime").ascending());
        Page<LogisticsTrack> pageData = repository.findByOrderId(orderId, pageRequest);

        return new PageResult<>(
                pageData.getNumber(),
                pageData.getSize(),
                pageData.getNumberOfElements(),
                pageData.isEmpty(),
                pageData.getContent()
        );
    }

    // 删除某订单的所有轨迹记录
    public void deleteTracksByOrderId(String orderId) {
        repository.deleteByOrderId(orderId);
    }
}
