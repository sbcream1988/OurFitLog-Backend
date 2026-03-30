package com.ofl.domain.reply.dto.request;

import jakarta.validation.constraints.NotBlank;
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
public class ReplyCreateRequestDto {

	@NotBlank(message = "댓글은 필수 입력사항 입니다. 댓글 내용을 입력해주세요")
	private String content;
}
