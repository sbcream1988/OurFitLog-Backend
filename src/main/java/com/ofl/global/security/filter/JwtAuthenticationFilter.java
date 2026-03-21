package com.ofl.global.security.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ofl.global.security.provider.JwtTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain) throws ServletException, IOException{
		String header = request.getHeader("Authorization");
		
		if(header == null || !header.startsWith("Bearer ")) {
			filterchain.doFilter(request, response);
			return;
		}
		
		String token = header.substring(7);
		
		try {
			if(jwtTokenProvider.validateToken(token)) {
				Authentication auth = jwtTokenProvider.getAuthentication(token);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}catch(Exception e) {
			SecurityContextHolder.clearContext();
		}
		filterchain.doFilter(request, response);
	}
}
