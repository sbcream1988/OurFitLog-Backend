package com.ofl.domain.map.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ofl.domain.map.service.MapService;
import com.ofl.infra.kakao.dto.KakaoSearchResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/map")
@RequiredArgsConstructor

public class MapController {

	private final MapService mapService;
	
	@GetMapping("/gyms")
	public ResponseEntity<KakaoSearchResponse> getNearbyGyms(@RequestParam("lat") String lat, @RequestParam("lng") String lng){
		
		KakaoSearchResponse response = mapService.findNearByGyms(lat, lng);
		return ResponseEntity.ok(response);
	}
}
