package com.ofl.global.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.ofl.global.dto.response.NotificationResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
	
	private final SimpMessagingTemplate messagingTemplate;

	@Override
	public void sendNotification(String userId, NotificationResponseDto notification) {

		String destination = "/sub/user/" + userId + "/notifications";
		
		messagingTemplate.convertAndSend(destination, notification);
	}
}
