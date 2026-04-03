package com.ofl.domain.participation.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofl.domain.gathering.entity.Gathering;
import com.ofl.domain.gathering.repository.GatheringRepository;
import com.ofl.domain.member.entity.Member;
import com.ofl.domain.participation.entity.Participation;
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


	@Override
	@Transactional
	public Long attend(Long gatheringId, Member member) {
		
		Gathering gathering = gatheringRepository.findByIdWithLock(gatheringId)
				.orElseThrow( () -> new CustomException(ErrorCode.GATHERING_NOT_FOUND));
		
		if (participationRepository.existsByMemberIdAndGatheringId(member.getId(), gatheringId)) {
			throw new CustomException(ErrorCode.ALREADY_PARTICIPATED);
		}
		
		long currentCount = participationRepository.countAcceptedParticipants(gatheringId);
		if(currentCount >= gathering.getMaxCapacity()) {
			throw new CustomException(ErrorCode.GATHERING_FULL);
		}
		
		Participation participation = new Participation(member, gathering, Status.ACCEPTED);
		
		return participationRepository.save(participation).getId();
	}
	
	
	@Override
	@Transactional
	public void cancel(Long gatheringId, Member member) {
		Participation participation = participationRepository.findByMemberIdAndGatheringId(member.getId(), gatheringId)
				.orElseThrow(() -> new CustomException(ErrorCode.PARTICIPATION_NOT_FOUD));
		
		participationRepository.delete(participation);
	}
	
}
