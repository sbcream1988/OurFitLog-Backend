package com.ofl.domain.post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofl.domain.post.dto.request.PostCreateRequestDto;
import com.ofl.domain.post.service.service.PostService;
import com.ofl.global.dto.ApiResponse;
import com.ofl.global.security.service.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Long>> createPost(@RequestBody PostCreateRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails customUserDetails){
		Long postId = postService.createPost(requestDto, customUserDetails.getUsername());
		
		return ResponseEntity.ok(ApiResponse.success("게시글이 성공적으로 등록되었습니다", postId));
	}
}
