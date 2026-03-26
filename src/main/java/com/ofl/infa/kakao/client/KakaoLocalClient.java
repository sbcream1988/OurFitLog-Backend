package com.ofl.infa.kakao.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ofl.infa.kakao.dto.KakaoSearchResponse;

@FeignClient(name = "kakaoLocalClient", url = "${kakao.api.base-url}")
public interface KakaoLocalClient {

	@GetMapping("/v2/local/search/keyword.json")
	KakaoSearchResponse searchByKeyword(@RequestHeader("Authorization") String authHeader,
			@RequestParam("query") String query, @RequestParam("x") String x, @RequestParam("y") String y,
			@RequestParam("radius") Integer radius, @RequestParam("sort") String sort);

}
