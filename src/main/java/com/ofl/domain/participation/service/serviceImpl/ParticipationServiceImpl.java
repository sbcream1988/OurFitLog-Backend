package com.ofl.domain.participation.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofl.domain.gathering.entity.Gathering;
import com.ofl.domain.gathering.repository.GatheringRepository;
import com.ofl.domain.member.entity.Member;
import com.ofl.domain.member.repository.MemberRepository;
import com.ofl.domain.participation.entity.Participation;
import com.ofl.domain.participation.repository.ParticipationRepository;
import com.ofl.domain.participation.service.service.ParticipationService;
import com.ofl.global.entity.Status;
import com.ofl.global.error.CustomException;
import com.ofl.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ParticipationServiceImpl implements ParticipationService{


	private final ParticipationRepository participationRepository;
	private final GatheringRepository gatheringRepository;
	private final MemberRepository memberRepository;


	@Override
	@Transactional
	
	public Long attend(Long gatheringId, Long memberId) {
		log.info("memberId: {}",memberId);
		
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		
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
	public void cancel(Long gatheringId, Long memberId) {
		log.info("== CANCEL == gatheringId : {} memberId : {}", gatheringId, memberId);
		
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		
		Participation participation = participationRepository.findByMemberIdAndGatheringId(memberId, gatheringId)
				.orElseThrow(() -> new CustomException(ErrorCode.PARTICIPATION_NOT_FOUD));
		
		participationRepository.delete(participation);
	}
	
}
