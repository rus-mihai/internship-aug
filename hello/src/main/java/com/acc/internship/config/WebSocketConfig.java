package com.acc.internship.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/admin/realtime").withSockJS();
		registry.addEndpoint("/admin/messages").withSockJS();
		
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config){

		config.enableSimpleBroker("/admin/topic");
		config.setApplicationDestinationPrefixes("/app");
	}
	
	

}

