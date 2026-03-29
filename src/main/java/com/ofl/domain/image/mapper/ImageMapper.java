package com.ofl.domain.image.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ofl.domain.image.dto.ImageResponseDto;
import com.ofl.domain.image.entity.Image;

@Mapper(componentModel = "spring")
public interface ImageMapper {

	@Mapping(source = "post.id", target = "postId")
	ImageResponseDto toResponseDto(Image image);
	
	List<ImageResponseDto> toResponseDtoList(List<Image> images);
	
}
