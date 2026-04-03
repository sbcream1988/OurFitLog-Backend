package com.ofl.domain.chat.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageResponseDto {
	private Long roomId;
	private String senderNickname;
	private String message;
	private String createdAt;
}
