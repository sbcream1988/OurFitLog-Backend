package com.ofl.domain.exercise.entity;

import com.ofl.domain.post.entity.Post;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "exercise")
public class Exercise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ExerciseType exerciseType;
	
	private String exerciseName;
	
	private int weight;
	
	private int sets;
	
	private int reps;
	
	private String memo;
	
	@ManyToOne
	@JoinColumn(name = "post_id")
	private Post post;

}
