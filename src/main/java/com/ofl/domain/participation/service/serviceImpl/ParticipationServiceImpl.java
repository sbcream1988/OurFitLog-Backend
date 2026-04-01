package com.ofl.domain.participation.service.serviceImpl;

import org.springframework.stereotype.Service;

import com.ofl.domain.gathering.entity.Gathering;
import com.ofl.domain.gathering.repository.GatheringRepository;
import com.ofl.domain.image.repository.ImageRepository;
import com.ofl.domain.member.entity.Member;
import com.ofl.domain.participation.entity.Participation;
import com.ofl.domain.participation.mapper.ParticipationMapper;
import com.ofl.domain.participation.repository.ParticipationRepository;
import com.ofl.domain.participation.service.service.ParticipationService;
import com.ofl.global.entity.Status;
import com.ofl.global.error.CustomException;
import com.ofl.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService{


	private final ParticipationRepository participationRepository;
	private final GatheringRepository gatheringRepository;
	private final ParticipationMapper participationMapper;


	@Override
	public Long attend(Long gatheringId, Member member) {
		
		Gathering gathering = gatheringRepository.findById(gatheringId)
				.orElseThrow( () -> new CustomException(ErrorCode.GATHERING_NOT_FOUND));
		
		if (participationRepository.existsByMemberIdAndGatheringId(member.getId(), gatheringId)) {
			throw new CustomException(ErrorCode.ALREADY_PARTICIPATED);
		}
		
		long currentCount = participationRepository.countAcceptedParticipants(gatheringId);
		if(currentCount >= gathering.getMaxCapacity()) {
			throw new CustomException(ErrorCode.GATHERING_FULL);
		}
		
		Participation participation = new Participation(member, gathering, Status.PENDING);
		
		return participationRepository.save(participation).getId();
	}
	
	
}
