package com.ofl.domain.post.service.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ofl.domain.post.dto.request.PostCreateRequestDto;
import com.ofl.domain.post.dto.response.PostResponseDto;

public interface PostService {

	Long createPost(PostCreateRequestDto request, String email, List<MultipartFile> images);
	
	List<PostResponseDto> getFilteredPosts(Double lat, Double lng, String placeName);
}
