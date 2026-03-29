package com.ofl.domain.image.entity;

import org.hibernate.annotations.GeneratorType;

import com.ofl.domain.post.entity.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Getter
@NoArgsConstructor
@Table(name = "image")
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(nullable = false)
	private String imageUrl;
	
	@Column(nullable = false)
	private String originName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;
	
	public void setPost(Post post) {
		this.post = post;
	}
	
	@Builder
	public Image(String imageUrl, String originName, Post post) {
		this.imageUrl = imageUrl;
		this.originName = originName;
		this.post = post;
	}
	
}
