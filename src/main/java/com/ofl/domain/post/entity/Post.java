package com.ofl.domain.post.entity;

import java.util.ArrayList;
import java.util.List;

import com.ofl.domain.exercise.entity.Exercise;
import com.ofl.domain.image.entity.Image;
import com.ofl.domain.location.entity.Location;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "post")
public class Post extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String content;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Exercise> exercises;

	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private List<Reply> reply;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private Location location;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<Image> images = new ArrayList<>();
	
	public void addLocation(Location location) {
		this.location = location;
		location.setPost(this);
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
	
	public void addImage(Image image) {
		this.images.add(image);
		image.setPost(this);
	}
}
