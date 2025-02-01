package com.sascom.stockpricebackend.global.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
abstract class ChickenStockEvent extends ApplicationEvent {

    private final String eventId;

    public ChickenStockEvent(Object source, String eventId) {
        super(source);
        this.eventId = eventId;
    }
}
