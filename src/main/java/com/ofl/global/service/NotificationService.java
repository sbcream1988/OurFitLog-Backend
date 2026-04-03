package com.ofl.global.service;

import com.ofl.global.dto.response.NotificationResponseDto;

public interface NotificationService {
	
	void sendNotification(String userId, NotificationResponseDto notify);
}
