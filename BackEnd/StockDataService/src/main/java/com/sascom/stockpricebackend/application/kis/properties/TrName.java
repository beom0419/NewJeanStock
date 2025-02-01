package com.sascom.stockpricebackend.application.kis.properties;

import lombok.Getter;

@Getter
public enum TrName {
    REALTIME_HOKA("/stock-hoka"),
    REALTIME_PURCHASE("/stock-purchase");

    private final String dest;

    TrName(String dest) {
        this.dest = dest;
    }
}
