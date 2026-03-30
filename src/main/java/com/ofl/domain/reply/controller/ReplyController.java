package com.ofl.domain.reply.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofl.domain.reply.dto.request.ReplyCreateRequestDto;
import com.ofl.domain.reply.dto.response.ReplyResponseDto;
import com.ofl.domain.reply.service.service.ReplyService;
import com.ofl.global.dto.ApiResponse;
import com.ofl.global.security.service.CustomUserDetails;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/replies")
public class ReplyController {

	private final ReplyService replyService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Long>> createReply(@PathVariable("postId") Long postId,@Valid @RequestBody ReplyCreateRequestDto dto, @AuthenticationPrincipal CustomUserDetails customUserDetails){
	
		Long replyId = replyService.createReply(postId, dto, customUserDetails.getUsername());
		
		return ResponseEntity.ok(ApiResponse.success(replyId));
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<ReplyResponseDto>>> getReplies(@PathVariable("postId") Long postId){
		List<ReplyResponseDto> replies = replyService.getReplies(postId);
		
		return ResponseEntity.ok(ApiResponse.success(replies));
	}
	
	@DeleteMapping("/{replyId}")
	public ResponseEntity<ApiResponse<Void>> deleteReply(@PathVariable("replyId") Long replyId, @AuthenticationPrincipal CustomUserDetails customUserDetails){
		
		replyService.deleteReply(replyId, customUserDetails.getUsername());
		
		return ResponseEntity.noContent().build();
	}
}
