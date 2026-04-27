package com.ofl.domain.exercise.service.service;

import java.util.List;

import com.ofl.domain.exercise.dto.request.ExerciseRequestDto;
import com.ofl.domain.exercise.dto.response.ExerciseResponseDto;
import com.ofl.domain.post.entity.Post;

public interface ExerciseService {

	// 운동 기록 삽입
	Long createExercise(Post post , List<ExerciseRequestDto> exercises);
	
	// 삭제
	void deleteExercise(Long id);
	
	// 조회
	// 페이징으로?
	List<ExerciseResponseDto> getExerciseList();
	
	ExerciseResponseDto getExercise(Long id);
	
	// 수정(차후 추가)
}
