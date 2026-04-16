package com.ofl.domain.chat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ofl.domain.chat.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
	
	@Query(value = "SELECT cr.* FROM chat_room cr " + "JOIN chat_participation cp1 ON cr.id = cp1.chat_room_id "
			+ "JOIN chat_participation cp2 ON cr.id = cp2.chat_room_id " + "WHERE cr.type = 'ONETOONE' "
			+ "AND cp1.member_id = :myId " + "AND cp2.member_id = :partnerId", nativeQuery = true)
	Optional<ChatRoom> findOneToOneRoom(@Param("myId") Long myId, @Param("partnerId") Long partnerId);
}
