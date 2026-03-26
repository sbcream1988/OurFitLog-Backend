package com.ofl.domain.member.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofl.domain.member.dto.request.MemberRequestDto;
import com.ofl.domain.member.dto.response.MemberResponseDto;
import com.ofl.domain.member.entity.Member;
import com.ofl.domain.member.repository.MemberRepository;
import com.ofl.domain.member.service.service.MemberService;
import com.ofl.global.error.CustomException;
import com.ofl.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository;
	
	@Override
	@Transactional(readOnly = true)
	public MemberResponseDto getMyInfo(Long memberId) {
		return memberRepository.findById(memberId)
				.map(member -> {
					log.debug("[MemberService] DB 조회 성공 - ID: {}", memberId);
					return new MemberResponseDto(member.getId(), member.getEmail(), member.getNickname());
				})
				.orElseThrow(()->{
					log.error("[MemberService] 사용자 조회 실패 - 존재하지 않는 사용자입니다");
					return new CustomException(ErrorCode.USER_NOT_FOUND);
				});
	}
	
	@Override
	@Transactional
	public void updateMember(Long memberId, MemberRequestDto request) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(()-> {
					log.error("[MemberService] 수정 실패 -유저 없음 ID: {}", memberId);
					return new CustomException(ErrorCode.USER_NOT_FOUND);});
		
		String oldNickname = member.getNickname();
		member.update(request.nickname());
		log.info("[MemberService] 닉네임 변경: {} -> {}", oldNickname, request.nickname());
	}
	
	@Override
	@Transactional
	public void withdrawMember(Long memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> {
					log.error("[MemberService] 탈퇴 실패 - 유저 없음 ID: {}", memberId);
					return new CustomException(ErrorCode.USER_NOT_FOUND);});
		
		member.withDraw();
		log.info("[MemberService] 회원 Soft Delete 완료 - ID: {}", memberId);
	}
}
