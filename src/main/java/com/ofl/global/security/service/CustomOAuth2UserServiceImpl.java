package com.ofl.global.security.service;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.ofl.domain.user.entity.Provider;
import com.ofl.domain.user.entity.Role;
import com.ofl.domain.user.entity.Member;
import com.ofl.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserServiceImpl extends DefaultOAuth2UserService {

	private final UserRepository userRepository;
	
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		Map<String, Object> attributes = oAuth2User.getAttributes();
		Map<String, Object> kakaoAccount = (Map<String,Object>) attributes.get("kakao_account");
		Map<String, Object> profile = (Map<String,Object>) kakaoAccount.get("profile");
		
		String email = (String) kakaoAccount.get("email");
		String nickname = (String) profile.get("nickname");
		String providerId = attributes.get("id").toString();
		
		Member user = saveOrUpdate(email, nickname, registrationId, providerId);
		
		return oAuth2User;
	}
	
	private Member saveOrUpdate(String email, String nickname, String provider, String providerId) {
		Member user = userRepository.findByEmail(email)
				.map(entity -> entity.update(nickname))
				.orElse(Member.builder()
						.email(email)
						.nickname(nickname)
						.provider(Provider.valueOf(provider.toUpperCase()))
						.providerId(providerId)
						.role(Role.USER)
						.build());
		
		return userRepository.save(user);
	}
	
}
