package com.ofl.domain.participation.service.service;

import com.ofl.domain.member.entity.Member;

public interface ParticipationService {

	Long attend(Long gatheringId, Member member);

	void cancel(Long gatheringId, Member member);
}
