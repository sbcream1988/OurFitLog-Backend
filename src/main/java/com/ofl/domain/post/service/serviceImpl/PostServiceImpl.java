package com.ofl.domain.post.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofl.domain.location.entity.Location;
import com.ofl.domain.location.mapper.LocationMapper;
import com.ofl.domain.member.entity.Member;
import com.ofl.domain.member.repository.MemberRepository;
import com.ofl.domain.post.dto.request.PostCreateRequestDto;
import com.ofl.domain.post.entity.Post;
import com.ofl.domain.post.mapper.PostMapper;
import com.ofl.domain.post.repository.PostRepository;
import com.ofl.domain.post.service.service.PostService;
import com.ofl.global.error.CustomException;
import com.ofl.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	private final PostMapper postMapper;
	private final LocationMapper locationMapper;
	
	@Override
	public Long createPost(PostCreateRequestDto request, String email) {
		Member member = memberRepository.findByEmail(email)
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		
		Post post = postMapper.toEntity(request);
		
		post.setMember(member);
		
		if(request.getLocation() != null) {
			Location location = locationMapper.toEntity(request.getLocation());
			post.addLocation(location);
		}
				
		return postRepository.save(post).getId();
	}
}
