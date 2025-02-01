package com.sascom.stockpricebackend.global.redis.pub;

import com.sascom.stockpricebackend.application.kis.model.StockData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RedisMessagePublisher {
    private final RedisTemplate<String, StockData> redisTemplate;

    public void publish(String destination, String message) {
        redisTemplate.convertAndSend(destination, message);
    }
}
