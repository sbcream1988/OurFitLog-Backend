package com.ofl.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofl.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
