package com.ofl.domain.chat.dto.request;

import com.ofl.domain.chat.entity.ChatRoom;
import com.ofl.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatParticipationRequestDto {

	private ChatRoom chatRoom;
	
	private Member member;
}
