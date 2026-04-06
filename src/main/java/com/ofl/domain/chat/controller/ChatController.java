package com.ofl.domain.chat.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ofl.domain.chat.dto.request.ChatMessageRequestDto;
import com.ofl.domain.chat.dto.request.ChatRoomRequestDto;
import com.ofl.domain.chat.dto.response.ChatMessageResponseDto;
import com.ofl.domain.chat.service.serviceImpl.ChatService;
import com.ofl.global.dto.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

	private final ChatService chatService;
	
	@MessageMapping("/chat/message")
	public void message(ChatMessageRequestDto message) {
		chatService.sendMessage(message);
	}
	
	@GetMapping("/rooms/{roomId}/messages")
	@ResponseBody
	public ResponseEntity<ApiResponse<List<ChatMessageResponseDto>>> getHistory(@PathVariable("roomId") Long roomId){
		return ResponseEntity.ok(ApiResponse.success(chatService.getChatHistory(roomId)));
	}
	
	@PostMapping("/room")
	@ResponseBody
	public ResponseEntity<ApiResponse<Long>> createRoom(@RequestBody ChatRoomRequestDto dto){
		return ResponseEntity.ok(ApiResponse.success(chatService.createChatRoom(dto)));
	}
}
