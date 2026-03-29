package com.ofl.domain.image.dto;


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
public class ImageResponseDto {

	private Long id;
	
	private String imageUrl;
	
	private String originName;
	
	private Long postId;
}
