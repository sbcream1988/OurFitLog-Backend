package com.ofl.domain.participation.repository;

import static com.ofl.domain.participation.entity.QParticipation.participation;

import com.ofl.global.entity.Status;
import com.querydsl.jpa.impl.JPAQueryFactory;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParticipationRepositoryCustomImpl implements ParticipationRepositoryCustom{

	private final JPAQueryFactory queryFactory;
	
	
	@Override
	public long countAcceptedParticipants(Long gatheringId) {
		return queryFactory.select(participation.count())
							.from(participation)
							.where(participation.gathering.id.eq(gatheringId),
									participation.status.eq(Status.ACCEPTED))
							.fetchOne();
	}

	@Override
	public boolean existsByMemberIdAndGatheringId(Long memberId, Long gatheringId) {
		Integer fetchOne = queryFactory
				.selectOne()
				.from(participation)
				.where(participation.member.id.eq(memberId), participation.gathering.id.eq(gatheringId))
				.fetchFirst();
		return fetchOne != null;
	}

	
}
