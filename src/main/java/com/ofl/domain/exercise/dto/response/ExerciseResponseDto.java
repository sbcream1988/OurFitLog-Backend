package com.ofl.domain.exercise.dto.response;

import com.ofl.domain.exercise.entity.ExerciseType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseResponseDto {

	Long id;
	
	ExerciseType exerciseType;
	
	String exerciseName;
	
	Integer weight;
	
	Integer sets;
	
	Integer reps;
	
	String memo;
	
}
