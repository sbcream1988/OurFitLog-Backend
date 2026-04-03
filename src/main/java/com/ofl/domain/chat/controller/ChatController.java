package com.ofl.domain.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ofl.domain.chat.dto.request.ChatMessageRequestDto;
import com.ofl.domain.chat.service.serviceImpl.ChatService;

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
}
