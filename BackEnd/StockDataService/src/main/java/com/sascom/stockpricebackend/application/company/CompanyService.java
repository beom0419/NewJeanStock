package com.sascom.stockpricebackend.application.company;

import com.sascom.stockpricebackend.application.kis.KisWebSocketClient;
import com.sascom.stockpricebackend.application.kis.util.KisWebSocketUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CompanyService {

    public static final int MAX_COMPANY_SUB_SIZE = 20;

    private final CompanyRepository companyRepository;
    private final Map<String, String> companyCodeMap;
    private final KisWebSocketClient kisClient;
    private final KisWebSocketUtil kisWebSocketUtil;


    public void subscribeByCompanyId(Long companyId) throws IOException {
        if (companyCodeMap.size() >= MAX_COMPANY_SUB_SIZE) {
            return;
        }

        CompanyInfo companyInfo = companyRepository.findCompanyById(companyId)
                .orElseThrow(IllegalArgumentException::new);

        if (companyCodeMap.containsKey(companyInfo.name())) {
            return;
        }

        subscribe(companyInfo);
    }

    public void subscribeByCompanyName(String companyName) throws IOException {
        if (companyCodeMap.size() >= MAX_COMPANY_SUB_SIZE) {
            return;
        }

        if (companyCodeMap.containsKey(companyName)) {
            return;
        }

        CompanyInfo companyInfo = companyRepository.findCompanyByName(companyName)
                .orElseThrow(IllegalArgumentException::new);

        subscribe(companyInfo);
    }

    public void cancelByCompanyId(Long companyId) throws IOException {
        CompanyInfo companyInfo = companyRepository.findCompanyById(companyId)
                .orElseThrow(IllegalArgumentException::new);

        if (!companyCodeMap.containsKey(companyInfo.name())) {
            return;
        }

        cancel(companyInfo);
    }

    public void cancelByCompanyName(String companyName) throws IOException {
        if (!companyCodeMap.containsKey(companyName)) {
            return;
        }

        CompanyInfo companyInfo = companyRepository.findCompanyByName(companyName)
                .orElseThrow(IllegalArgumentException::new);

        cancel(companyInfo);
    }

    private void subscribe(CompanyInfo companyInfo) throws IOException {
        WebSocketSession kisWebSocketSession = kisClient.getKisWebSocketSession();
        kisWebSocketUtil.subscribeCompany(kisWebSocketSession, companyInfo.code());
        companyCodeMap.put(companyInfo.name(), companyInfo.code());
    }

    private void cancel(CompanyInfo companyInfo) throws IOException {
        WebSocketSession kisWebSocketSession = kisClient.getKisWebSocketSession();
        kisWebSocketUtil.cancelCompany(kisWebSocketSession, companyInfo.code());
        companyCodeMap.remove(companyInfo.name());
    }
}
