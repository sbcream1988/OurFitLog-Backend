package com.ofl.domain.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofl.domain.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
