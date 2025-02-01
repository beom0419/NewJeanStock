package com.sascom.stockpricebackend.application.client.config;

import com.sascom.stockpricebackend.application.client.handler.ClientWebSocketHandlerDecorator;
import com.sascom.stockpricebackend.application.kis.properties.TrName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class ClientWebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stock-info")
                .setAllowedOriginPatterns("*").withSockJS();
        registry.addEndpoint("/stock-info")
                .setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker(
                TrName.REALTIME_HOKA.getDest(),
                TrName.REALTIME_PURCHASE.getDest()
        );
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.addDecoratorFactory(this::clientWebSocketHandlerDecorator);
    }

    @Bean
    public WebSocketHandlerDecorator clientWebSocketHandlerDecorator(
            @Qualifier("subProtocolWebSocketHandler")
            WebSocketHandler webSocketHandler) {
        return new ClientWebSocketHandlerDecorator(webSocketHandler);
    }
}
