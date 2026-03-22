package com.ofl.domain.post.entity;

import java.util.List;

import com.ofl.domain.exercise.entity.Exercise;
import com.ofl.domain.member.entity.Member;
import com.ofl.domain.reply.entity.Reply;
import com.ofl.global.entity.BaseTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String content;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Exercise> exercise;

	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private List<Reply> reply;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Member user;
}
