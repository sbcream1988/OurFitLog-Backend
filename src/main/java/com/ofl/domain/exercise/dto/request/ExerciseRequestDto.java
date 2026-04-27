package com.ofl.domain.exercise.dto.request;

import com.ofl.domain.exercise.entity.ExerciseType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseRequestDto {

	ExerciseType exerciseType;
	
	String exerciseName;
	
	Integer weight;
	
	Integer sets;
	
	Integer reps;
	
	String memo;
}
