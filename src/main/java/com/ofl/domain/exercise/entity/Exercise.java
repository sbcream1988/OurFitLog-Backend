package com.ofl.domain.exercise.entity;

import com.ofl.domain.post.entity.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
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
	
	private Integer weight;
	
	private Integer sets;
	
	private Integer reps;
	
	@Column(columnDefinition = "TEXT")
	private String memo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;
	
	@Builder
	public Exercise(ExerciseType exerciseType, String exerciseName, Integer weight, Integer sets, Integer reps, String memo) {
		this.exerciseType = exerciseType;
		this.exerciseName = exerciseName;
		this.weight = weight;
		this.sets = sets;
		this.reps = reps;
		this.memo = memo;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}

}
