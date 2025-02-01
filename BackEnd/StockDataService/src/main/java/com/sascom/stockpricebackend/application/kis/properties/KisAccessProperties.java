package com.sascom.stockpricebackend.application.kis.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.HashMap;
import java.util.Map;

@Getter
@ConfigurationProperties("kis")
public class KisAccessProperties {
    private final String url;
    private final String real_port, test_port;
    private final Map<String, String> trIdMap = new HashMap<>();

    @ConstructorBinding
    private KisAccessProperties(
            String url, String real_port, String test_port,
            String[] trIdNames, String[] trIds
    ) {
        this.url = url;
        this.real_port = real_port;
        this.test_port = test_port;

        for (int i = 0; i < trIdNames.length; i++) {
            trIdMap.put(trIdNames[i], trIds[i]);
        }
    }
}
