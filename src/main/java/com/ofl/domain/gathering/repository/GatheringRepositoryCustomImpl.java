package com.ofl.domain.gathering.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.ofl.domain.gathering.entity.Gathering;
import static com.ofl.domain.gathering.entity.QGathering.gathering;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class GatheringRepositoryCustomImpl implements GatheringRepositoryCustom{

	private final JPAQueryFactory queryFactory;
	
	@Override
	public List<Gathering> searchMyGathering(Long memberId) {
		return null;
	}

	@Override
	public List<Gathering> findActiveGatherings() {
		return queryFactory.selectFrom(gathering)
							.where(gathering.startsAt.after(LocalDateTime.now()))
							.orderBy(gathering.createdAt.desc())
							.fetch();
	}

}
