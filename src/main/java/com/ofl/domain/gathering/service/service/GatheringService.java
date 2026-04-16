package com.ofl.domain.gathering.service.service;

import java.util.List;

import com.ofl.domain.gathering.dto.request.GatheringRequestDto;
import com.ofl.domain.gathering.dto.response.GatheringResponseDto;

public interface GatheringService {
	
	Long createGathering(GatheringRequestDto dto, Long host);
	
	public List<GatheringResponseDto> getActiveGatherings();
	
	public GatheringResponseDto getGatheringById(Long id);

	public void delete(Long id);

	
}
