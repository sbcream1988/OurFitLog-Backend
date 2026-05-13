package com.ofl.domain.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofl.domain.auth.dto.request.LoginRequestDto;
import com.ofl.domain.auth.dto.request.SignupRequestDto;
import com.ofl.domain.auth.dto.response.TokenResponseDto;
import com.ofl.domain.auth.service.service.AuthService;
import com.ofl.global.dto.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<Void>> signup(@RequestBody SignupRequestDto request){
		authService.signup(request);
		return ResponseEntity.ok(ApiResponse.success(null));
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<TokenResponseDto>> login(@RequestBody LoginRequestDto request ){
		
		return ResponseEntity.ok(ApiResponse.success(authService.login(request)));
	}
}
