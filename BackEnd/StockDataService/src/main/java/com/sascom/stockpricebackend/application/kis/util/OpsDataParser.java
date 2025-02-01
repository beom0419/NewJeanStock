package com.sascom.stockpricebackend.application.kis.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sascom.stockpricebackend.application.kis.model.ResolvedData;
import com.sascom.stockpricebackend.application.kis.model.StockData;
import com.sascom.stockpricebackend.application.kis.model.StockHokaDto;
import com.sascom.stockpricebackend.application.kis.model.StockPurchaseDto;
import com.sascom.stockpricebackend.application.kis.properties.KisAccessProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpsDataParser {
	public static final String PINGPONG_TR_ID = "PINGPONG";
	private final KisAccessProperties accessProperties;
	private final String HOKA_TR_ID = "H0STASP0";
	private final String PURCHASE_TR_ID = "H0STCNT0";
	private final String DELIMITER_PIPE = "\\|";
	private final String DELIMITER_CARET = "\\^";
	private final ObjectMapper objectMapper;

	public ResolvedData<StockData> resolveMessage(String message) throws JsonProcessingException {

		char fStr = message.charAt(0);

        switch (fStr) {
            case '0' -> {
                return handleApiResponseMessage(message);
            }
            case '1' -> {
                log.info("지원하지 않는 \"체결 통보 요청\"입니다. 코드를 확인해주세요");
                return null;
            }
            default -> {
                return handleNormalJsonMessage(message);
            }
        }
	}

	private ResolvedData<StockData> handleApiResponseMessage(String message) throws JsonProcessingException {
		String[] messageData = message.split(DELIMITER_PIPE);

		String trId = messageData[1];
		String[] stockData = messageData[3].split(DELIMITER_CARET);

		switch (trId) {
			case HOKA_TR_ID -> {
				StockHokaDto hokaDto = new StockHokaDto(stockData);
				return new ResolvedData<>(trId, hokaDto);
			}
			case PURCHASE_TR_ID -> {
				StockPurchaseDto purchaseDto = new StockPurchaseDto(stockData);
				return new ResolvedData<>(trId, purchaseDto);
			}
			default -> {
				return null;
            }
		}
    }

	private ResolvedData<StockData> handleNormalJsonMessage(String message) throws JsonProcessingException {
		JsonNode jsonNode = objectMapper.readTree(message);
		JsonNode header = jsonNode.get("header");

		String trId = header.get("tr_id").textValue();
		log.info("Transaction ID: {}", trId);

		if (PINGPONG_TR_ID.equals(trId)) {
			return new ResolvedData<>(trId, null);
		}

		JsonNode body = jsonNode.get("body");
		String resultCode = body.get("rt_cd").textValue();
		String messageCode = body.get("msg_cd").textValue();
		String msg = body.get("msg1").textValue();

		if (resultCode.equals("0")) {
			// TODO 메시지 암복호화를 위한 key (추후 삭제)
			JsonNode output = body.get("output");
			String key = output.get("key").textValue();
			String iv = output.get("iv").textValue();
			log.info("Key: {}, IV: {}", key, iv);
		} else {
			log.debug("Message Code: {}, Message: {}", messageCode, msg);
		}

		switch (trId) {
			case HOKA_TR_ID:
				log.info("주식호가 [{}] [{}]", messageCode, msg);
				break;
			case PURCHASE_TR_ID:
				log.info("주식체결 [{}] [{}]", messageCode, msg);
				break;
			default:
				break;
		}

		return new ResolvedData<>(trId, null);
	}
}
