package com.ofl.domain.participation.service.service;

public interface ParticipationService {

	Long attend(Long gatheringId, Long memberId);

	void cancel(Long gatheringId, Long memberId);
}
