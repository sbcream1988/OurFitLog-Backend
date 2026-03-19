package com.ofl.domain.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofl.domain.exercise.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>{

}
