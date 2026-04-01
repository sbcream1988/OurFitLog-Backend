package com.ofl.domain.participation.repository;

public interface ParticipationRepositoryCustom {

	long countAcceptedParticipants(Long gatheringId);
	
	boolean existsByMemberIdAndGatheringId(Long memberId, Long gatheringId);
}
