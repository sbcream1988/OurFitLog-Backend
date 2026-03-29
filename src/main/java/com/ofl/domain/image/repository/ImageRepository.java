package com.ofl.domain.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofl.domain.image.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
