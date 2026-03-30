package com.ofl.domain.like.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofl.domain.like.service.service.PostLikeService;
import com.ofl.global.dto.ApiResponse;
import com.ofl.global.security.service.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts/{postId}/likes")
@RequiredArgsConstructor
public class PostLikeController {

	private final PostLikeService postLikeService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<Boolean>> toggleLike(@PathVariable(name = "postId") Long postId, @AuthenticationPrincipal CustomUserDetails userDetails){
		
		boolean result = postLikeService.toggleLike(postId, userDetails.getUsername());
		
		return ResponseEntity.ok(ApiResponse.success(result));
	}
	
}
