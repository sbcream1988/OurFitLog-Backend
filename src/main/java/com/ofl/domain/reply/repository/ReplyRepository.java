package com.ofl.domain.reply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofl.domain.post.entity.Post;
import com.ofl.domain.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	Post findByPostId(Long postId);
	
	List<Reply> findByPostIdOrderByCreatedAtDesc(Long postId);

}
