package com.ofl.domain.gathering.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofl.domain.gathering.dto.request.GatheringRequestDto;
import com.ofl.domain.gathering.dto.response.GatheringResponseDto;
import com.ofl.domain.gathering.entity.Gathering;
import com.ofl.domain.gathering.mapper.GatheringMapper;
import com.ofl.domain.gathering.repository.GatheringRepository;
import com.ofl.domain.gathering.service.service.GatheringService;
import com.ofl.domain.member.entity.Member;
import com.ofl.global.error.CustomException;
import com.ofl.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GatheringServiceImpl implements GatheringService {

	private final GatheringRepository gatheringRepository;
	private final GatheringMapper gatheringMapper;
	
	@Override
	@Transactional
	public Long createGathering(GatheringRequestDto dto, Member host) {
		Gathering gathering = new Gathering(
				dto.getTitle(),
				dto.getDescription(),
				dto.getStartsAt(),
				dto.getMaxCapacity(),
				host);
		return gatheringRepository.save(gathering).getId();
	}

	@Override
	public List<GatheringResponseDto> getActiveGatherings() {
		return gatheringRepository.findActiveGatherings().stream()
				.map(gatheringMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public GatheringResponseDto getGatheringById(Long id) {
		Gathering gathering = gatheringRepository.findById(id)
					.orElseThrow(()-> new CustomException(ErrorCode.GATHERING_NOT_FOUND));
		return gatheringMapper.toDto(gathering);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		Gathering gathering = gatheringRepository.findById(id)
				.orElseThrow(()-> new CustomException(ErrorCode.GATHERING_NOT_FOUND));
		
		gatheringRepository.delete(gathering);
	}

}
