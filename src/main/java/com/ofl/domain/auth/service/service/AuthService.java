package com.ofl.domain.auth.service.service;

import com.ofl.domain.auth.dto.request.LoginRequestDto;
import com.ofl.domain.auth.dto.request.SignupRequestDto;
import com.ofl.domain.auth.dto.response.TokenResponseDto;

public interface AuthService {

	void signup(SignupRequestDto request);

	TokenResponseDto login(LoginRequestDto request);
	
	

}
