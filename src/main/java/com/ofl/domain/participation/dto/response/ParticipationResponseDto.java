package com.ofl.domain.participation.dto.response;

import java.time.LocalDateTime;

import com.ofl.global.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationResponseDto {

	private Long id;
	
	private String memberNickname;
	
	private Status status;
	
	private LocalDateTime createdAt;
}
