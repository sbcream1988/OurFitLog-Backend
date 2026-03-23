package com.ofl.global.security.provider;

import java.security.Key;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.ofl.domain.member.entity.Member;
import com.ofl.domain.member.entity.Role;
import com.ofl.global.security.service.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	private final Key key;
	private final long accessTokenExpirationTime;
	
	public JwtTokenProvider(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration_time}") long expirationTime) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
		this.accessTokenExpirationTime = expirationTime;
		
		
	}
	
	public String createToken(Member user) {
		long now = System.currentTimeMillis();
		Date expiry = new Date(now + accessTokenExpirationTime);
		
		return Jwts.builder()
				.setSubject(String.valueOf(user.getId()))
				.claim("email", user.getEmail())
				.claim("role", user.getRole().name())
				.claim("type", "access")
				.setIssuedAt(new Date(now))
				.setExpiration(expiry)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	public Long getUserId(String token) {
		return Long.parseLong(Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject());
			
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	
	public String createRefreshToken(Long userId) {
		long now = System.currentTimeMillis();
		Date expiry = new Date(now + 1000 * 60 * 60 * 24 * 7);
		
		return Jwts.builder()
				.setSubject(String.valueOf(userId))
				.claim("type", "refresh")
				.setIssuedAt(new Date(now))
				.setExpiration(expiry)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	public boolean validateRefreshToken(String token) {
		try {
			var claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
			return "refresh".equals(claims.get("type"));
		}catch (Exception e) {
			return false;
		}
	}
	
	public Authentication getAuthentication(String token) {
		
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		Long userId = Long.parseLong(claims.getSubject());
		String email = claims.get("email", String.class);
		String roleName = claims.get("role", String.class);
		
		Member member = Member.builder()
				.email(email)
				.role(Role.valueOf(roleName))
				.build();
		
		CustomUserDetails principal = new CustomUserDetails(member,userId);
		
		List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + roleName));
		
		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}
}
