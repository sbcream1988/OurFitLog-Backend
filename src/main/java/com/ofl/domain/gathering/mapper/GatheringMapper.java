package com.ofl.domain.gathering.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ofl.domain.gathering.dto.response.GatheringResponseDto;
import com.ofl.domain.gathering.entity.Gathering;

@Mapper(componentModel = "spring")
public interface GatheringMapper {


	@Mapping(target = "hostNickname", source = "host.nickname")
    @Mapping(target = "currentParticipationsCount", expression = "java(gathering.getParticipations().size())")
	GatheringResponseDto toDto(Gathering gathering);
}
