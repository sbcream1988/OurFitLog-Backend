package com.ofl.domain.reply.dto.response;

import java.time.LocalDateTime;

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
public class ReplyResponseDto {

	private Long id;
	private String content;
	private String nickname;
	private String email;
	private LocalDateTime createdAt;
}
