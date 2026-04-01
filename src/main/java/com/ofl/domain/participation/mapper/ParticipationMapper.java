package com.ofl.domain.participation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ofl.domain.participation.dto.response.ParticipationResponseDto;
import com.ofl.domain.participation.entity.Participation;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {

	@Mapping(target = "memberNickname", source = "member.nickname")
	ParticipationResponseDto toDto(Participation participation);
}
