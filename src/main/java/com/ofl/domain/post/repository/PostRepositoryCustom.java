package com.ofl.domain.post.repository;

import java.util.List;

import com.ofl.domain.post.entity.Post;

public interface PostRepositoryCustom {
	List<Post> searchNearbyPosts(Double lat, Double lng, String placeName);
}
