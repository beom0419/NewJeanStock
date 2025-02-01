package com.sascom.stockpricebackend.application.kis.model;

public record ResolvedData<T>(
        String trId,
        T data
) {
}
