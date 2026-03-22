package com.ofl.global.security.handler;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ofl.domain.member.entity.Member;
import com.ofl.domain.member.repository.UserRepository;
import com.ofl.global.security.provider.JwtTokenProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication
			) throws IOException, ServletException{
		
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		
		String email = (String) ((Map<?, ?>) oAuth2User.getAttributes().get("kakao_account")).get("email");
		Member user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("유저를 찾을수 없습니다"));
		
		String accessToken = jwtTokenProvider.createToken(user);
		String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());
		
		String targetUrl = "http://localhost:5173/login-success?token=" + accessToken + "&refresh=" + refreshToken;
		
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

}
