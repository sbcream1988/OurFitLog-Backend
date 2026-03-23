package com.ofl.domain.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofl.domain.member.dto.request.MemberRequestDto;
import com.ofl.domain.member.dto.response.MemberResponseDto;
import com.ofl.domain.member.service.service.MemberService;
import com.ofl.global.error.CustomException;
import com.ofl.global.error.ErrorCode;
import com.ofl.global.security.service.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	// 내 정보 조회
	@GetMapping("/me")
	public ResponseEntity<MemberResponseDto> getMyInfo(@AuthenticationPrincipal CustomUserDetails userDetails){
		if(userDetails == null || userDetails.getId() ==null) {
			throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
		}
		MemberResponseDto response = memberService.getMyInfo(userDetails.getId());
		return ResponseEntity.ok(response);
	}
	
	// 내 정보 수정
	@PatchMapping("/me")
	public ResponseEntity<Void> updateMyInfo(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody MemberRequestDto request){
		memberService.updateMember(userDetails.getId(), request);
		return ResponseEntity.ok().build();
	}
	
	// 회원 탈퇴
	@DeleteMapping("/me")
	public ResponseEntity<Void> withdrawMember(@AuthenticationPrincipal CustomUserDetails userDetails){
		memberService.withdrawMember(userDetails.getId());
		return ResponseEntity.noContent().build();
	}
	
}
