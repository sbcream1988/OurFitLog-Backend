package com.ofl.domain.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofl.domain.chat.entity.ChatParticipation;
import com.ofl.domain.member.entity.Member;


public interface ChatParticipationRepository extends JpaRepository<ChatParticipation, Long> {

	List<ChatParticipation> findByMember(Member member);
}
