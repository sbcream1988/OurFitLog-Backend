package com.ofl.domain.chat.dto.request;

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
public class ChatMessageRequestDto {

	private Long roomId;
	
	private String sender;
	
	private String message;
}
