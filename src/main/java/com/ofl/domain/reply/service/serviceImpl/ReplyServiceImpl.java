package com.ofl.domain.reply.service.serviceImpl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofl.domain.member.entity.Member;
import com.ofl.domain.member.repository.MemberRepository;
import com.ofl.domain.post.entity.Post;
import com.ofl.domain.post.repository.PostRepository;
import com.ofl.domain.reply.dto.request.ReplyCreateRequestDto;
import com.ofl.domain.reply.dto.response.ReplyResponseDto;
import com.ofl.domain.reply.entity.Reply;
import com.ofl.domain.reply.mapper.ReplyMapper;
import com.ofl.domain.reply.repository.ReplyRepository;
import com.ofl.domain.reply.service.service.ReplyService;
import com.ofl.global.error.CustomException;
import com.ofl.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {

	private final ReplyRepository replyRepository;
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	private final ReplyMapper replyMapper;
	
	@Override
	public Long createReply(Long postId, ReplyCreateRequestDto dto, String email) {
		Member member = memberRepository.findByEmail(email)
				.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
		
		Post post = postRepository.findById(postId)
				.orElseThrow(()->new CustomException(ErrorCode.POST_NOT_FOUND));
		
		Reply reply = replyMapper.toEntity(dto);
		reply.setRegistraction(member, post);
		
		
		return replyRepository.save(reply).getId();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ReplyResponseDto> getReplies(Long postId){
		return replyRepository.findByPostIdOrderByCreatedAtDesc(postId)
				.stream()
				.map(replyMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteReply(Long replyId, String email) {
		
		Reply reply = replyRepository.findById(replyId)
				.orElseThrow(() -> new CustomException(ErrorCode.REPLY_NOT_FOUND));
		
		if(!reply.isAuthor(email)) {
			throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
		}
		
		replyRepository.delete(reply);
		
	}

}
