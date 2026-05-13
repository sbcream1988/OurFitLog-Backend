package com.ofl.domain.post.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.ofl.domain.exercise.dto.response.ExerciseResponseDto;
import com.ofl.domain.location.dto.response.LocationResponseDto;

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
public class PostResponseDto {
	private Long id;
	private String title; 
	private String content; 
	private String nickname;
	private Long memberId;
	private LocationResponseDto location;
	private LocalDateTime createdAt;
	private List<ExerciseResponseDto> exercises;
}
