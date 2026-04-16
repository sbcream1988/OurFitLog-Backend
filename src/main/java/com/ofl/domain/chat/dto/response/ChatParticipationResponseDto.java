package com.ofl.domain.chat.dto.response;

import com.ofl.domain.chat.entity.ChatRoom;
import com.ofl.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatParticipationResponseDto {

	private Long id;
	
	private ChatRoom chatRoom;
	
	private Member member;
}
