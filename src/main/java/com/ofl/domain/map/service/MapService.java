package com.ofl.domain.map.service;

import com.ofl.infra.kakao.dto.KakaoSearchResponse;

public interface MapService {

	KakaoSearchResponse findNearByGyms(String lat, String lng);
	
}
