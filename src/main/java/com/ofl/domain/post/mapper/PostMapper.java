package com.ofl.domain.post.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ofl.domain.location.mapper.LocationMapper;
import com.ofl.domain.post.dto.request.PostCreateRequestDto;
import com.ofl.domain.post.dto.response.PostResponseDto;
import com.ofl.domain.post.entity.Post;

@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface PostMapper {

	@Mapping(source = "member.nickname", target = "nickname")
	PostResponseDto toDto(Post post);
	
	List<PostResponseDto> toDtoList(List<Post> posts);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "member", ignore = true)
	@Mapping(target = "exercise", ignore = true)
	@Mapping(target = "reply", ignore = true)
	@Mapping(target = "images", ignore = true)
	Post toEntity(PostCreateRequestDto dto);
}
