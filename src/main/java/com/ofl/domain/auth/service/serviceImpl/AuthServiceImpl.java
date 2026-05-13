package com.ofl.domain.auth.service.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofl.domain.auth.dto.request.LoginRequestDto;
import com.ofl.domain.auth.dto.request.SignupRequestDto;
import com.ofl.domain.auth.dto.response.TokenResponseDto;
import com.ofl.domain.auth.service.service.AuthService;
import com.ofl.domain.member.entity.Member;
import com.ofl.domain.member.entity.Role;
import com.ofl.domain.member.repository.MemberRepository;
import com.ofl.global.entity.ProviderType;
import com.ofl.global.error.CustomException;
import com.ofl.global.error.ErrorCode;
import com.ofl.global.security.provider.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final MemberRepository memberRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public void signup(SignupRequestDto request) {
		if(memberRepository.existsByEmail(request.getEmail())) {
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}
		
		Member member = Member.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.nickname(request.getNickname())
				.provider(ProviderType.SYSTEM)
				.role(Role.USER)
				.build();
		
		memberRepository.save(member);
	}
	
	@Transactional(readOnly = true)
	public TokenResponseDto login(LoginRequestDto request) {
		Member member = memberRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		
		if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
			throw new CustomException(ErrorCode.INVALID_PARAMETER);
		}
		
		String accessToken = jwtTokenProvider.createToken(member);
		String refreshToken = jwtTokenProvider.createRefreshToken(member.getId());
		
		return new TokenResponseDto(accessToken, refreshToken);
	}
}
