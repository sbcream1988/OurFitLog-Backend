package com.ofl.domain.exercise.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ofl.domain.exercise.dto.request.ExerciseRequestDto;
import com.ofl.domain.exercise.dto.response.ExerciseResponseDto;
import com.ofl.domain.exercise.entity.Exercise;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
	
	
	ExerciseResponseDto toDto(Exercise exercise);
	
	List<ExerciseResponseDto> toDtoList(List<Exercise> exercises);

	Exercise toEntity(ExerciseRequestDto dto);
}