package com.ofl.domain.chat.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ofl.domain.chat.dto.request.ChatMessageRequestDto;
import com.ofl.domain.chat.dto.request.ChatRoomRequestDto;
import com.ofl.domain.chat.dto.response.ChatMessageResponseDto;
import com.ofl.domain.chat.dto.response.ChatRoomResponseDto;
import com.ofl.domain.chat.service.service.ChatService;
import com.ofl.global.dto.response.ApiResponse;
import com.ofl.global.security.service.CustomUserDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/chat")
@Slf4j
public class ChatController {

	private final ChatService chatService;
	
	@MessageMapping("/chat/message")
	public void message(ChatMessageRequestDto message, StompHeaderAccessor accessor) {
		System.out.println("message = " + message);
		System.out.println("roomId = " + message.getRoomId());
	    String email = (String) accessor.getSessionAttributes().get("userEmail");

	    if (email == null) {

	        log.error("세션 속성에서도 유저 정보를 찾을 수 없습니다.");
	        return; 
	    }

	    System.out.println("채팅 전송 성공 - User: " + email);
	    chatService.sendMessage(message, email);
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
	
	@GetMapping("/rooms")
	@ResponseBody
	public ResponseEntity<ApiResponse<List<ChatRoomResponseDto>>>getRooms(@AuthenticationPrincipal CustomUserDetails userDetails){
		return ResponseEntity.ok(ApiResponse.success(chatService.getChatRooms(userDetails.getUsername())));
	}
	
	@PostMapping("/room/dm/{partnerId}")
	@ResponseBody
	public ResponseEntity<ApiResponse<Long>> createDmRoom (@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable("partnerId") Long partnerId){
		return ResponseEntity.ok(ApiResponse.success(chatService.createOneToOneChat(userDetails.getUsername(), partnerId)));
	}
	
	@DeleteMapping("/room/{roomId}")
	@ResponseBody
	public ResponseEntity<ApiResponse<Void>> leaveRoom(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable("roomId") Long roomId){
		chatService.leaveRoom(userDetails.getUsername(), roomId);
		
		return ResponseEntity.ok(ApiResponse.success(null));
		
	}
}
