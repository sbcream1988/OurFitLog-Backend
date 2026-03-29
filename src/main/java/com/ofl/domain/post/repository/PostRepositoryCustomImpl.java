package com.ofl.domain.post.repository;

import java.util.List;

import com.ofl.domain.post.entity.Post;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.ofl.domain.post.entity.QPost.post;
import static com.ofl.domain.location.entity.QLocation.location;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Post> searchNearbyPosts(Double lat, Double lon, String placeName) {

		return queryFactory.selectFrom(post).leftJoin(post.location, location).fetchJoin()
				.where(placeNameEq(placeName), nearBy(lat, lon)).fetch();

	}

	private BooleanExpression placeNameEq(String placeName) {
		return (placeName != null && !placeName.isEmpty()) ? location.placeName.contains(placeName) : null;
	}

	private BooleanExpression nearBy(Double lat, Double lon) {
		if (lat == null || lon == null) {
			return null;
		}
		double radius = 0.01;
		return location.latitude.between(lat - radius, lat + radius)
				.and(location.longitude.between(lon - radius, lon + radius));

	}
}
