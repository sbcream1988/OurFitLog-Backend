package com.ofl.domain.map.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ofl.domain.map.service.MapService;
import com.ofl.infra.kakao.client.KakaoLocalClient;
import com.ofl.infra.kakao.dto.KakaoSearchResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService{

	private final KakaoLocalClient kakaoLocalClient;
	
	@Value("${kakao.api.key}")
	private String apiKey;
	
	@Override
	public KakaoSearchResponse findNearByGyms(String lat, String lng) {
		String authHeader = "KakaoAK " + apiKey;
		
		return kakaoLocalClient.searchByKeyword(authHeader, "헬스장", lng, lat, 2000, "distance");
	}
}
