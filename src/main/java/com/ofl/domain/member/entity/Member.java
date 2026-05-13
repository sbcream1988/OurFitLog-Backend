package com.ofl.domain.member.entity;

import org.hibernate.annotations.SQLRestriction;

import com.ofl.global.entity.BaseTime;
import com.ofl.global.entity.ProviderType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "members")
@SQLRestriction("is_deleted = false")
public class Member extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique=true)
	private String email;
	
	private String password;
	
	private String nickname;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "provider", length = 20)
	private ProviderType provider;
	
	private String providerId;
	
	@Builder
	public Member(String email, String password, String nickname, Role role, ProviderType provider, String providerId) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.role = role;
		this.provider = provider;
		this.providerId = providerId;
	}
	
	public Member update(String nickname) {
		this.nickname = nickname;
		return this;
	}
	
	private boolean isDeleted = false;
	
	public void withDraw() {
		this.isDeleted = true;
	}
}
