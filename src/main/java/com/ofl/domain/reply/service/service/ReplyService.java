package com.ofl.domain.reply.service.service;

import java.util.List;

import com.ofl.domain.reply.dto.request.ReplyCreateRequestDto;
import com.ofl.domain.reply.dto.response.ReplyResponseDto;

public interface ReplyService {

	Long createReply(Long postId, ReplyCreateRequestDto dto, String email);
	
	List<ReplyResponseDto> getReplies(Long postId);
	
	void deleteReply(Long replyId, String email);
}
