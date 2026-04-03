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
import com.ofl.global.dto.response.ApiResponse;
import com.ofl.global.error.CustomException;
import com.ofl.global.error.ErrorCode;
import com.ofl.global.security.service.CustomUserDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	// 내 정보 조회
	@GetMapping("/me")
	public ResponseEntity<ApiResponse<MemberResponseDto>> getMyInfo(@AuthenticationPrincipal CustomUserDetails userDetails){
		log.info("[MemberController] 내 정보 조회 요청 - Member ID:{}",userDetails.getId());
		if(userDetails == null || userDetails.getId() ==null) {
			log.warn("[MemberController] 인증 정보 없음 - 접근 거부");
			throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
		}
		MemberResponseDto response = memberService.getMyInfo(userDetails.getId());
		log.info("[MemberController] 내 정보 조회 성공 - Email : {}", response.getEmail());
		return ResponseEntity.ok(ApiResponse.success(response));
	}
	
	// 내 정보 수정
	@PatchMapping("/me")
	public ResponseEntity<Void> updateMyInfo(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody MemberRequestDto request){
		log.info("[MemberController] 정보 수정 요청 - Member ID: {} , New Nickname : {}", userDetails.getId(), request.getNickname());
		memberService.updateMember(userDetails.getId(), request);
		log.info("[MemberController] 정보 수정 완료 - Member ID: {}", userDetails.getId());
		return ResponseEntity.ok().build();
	}
	
	// 회원 탈퇴
	@DeleteMapping("/me")
	public ResponseEntity<Void> withdrawMember(@AuthenticationPrincipal CustomUserDetails userDetails){
		log.info("[MemberController] 회원 탈퇴 요청 - Member ID: {}", userDetails.getId());
		memberService.withdrawMember(userDetails.getId());
		log.info("[MemberController] 회원 탈퇴 성공 - Member ID: {}", userDetails.getId());
		return ResponseEntity.noContent().build();
	}
	
}
