package com.ofl.domain.image.service.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	String uploadImage(MultipartFile file);
}
