package com.ofl.domain.user.entity;

import com.ofl.global.entity.BaseTime;

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
@Table(name = "users")
public class User extends BaseTime {

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
	private Provider provider;
	
	private String providerId;
	
	@Builder
	public User(String email, String nickname, Role role, Provider provider, String providerId) {
		this.email = email;
		this.nickname = nickname;
		this.role = role;
		this.provider = provider;
		this.providerId = providerId;
	}
	
	public User update(String nickname) {
		this.nickname = nickname;
		return this;
	}
}
