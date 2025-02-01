package com.sascom.stockpricebackend.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class AppConfig {

    @Bean
    public Map<String, String> companyCodeMap() {
        ConcurrentHashMap<String, String> companyCodeMap = new ConcurrentHashMap<>();
        companyCodeMap.put("삼성전자", "005930");
        companyCodeMap.put("삼성전기", "009150");
        companyCodeMap.put("신한은행", "000010");
        companyCodeMap.put("하나은행", "004940");
        return companyCodeMap;
    }
}
