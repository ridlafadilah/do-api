package com.dongkap.notification.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer  {

    @Value("${server.servlet.context-path}")
    private String destinationPrefix;

    @Value("${websocket.granted.hosts}")
    private String[] allowedCrossOrigin;

    @Value("${websocket.root.endpoint}")
    private String websocketRootEndpoint;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry broker) {
    		broker.enableSimpleBroker("/public", "/tasklist", "/report", "/chat");
    		broker.setApplicationDestinationPrefixes(destinationPrefix);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(websocketRootEndpoint).setAllowedOrigins(allowedCrossOrigin);
    }

}