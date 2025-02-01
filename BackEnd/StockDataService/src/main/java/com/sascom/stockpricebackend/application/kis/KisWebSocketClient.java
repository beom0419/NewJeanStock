package com.sascom.stockpricebackend.application.kis;

import com.sascom.stockpricebackend.application.kis.util.KisWebSocketUtil;
import com.sascom.stockpricebackend.global.event.DisconnectEvent;
import com.sascom.stockpricebackend.application.kis.properties.KisAccessProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Component
public class KisWebSocketClient extends StandardWebSocketClient {

    private final WebSocketHandler kisWebSocketHandler;
    private final KisAccessProperties accessProperties;
    private final Map<String, String> companyCodeMap;
    private final KisWebSocketUtil kisWebSocketUtil;

    private final int MAX_RECONNECT_ATTEMPTS = 8;
    private final int MAX_DELAY = 100000;
    private final Random random = new Random();

    private final AtomicInteger reconnectAttempts = new AtomicInteger();
    private int reconnectTime = 0;

    @Getter
    private WebSocketSession kisWebSocketSession;

    @EventListener(ApplicationReadyEvent.class)
    protected void initializeConnection() {
        String kisApiUri = accessProperties.getUrl() + ":" + accessProperties.getReal_port();
        connectWebSocket(kisApiUri);
        reconnectTime = 0;
    }

    @EventListener(DisconnectEvent.class)
    private void reconnect(DisconnectEvent event) throws InterruptedException {
        if (reconnectAttempts.get() >= MAX_RECONNECT_ATTEMPTS) {
            log.error("[Reconnect Error] Kis WebSocket reconnect failed after {} attempts, at: {}", reconnectAttempts.get(), LocalDateTime.now());
            return;
        }

        int delay = calcDelay(reconnectAttempts.getAndIncrement());
        log.info("[Listen] Disconnect Event: {}", event.getEventId());
        log.info("Wait {}sec and start reconnect", delay/1000.0);

        Thread.sleep(delay);
        initializeConnection();
    }

    private int calcDelay(int attempt) {
        if (attempt == 0) {
            return random.nextInt(1000);
        }
        return (int) Math.min(MAX_DELAY, (Math.pow(2, attempt) * 1000) + random.nextInt(1000));
    }

    private void connectWebSocket(String apiUri) {
        CompletableFuture<WebSocketSession> execute = execute(kisWebSocketHandler, apiUri);
        execute.thenAccept(webSocketSession -> {
            log.info("kis session id: {}", webSocketSession.getId());
            kisWebSocketSession = webSocketSession;

            companyCodeMap.forEach((name, code) -> {
                try {
                    kisWebSocketUtil.subscribeCompany(kisWebSocketSession, code);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }
}
