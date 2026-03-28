package com.ofl.domain.location.entity;

import com.ofl.domain.post.entity.Post;
import com.ofl.global.entity.ProviderType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ProviderType provider;

	private String providerId;
	
	private String placeName;
	
	private Double longitude;

	private Double latitude;

	private String address;

	@OneToOne
	@JoinColumn(name = "post_id")
	private Post post;
	
	public void setPost(Post post) {
		this.post = post;
	}
}
