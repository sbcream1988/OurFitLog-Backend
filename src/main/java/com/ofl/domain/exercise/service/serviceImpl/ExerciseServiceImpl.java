package com.ofl.domain.exercise.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofl.domain.exercise.dto.request.ExerciseRequestDto;
import com.ofl.domain.exercise.dto.response.ExerciseResponseDto;
import com.ofl.domain.exercise.entity.Exercise;
import com.ofl.domain.exercise.mapper.ExerciseMapper;
import com.ofl.domain.exercise.repository.ExerciseRepository;
import com.ofl.domain.exercise.service.service.ExerciseService;
import com.ofl.domain.post.entity.Post;
import com.ofl.global.error.CustomException;
import com.ofl.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService{
	
	private final ExerciseRepository exerciseRepository;
	private final ExerciseMapper exerciseMapper;

	// 생성
	@Override
	public Long createExercise(Post post, List<ExerciseRequestDto> exercises) {
		if(exercises == null || exercises.isEmpty()) {
			return 0L;
		}
		for(ExerciseRequestDto dto : exercises) {
			Exercise exercise = exerciseMapper.toEntity(dto);
			
			exercise.setPost(post);
			
			exerciseRepository.save(exercise);
		}
		
		return (long)exercises.size();
	}

	// 삭제
	@Override
	public void deleteExercise(Long id) {
		
		exerciseRepository.deleteById(id);
		
	}

	//조회(리스트)
	
	@Override
	@Transactional(readOnly = true)
	public List<ExerciseResponseDto> getExerciseList() {
		List<Exercise> exercises = exerciseRepository.findAll();
		return exerciseMapper.toDtoList(exercises);
	}

	// 조회(단건)
	@Override
	@Transactional(readOnly = true)
	public ExerciseResponseDto getExercise(Long id) {
		Exercise exercise = exerciseRepository.findById(id)
				.orElseThrow(() -> new CustomException(ErrorCode.EXERCISE_NOT_FOUND));
		return exerciseMapper.toDto(exercise);
	}

}
