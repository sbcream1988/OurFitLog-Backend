package com.ofl.domain.chat.service.service;

import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofl.domain.chat.dto.request.ChatMessageRequestDto;
import com.ofl.domain.chat.dto.response.ChatMessageResponseDto;
import com.ofl.domain.chat.entity.ChatMessage;
import com.ofl.domain.chat.entity.ChatRoom;
import com.ofl.domain.chat.entity.ChatType;
import com.ofl.domain.chat.mapper.ChatMapper;
import com.ofl.domain.chat.repository.ChatMessageRepository;
import com.ofl.domain.chat.repository.ChatRoomRepository;
import com.ofl.domain.chat.service.serviceImpl.ChatService;
import com.ofl.domain.member.entity.Member;
import com.ofl.domain.member.repository.MemberRepository;
import com.ofl.global.error.CustomException;
import com.ofl.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatServiceImpl implements ChatService{

	private final ChatRoomRepository chatRoomRespository;
	private final ChatMessageRepository chatMessageRepository;
	private final MemberRepository memberRepository;
	private final ChatMapper chatMapper;
	private final SimpMessagingTemplate simpMessagingTemplate;
	
	@Override
	public Long createChatRoom(String name, ChatType type) {
		ChatRoom room = ChatRoom.builder()
				.name(name)
				.type(type)
				.build();
		
		return chatRoomRespository.save(room).getId();
	}
	
	@Override
	public void sendMessage(ChatMessageRequestDto dto) {
		ChatRoom room = chatRoomRespository.findById(dto.getRoomId())
				.orElseThrow(() -> new CustomException(ErrorCode.CHATROOM_NOT_FOUND));
		
		Member sender = memberRepository.findByEmail(dto.getSender())
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		
		ChatMessage chatMessage = ChatMessage.builder()
				.chatRoom(room)
				.sender(sender)
				.message(dto.getMessage())
				.build();
		
		ChatMessage savedMessage = chatMessageRepository.save(chatMessage);
		ChatMessageResponseDto responseDto = chatMapper.toMessageDto(savedMessage);
		
		
		simpMessagingTemplate.convertAndSend("/sub/chat/room" + responseDto.getRoomId(), responseDto);
				
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ChatMessageResponseDto> getChatHistory(Long roomId){
		List<ChatMessage> messages = chatMessageRepository.findByChatRoomIdOrderByCreatedAtAsc(roomId);
		return chatMapper.toMessageDtoList(messages);
	}
}
