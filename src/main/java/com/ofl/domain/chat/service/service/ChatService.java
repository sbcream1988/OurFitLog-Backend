package com.ofl.domain.chat.service.service;

import java.util.List;

import com.ofl.domain.chat.dto.request.ChatMessageRequestDto;
import com.ofl.domain.chat.dto.request.ChatRoomRequestDto;
import com.ofl.domain.chat.dto.response.ChatMessageResponseDto;
import com.ofl.domain.chat.dto.response.ChatRoomResponseDto;

public interface ChatService {

	Long createChatRoom(ChatRoomRequestDto dto);

	void sendMessage(ChatMessageRequestDto dto);

	List<ChatMessageResponseDto> getChatHistory(Long roomId);

	Long createOneToOneChat(String myEmail, Long partnerId);

	List<ChatRoomResponseDto> getChatRooms(String email);

}
