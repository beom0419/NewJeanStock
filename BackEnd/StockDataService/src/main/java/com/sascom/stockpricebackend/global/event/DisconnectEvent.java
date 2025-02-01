package com.sascom.stockpricebackend.global.event;

public class DisconnectEvent extends ChickenStockEvent {

    public DisconnectEvent(Object source, String eventId) {
        super(source, eventId);
    }
}
