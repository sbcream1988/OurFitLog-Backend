package com.ofl.global.security.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.ofl.domain.member.entity.Member;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails, OAuth2User{


	private static final long serialVersionUID = 1L;
	private final Member member;
	private Long id;
	private Map<String, Object> attributes;
	
	public CustomUserDetails(Member member, Long id) {
		this.member = member;
		this.id = id;
	}
	
	public CustomUserDetails(Member member, Map<String,Object> attributes ) {
		this.member = member;
		this.id = member.getId();
		this.attributes = attributes;
		
	}
	
	public Long getId() {
		return (this.id !=null) ? this.id : member.getId();
	}
	
	@Override
	public Map<String, Object> getAttributes(){
		return attributes;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return Collections.singleton(()->member.getRole().name());
	}
	
	@Override
	public String getPassword() {
		return member.getPassword();
	}
	
	@Override
	public String getUsername() {
		return member.getEmail();
	}
	
	@Override
	public String getName() {
		return member.getNickname();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override 
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
