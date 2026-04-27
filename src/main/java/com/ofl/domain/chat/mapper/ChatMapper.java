package com.ofl.domain.chat.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ofl.domain.chat.dto.response.ChatMessageResponseDto;
import com.ofl.domain.chat.dto.response.ChatRoomResponseDto;
import com.ofl.domain.chat.entity.ChatMessage;
import com.ofl.domain.chat.entity.ChatRoom;

@Mapper(componentModel = "spring")
public interface ChatMapper {

	@Mapping(source = "id", target="roomId")
	ChatRoomResponseDto toRoomDto(ChatRoom chatRoom);
	
	@Mapping(source = "sender.nickname", target = "senderNickname")
	@Mapping(source = "chatRoom.id", target = "roomId")
	@Mapping(source = "sender.id", target = "senderId")
	ChatMessageResponseDto toMessageDto(ChatMessage chatMessage);
	
	List<ChatRoomResponseDto> toRoomDtoList(List<ChatRoom> chatRooms);
	List<ChatMessageResponseDto> toMessageDtoList(List<ChatMessage> chatMessages);
	

}
