package com.sascom.stockpricebackend.global.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChickenStockEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishDisconnectEvent(String eventId) {
        log.info("[Publish] Disconnect Event: {}", eventId);
        DisconnectEvent disconnectEvent = new DisconnectEvent(this, eventId);
        applicationEventPublisher.publishEvent(disconnectEvent);
    }
}
