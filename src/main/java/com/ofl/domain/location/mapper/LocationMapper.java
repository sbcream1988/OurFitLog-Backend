package com.ofl.domain.location.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ofl.domain.location.dto.request.LocationRequestDto;
import com.ofl.domain.location.dto.response.LocationResponseDto;
import com.ofl.domain.location.entity.Location;

@Mapper(componentModel = "spring")
public interface LocationMapper {
	
	LocationResponseDto toDto(Location location);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "post", ignore = true)
	Location toEntity(LocationRequestDto dto);
}
