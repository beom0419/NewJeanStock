package com.sascom.stockpricebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan(value = {"com.sascom.stockpricebackend.application.kis.properties"})
public class StockPriceBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockPriceBackendApplication.class, args);
    }

}
