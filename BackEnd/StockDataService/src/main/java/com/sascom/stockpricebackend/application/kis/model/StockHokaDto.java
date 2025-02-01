package com.sascom.stockpricebackend.application.kis.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StockHokaDto implements StockData{
    private final String stockCode;
    private final String businessTime;
    private final String timeDivisionCode;
    private final List<OfferBid> offers;  // 매도호가
    private final List<OfferBid> bids;    // 매수호가
    private final String totalOfferVolume;
    private final String totalOfferChange;
    private final String totalBidVolume;
    private final String totalBidChange;
    private final String afterHoursTotalOfferVolume;
    private final String afterHoursTotalBidChange;
    private final String afterHoursTotalOfferVolumeChange;
    private final String afterHoursTotalBidChangeChange;
    private final String expectedTransactionPrice;
    private final String expectedTransactionVolume;
    private final String expectedTransactionAmount;
    private final String expectedTransactionDifference;
    private final String sign;
    private final String expectedTransactionRate;
    private final String cumulativeTransactionVolume;
    private final String stockTradeDivisionCode;


    public StockHokaDto(String[] hokaValueArr) {

        this.stockCode = hokaValueArr[0];
        this.businessTime = hokaValueArr[1];
        this.timeDivisionCode = hokaValueArr[2];

        this.offers = new ArrayList<>(9);
        this.bids = new ArrayList<>(10);

        for (int i = 3; i <= 11; i++) {
            OfferBid offer = new OfferBid(hokaValueArr[i], hokaValueArr[i + 20]);
            this.offers.add(offer);
        }

        for (int i = 13; i <= 22; i++) {
            OfferBid bid = new OfferBid(hokaValueArr[i], hokaValueArr[i + 20]);
            this.bids.add(bid);
        }

        this.totalOfferVolume = hokaValueArr[43];
        this.totalOfferChange = hokaValueArr[54];
        this.totalBidVolume = hokaValueArr[44];
        this.totalBidChange = hokaValueArr[55];
        this.afterHoursTotalOfferVolume = hokaValueArr[45];
        this.afterHoursTotalBidChange = hokaValueArr[46];
        this.afterHoursTotalOfferVolumeChange = hokaValueArr[56];
        this.afterHoursTotalBidChangeChange = hokaValueArr[57];
        this.expectedTransactionPrice = hokaValueArr[47];
        this.expectedTransactionVolume = hokaValueArr[48];
        this.expectedTransactionAmount = hokaValueArr[49];
        this.expectedTransactionDifference = hokaValueArr[50];
        this.sign = hokaValueArr[51];
        this.expectedTransactionRate = hokaValueArr[52];
        this.cumulativeTransactionVolume = hokaValueArr[53];
        this.stockTradeDivisionCode = hokaValueArr[58];
    }
}
