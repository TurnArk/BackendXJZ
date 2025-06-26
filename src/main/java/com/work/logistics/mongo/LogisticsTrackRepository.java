package com.work.logistics.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogisticsTrackRepository extends MongoRepository<LogisticsTrack, String> {
    List<LogisticsTrack> findByOrderIdOrderByUpdateTimeAsc(String orderId);
    Page<LogisticsTrack> findByOrderId(String orderId, Pageable pageable);
    void deleteByOrderId(String orderId);
}
