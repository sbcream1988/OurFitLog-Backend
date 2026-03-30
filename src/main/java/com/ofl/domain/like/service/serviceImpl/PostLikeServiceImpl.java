package com.ofl.domain.like.service.serviceImpl;

import org.springframework.stereotype.Service;

import com.ofl.domain.like.entity.PostLike;
import com.ofl.domain.like.repository.PostLikeRepository;
import com.ofl.domain.like.service.service.PostLikeService;
import com.ofl.domain.member.entity.Member;
import com.ofl.domain.member.repository.MemberRepository;
import com.ofl.domain.post.entity.Post;
import com.ofl.domain.post.repository.PostRepository;
import com.ofl.global.error.CustomException;
import com.ofl.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService{
	
	private final PostLikeRepository likeRepository;
	private final MemberRepository memberRepository;
	private final PostRepository postRepository;

	@Override
	public boolean toggleLike(Long postId, String email) {
		
		Member member = memberRepository.findByEmail(email)
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
		
		return likeRepository.findByMemberAndPost(member, post)
				.map(like-> {
					likeRepository.delete(like);
					return false;
				})
				.orElseGet(()->{
					likeRepository.save(PostLike.builder().member(member).post(post).build());
					return true;
				});
	}

}
