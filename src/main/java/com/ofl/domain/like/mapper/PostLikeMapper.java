package com.ofl.domain.like.mapper;

import org.mapstruct.Mapper;

import com.ofl.domain.like.dto.response.PostLikeResponseDto;

@Mapper(componentModel = "spring")
public interface PostLikeMapper {
	default PostLikeResponseDto toDto(boolean isLiked, Long count) {
		return PostLikeResponseDto.builder()
				.isLiked(isLiked)
				.likeCount(count)
				.build();
	}
}
