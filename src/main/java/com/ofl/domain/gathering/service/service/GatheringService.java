package com.ofl.domain.gathering.service.service;

import java.util.List;

import com.ofl.domain.gathering.dto.request.GatheringRequestDto;
import com.ofl.domain.gathering.dto.response.GatheringResponseDto;
import com.ofl.domain.member.entity.Member;

public interface GatheringService {
	
	Long createGathering(GatheringRequestDto dto, Member host);
	
	public List<GatheringResponseDto> getActiveGatherings();
	
	public GatheringResponseDto getGatheringById(Long id);

	
}
