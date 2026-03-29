package com.ofl.domain.image.service.serviceImpl;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ofl.domain.image.service.service.ImageService;
import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

	@Autowired(required = false)
	private S3Template s3Template;
	
	@Value("${spring.cloud.aws.s3.bucket}")
	private String bucket;


	public String uploadImage(MultipartFile file) {
		String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
		
		try {
			S3Resource s3Resource = s3Template.upload(bucket, fileName, file.getInputStream(),ObjectMetadata.builder().contentType(file.getContentType()).build());
			return s3Resource.getURL().toString();
		}catch(IOException e) {
			throw new RuntimeException("이미지 업로드 중 오류 발생",e);
		}
	}
	
	
}
