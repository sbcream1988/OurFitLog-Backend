package com.ofl.domain.post.dto.request;

import com.ofl.domain.location.dto.request.LocationRequestDto;

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
public class PostCreateRequestDto {
	private String title;
	
	private String content;
	
	private LocationRequestDto location;
}
