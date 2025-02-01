package com.sascom.stockpricebackend.application.kis.model;

import lombok.Getter;

// "유가증권단축종목코드|주식체결시간|주식현재가|전일대비부호|전일대비|전일대비율|가중평균주식가격|주식시가|주식최고가|주식최저가|매도호가1|매수호가1|체결거래량|누적거래량|누적거래대금|매도체결건수|매수체결건수|순매수체결건수|체결강도|총매도수량|총매수수량|체결구분|매수비율|전일거래량대비등락율|시가시간|시가대비구분|시가대비|최고가시간|고가대비구분|고가대비|최저가시간|저가대비구분|저가대비|영업일자|신장운영구분코드|거래정지여부|매도호가잔량|매수호가잔량|총매도호가잔량|총매수호가잔량|거래량회전율|전일동시간누적거래량|전일동시간누적거래량비율|시간구분코드|임의종료구분코드|정적VI발동기준가"
@Getter
public class StockPurchaseDto implements StockData{
    // 유가증권단축종목코드
    public final String stockCode;
    // 주식체결시간
    public final String transactionTime;
    // 주식현재가
    public final String currentPrice;
    // 전일대비부호
    public final String changeSign;
    // 전일대비
    public final String change;
    // 전일대비율
    public final String changeRate;
    // 가중평균주식가격
    public final String weightedAveragePrice;
    // 주식시가
    public final String openingPrice;
    // 주식최고가
    public final String highestPrice;
    // 주식최저가
    public final String lowestPrice;
    // 매도호가1
    public final String sellOffer1;
    // 매수호가1
    public final String buyOffer1;
    // 체결거래량
    public final String transactionVolume;
    // 누적거래량
    public final String cumulativeVolume;
    // 누적거래대금
    public final String cumulativeAmount;
    // 매도체결건수
    public final String sellTransactions;
    // 매수체결건수
    public final String buyTransactions;
    // 순매수체결건수
    public final String netBuyTransactions;
    // 체결강도
    public final String transactionStrength;
    // 총매도수량
    public final String totalSellQuantity;
    // 총매수수량
    public final String totalBuyQuantity;
    // 체결구분
    public final String transactionType;
    // 매수비율
    public final String buyRatio;
    // 전일거래량대비등락율
    public final String previousVolumeChangeRate;
    // 시가시간
    public final String openingTime;
    // 시가대비구분
    public final String openingChangeType;
    // 시가대비
    public final String openingChange;
    // 최고가시간
    public final String highestTime;
    // 고가대비구분
    public final String highestChangeType;
    // 고가대비
    public final String highestChange;
    // 최저가시간
    public final String lowestTime;
    // 저가대비구분
    public final String lowestChangeType;
    // 저가대비
    public final String lowestChange;
    // 영업일자
    public final String businessDate;
    // 신장운영구분코드
    public final String operationTypeCode;
    // 거래정지여부
    public final String tradingHalt;
    // 매도호가잔량
    public final String remainingSellOffer;
    // 매수호가잔량
    public final String remainingBuyOffer;
    // 총매도호가잔량
    public final String totalRemainingSellOffer;
    // 총매수호가잔량
    public final String totalRemainingBuyOffer;
    // 거래량회전율
    public final String volumeTurnoverRate;
    // 전일동시간누적거래량
    public final String previousSameTimeVolume;
    // 전일동시간누적거래량비율
    public final String previousSameTimeVolumeRate;
    // 시간구분코드
    public final String timeTypeCode;
    // 임의종료구분코드
    public final String arbitraryEndTypeCode;
    // 정적VI발동기준가
    public final String staticVILimitPrice;

    // 생성자
    public StockPurchaseDto(String[] data) {
        this.stockCode = data[0];
        this.transactionTime = data[1];
        this.currentPrice = data[2];
        this.changeSign = data[3];
        this.change = data[4];
        this.changeRate = data[5];
        this.weightedAveragePrice = data[6];
        this.openingPrice = data[7];
        this.highestPrice = data[8];
        this.lowestPrice = data[9];
        this.sellOffer1 = data[10];
        this.buyOffer1 = data[11];
        this.transactionVolume = data[12];
        this.cumulativeVolume = data[13];
        this.cumulativeAmount = data[14];
        this.sellTransactions = data[15];
        this.buyTransactions = data[16];
        this.netBuyTransactions = data[17];
        this.transactionStrength = data[18];
        this.totalSellQuantity = data[19];
        this.totalBuyQuantity = data[20];
        this.transactionType = data[21];
        this.buyRatio = data[22];
        this.previousVolumeChangeRate = data[23];
        this.openingTime = data[24];
        this.openingChangeType = data[25];
        this.openingChange = data[26];
        this.highestTime = data[27];
        this.highestChangeType = data[28];
        this.highestChange = data[29];
        this.lowestTime = data[30];
        this.lowestChangeType = data[31];
        this.lowestChange = data[32];
        this.businessDate = data[33];
        this.operationTypeCode = data[34];
        this.tradingHalt = data[35];
        this.remainingSellOffer = data[36];
        this.remainingBuyOffer = data[37];
        this.totalRemainingSellOffer = data[38];
        this.totalRemainingBuyOffer = data[39];
        this.volumeTurnoverRate = data[40];
        this.previousSameTimeVolume = data[41];
        this.previousSameTimeVolumeRate = data[42];
        this.timeTypeCode = data[43];
        this.arbitraryEndTypeCode = data[44];
        this.staticVILimitPrice = data[45];
    }
}