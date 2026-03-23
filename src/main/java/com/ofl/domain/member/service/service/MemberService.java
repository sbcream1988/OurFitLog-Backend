package com.ofl.domain.member.service.service;

import com.ofl.domain.member.dto.request.MemberRequestDto;
import com.ofl.domain.member.dto.response.MemberResponseDto;

public interface MemberService {
	
	MemberResponseDto getMyInfo(Long memberId);
	
	void updateMember(Long memberId, MemberRequestDto request);
	
	void withdrawMember(Long memberId);
}
