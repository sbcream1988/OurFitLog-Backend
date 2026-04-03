package com.ofl.domain.chat.service.serviceImpl;

import java.util.List;

import com.ofl.domain.chat.dto.request.ChatMessageRequestDto;
import com.ofl.domain.chat.dto.response.ChatMessageResponseDto;
import com.ofl.domain.chat.entity.ChatType;

public interface ChatService {

	Long createChatRoom(String name, ChatType type);
	
	void sendMessage(ChatMessageRequestDto dto);

	List<ChatMessageResponseDto> getChatHistory(Long roomId);
}
