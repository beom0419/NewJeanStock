package com.sascom.stockpricebackend.application.kis.properties;

import lombok.Getter;

@Getter
public enum TrType {
    SUBSCRIBE("1"),
    CANCEL("2");

    private final String value;

    TrType(String value) {
        this.value = value;
    }
}
