package com.ofl.domain.member.mapper;

import org.mapstruct.Mapper;

import com.ofl.domain.member.dto.request.MemberRequestDto;
import com.ofl.domain.member.dto.response.MemberResponseDto;
import com.ofl.domain.member.entity.Member;

@Mapper(componentModel = "spring")
public interface MemberMapper {

	MemberResponseDto toDto(Member member);
	
	Member toEntity(MemberRequestDto dto);
}
