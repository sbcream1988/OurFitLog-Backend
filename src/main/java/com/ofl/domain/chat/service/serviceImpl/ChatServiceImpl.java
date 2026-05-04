package com.ofl.domain.chat.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofl.domain.chat.dto.request.ChatMessageRequestDto;
import com.ofl.domain.chat.dto.request.ChatRoomRequestDto;
import com.ofl.domain.chat.dto.response.ChatMessageResponseDto;
import com.ofl.domain.chat.dto.response.ChatRoomResponseDto;
import com.ofl.domain.chat.entity.ChatMessage;
import com.ofl.domain.chat.entity.ChatParticipation;
import com.ofl.domain.chat.entity.ChatRoom;
import com.ofl.domain.chat.entity.ChatType;
import com.ofl.domain.chat.mapper.ChatMapper;
import com.ofl.domain.chat.repository.ChatMessageRepository;
import com.ofl.domain.chat.repository.ChatParticipationRepository;
import com.ofl.domain.chat.repository.ChatRoomRepository;
import com.ofl.domain.chat.service.service.ChatService;
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
	private final ChatParticipationRepository chatParticipationRepository;
	
	@Override
	public Long createChatRoom(ChatRoomRequestDto dto) {
		ChatRoom room = ChatRoom.builder()
				.name(dto.getName())
				.type(dto.getType())
				.build();
		
		return chatRoomRespository.save(room).getId();
	}
	
	@Override
	public void sendMessage(ChatMessageRequestDto dto, String email) {
		ChatRoom room = chatRoomRespository.findById(dto.getRoomId())
				.orElseThrow(() -> new CustomException(ErrorCode.CHATROOM_NOT_FOUND));
		
		Member sender = memberRepository.findByEmail(email)
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		
		ChatMessage chatMessage = ChatMessage.builder()
				.chatRoom(room)
				.sender(sender)
				.message(dto.getMessage())
				.build();
		
		ChatMessage savedMessage = chatMessageRepository.save(chatMessage);
		ChatMessageResponseDto responseDto = chatMapper.toMessageDto(savedMessage);
		
		
		simpMessagingTemplate.convertAndSend("/sub/chat/room/" + responseDto.getRoomId(), responseDto);
				
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ChatMessageResponseDto> getChatHistory(Long roomId){
		List<ChatMessage> messages = chatMessageRepository.findByChatRoomIdOrderByCreatedAtAsc(roomId);
		return chatMapper.toMessageDtoList(messages);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ChatRoomResponseDto> getChatRooms(String email){;
		Member me = memberRepository.findByEmail(email)
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		
		List<ChatRoom> rooms = chatParticipationRepository.findByMember(me).stream()
				.map(participation -> participation.getChatRoom())
				.toList();
		
		List<ChatRoomResponseDto> roomDtoList = chatMapper.toRoomDtoList(rooms);
		
		for(int i = 0; i <rooms.size(); i++ ) {
			ChatRoom room = rooms.get(i);
			ChatRoomResponseDto dto = roomDtoList.get(i);
			
			if(room.getType() == ChatType.ONETOONE) {
				String partnerName = room.getName()
						.replace(me.getNickname(), "")
						.replace(",","")
						.trim();
				
				dto.setName(partnerName);
			}
		}
		
		return roomDtoList;
	}
	
	//개인
	@Override
	public Long createOneToOneChat(String myEmail, Long partnerId) {
		Member me = memberRepository.findByEmail(myEmail)
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		
		Member partner = memberRepository.findById(partnerId)
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		
		Optional<ChatRoom> existingRoom = chatRoomRespository.findOneToOneRoom(me.getId(), partner.getId());
		
		if(existingRoom.isPresent()) {
			return existingRoom.get().getId();
		}
		
		ChatRoom room = ChatRoom.builder()
				.name(me.getNickname() + ", " + partner.getNickname())
				.type(ChatType.ONETOONE)
				.build();
		ChatRoom savedRoom = chatRoomRespository.save(room);
		
		ChatParticipation myParticipation = ChatParticipation.builder()
				.chatRoom(savedRoom)
				.member(me)
				.build();
		ChatParticipation partnerParticipation = ChatParticipation.builder()
				.chatRoom(savedRoom)
				.member(partner)
				.build();
		
		chatParticipationRepository.save(myParticipation);
		chatParticipationRepository.save(partnerParticipation);
		
		return savedRoom.getId();
	}
	
	@Override
	@Transactional
	public void leaveRoom(String email, Long roomId) {
		Member me = memberRepository.findByEmail(email)
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		
//		ChatParticipation participation = chatParticipationRepository.findByMemberAndChatRoomId(me,roomId)
//				.orElseThrow(() -> new CustomException(ErrorCode.PARTICIPATION_NOT_FOUD));
//		
//		chatParticipationRepository.delete(participation);
		
		chatParticipationRepository.deleteByMemberAndChatRoomId(me, roomId);
		
		if(chatParticipationRepository.countByChatRoomId(roomId) == 0 ) {
			chatMessageRepository.deleteByChatRoomId(roomId);
			chatRoomRespository.deleteById(roomId);
		}
		
		
	}
}
