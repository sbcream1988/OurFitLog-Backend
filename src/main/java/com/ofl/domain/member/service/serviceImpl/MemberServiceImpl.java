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

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberRepository memberRepository;
	
	@Override
	public MemberResponseDto getMyInfo(Long memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
		
		return new MemberResponseDto(member.getId(), member.getEmail(), member.getNickname());
	}
	
	@Override
	@Transactional
	public void updateMember(Long memberId, MemberRequestDto request) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
		
		member.update(request.nickname());
	}
	
	@Override
	@Transactional
	public void withdrawMember(Long memberId) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		
		member.withDraw();
	}
}
