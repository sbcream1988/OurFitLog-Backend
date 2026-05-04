package com.ofl.domain.chat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ofl.domain.chat.entity.ChatParticipation;
import com.ofl.domain.member.entity.Member;


public interface ChatParticipationRepository extends JpaRepository<ChatParticipation, Long> {

	List<ChatParticipation> findByMember(Member member);
	
	Optional<ChatParticipation> findByMemberAndChatRoomId(Member member, Long roomId);

	long countByChatRoomId(Long roomId);

	@Modifying
	@Query("delete from ChatParticipation cp where cp.member = :member and cp.chatRoom.id = :roomId")
	void deleteByMemberAndChatRoomId(@Param("member")Member me, @Param("roomId") Long roomId);
}
