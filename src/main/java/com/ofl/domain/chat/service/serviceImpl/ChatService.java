package com.ofl.domain.chat.service.serviceImpl;

import java.util.List;

import com.ofl.domain.chat.dto.request.ChatMessageRequestDto;
import com.ofl.domain.chat.dto.request.ChatRoomRequestDto;
import com.ofl.domain.chat.dto.response.ChatMessageResponseDto;

public interface ChatService {
	
	Long createChatRoom(ChatRoomRequestDto dto);
	
	void sendMessage(ChatMessageRequestDto dto);

	List<ChatMessageResponseDto> getChatHistory(Long roomId);

	
}
