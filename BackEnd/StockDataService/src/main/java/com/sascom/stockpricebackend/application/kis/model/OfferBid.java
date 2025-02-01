package com.sascom.stockpricebackend.application.kis.model;

/**
 * @param price  가격
 * @param volume 잔량
 */
public record OfferBid(
        String price,
        String volume
) {
}


