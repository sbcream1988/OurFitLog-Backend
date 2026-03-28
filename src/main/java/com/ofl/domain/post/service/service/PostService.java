package com.ofl.domain.post.service.service;

import com.ofl.domain.post.dto.request.PostCreateRequestDto;

public interface PostService {

	Long createPost(PostCreateRequestDto request, String email);
}
