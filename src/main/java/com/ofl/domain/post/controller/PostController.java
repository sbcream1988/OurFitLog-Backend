package com.ofl.domain.post.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ofl.domain.post.dto.request.PostCreateRequestDto;
import com.ofl.domain.post.dto.response.PostResponseDto;
import com.ofl.domain.post.service.service.PostService;
import com.ofl.global.dto.response.ApiResponse;
import com.ofl.global.security.service.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<ApiResponse<Long>> createPost(@RequestPart("requestDto") PostCreateRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestPart(value = "images", required = false) List<MultipartFile> images){
		Long postId = postService.createPost(requestDto, customUserDetails.getUsername(), images);
		
		return ResponseEntity.ok(ApiResponse.success("게시글이 성공적으로 등록되었습니다", postId));
	}
	
	@GetMapping("/nearby")
	public ResponseEntity<ApiResponse<List<PostResponseDto>>> getNearByPosts(@RequestParam("lat") Double lat, @RequestParam("lng") Double lng, @RequestParam("placeName") String placeName){
		List<PostResponseDto> response = postService.getFilteredPosts(lat, lng, placeName);
		return ResponseEntity.ok(ApiResponse.success("게시글을 성공적으로 찾았습니다",response));
	}
}
