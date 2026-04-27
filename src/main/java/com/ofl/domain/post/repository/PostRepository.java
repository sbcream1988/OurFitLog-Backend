package com.ofl.domain.post.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ofl.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom{

	Page<Post> findAll(Pageable pageable);
	
	@Query("SELECT p from Post p left join fetch p.images where p.id = :id")
	Optional<Post> findByIdWithImages(@Param("id") Long id);
	
	
}
