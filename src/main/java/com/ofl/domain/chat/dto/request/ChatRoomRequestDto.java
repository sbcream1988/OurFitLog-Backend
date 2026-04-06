package com.ofl.domain.chat.dto.request;

import com.ofl.domain.chat.entity.ChatType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomRequestDto {
	private String name;
	private ChatType type;

}
