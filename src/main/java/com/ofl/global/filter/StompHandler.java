package com.ofl.global.filter;

import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Map;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

import com.ofl.global.security.provider.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class StompHandler implements ChannelInterceptor {

	private final JwtTokenProvider provider;

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
	    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
	    
	    if (StompCommand.CONNECT == accessor.getCommand()) {
	        String jwt = accessor.getFirstNativeHeader("Authorization");
	        if (jwt != null && jwt.startsWith("Bearer ")) {
	            String token = jwt.substring(7);
	            if (provider.validateToken(token)) {
	                String email = provider.getAuthentication(token).getName();
	                
	                // ★ 여기가 핵심입니다. 세션 주머니에 이메일을 직접 넣습니다.
	                Map<String, Object> sessionAttributes = accessor.getSessionAttributes();
	                if (sessionAttributes != null) {
	                    sessionAttributes.put("userEmail", email);
	                }
	                
	                accessor.setUser(provider.getAuthentication(token));
	            }
	        }
	    }
	    return message;
	}
}
