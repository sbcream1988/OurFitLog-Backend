package com.ofl.domain.reply.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ofl.domain.reply.dto.request.ReplyCreateRequestDto;
import com.ofl.domain.reply.dto.response.ReplyResponseDto;
import com.ofl.domain.reply.entity.Reply;

@Mapper(componentModel = "spring")
public interface ReplyMapper {

	@Mapping(source = "member.nickname", target = "nickname")
	@Mapping(source = "member.email", target = "email")
	ReplyResponseDto toDto(Reply reply);
	

	@Mapping(target = "member", ignore = true)
	@Mapping(target = "post", ignore = true)	
	Reply toEntity(ReplyCreateRequestDto dto);
	
}
