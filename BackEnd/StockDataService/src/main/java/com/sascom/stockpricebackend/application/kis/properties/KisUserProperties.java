package com.sascom.stockpricebackend.application.kis.properties;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class KisUserProperties {
    private final String appkey;
    private final String appsecret;
    private final String personalsecKey;
    private final String custType;
    private final String contentType;
    private final String htsId;

    protected KisUserProperties(String appkey, String appsecret, String personalsecKey, String custType, String contentType, String htsId) {
        this.appkey = appkey;
        this.appsecret = appsecret;
        this.personalsecKey = personalsecKey;
        this.custType = custType;
        this.contentType = contentType;
        this.htsId = htsId;
    }

    /**
     * @param trType => 거래타입 (1:등록, 2:해제)
     * @return
     */
    public Map<String, Object> getHeader(String trType) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("appkey", appkey);
        map.put("appsecret", appsecret);
        map.put("personalseckey", personalsecKey);
        map.put("custtype", custType);
        map.put("tr_type", trType);
        map.put("content-type", contentType);
        return map;
    }
}
