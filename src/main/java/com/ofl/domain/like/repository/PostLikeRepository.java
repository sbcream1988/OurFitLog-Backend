package com.ofl.domain.like.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofl.domain.like.entity.PostLike;
import com.ofl.domain.member.entity.Member;
import com.ofl.domain.post.entity.Post;

public interface PostLikeRepository extends JpaRepository<PostLike, Long>{

	Optional<PostLike> findByMemberAndPost(Member member, Post post);
	
	void deleteByMemberAndPost(Member member, Post post);
	
	boolean existsByMemberAndPost(Member member, Post post);
}
