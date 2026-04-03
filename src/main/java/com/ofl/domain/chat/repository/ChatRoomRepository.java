package com.ofl.domain.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofl.domain.chat.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

}
