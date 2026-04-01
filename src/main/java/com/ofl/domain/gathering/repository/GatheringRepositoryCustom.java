package com.ofl.domain.gathering.repository;

import java.util.List;

import com.ofl.domain.gathering.entity.Gathering;


public interface GatheringRepositoryCustom {
	
	// 내 모임 조회
	List<Gathering> searchMyGathering(Long memberId);
	
	// 모집중 모임 목록 조회
	List<Gathering> findActiveGatherings();
}
