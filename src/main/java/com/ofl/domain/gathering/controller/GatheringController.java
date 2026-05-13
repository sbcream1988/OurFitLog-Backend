package com.ofl.domain.gathering.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofl.domain.gathering.dto.request.GatheringRequestDto;
import com.ofl.domain.gathering.dto.response.GatheringResponseDto;
import com.ofl.domain.gathering.service.service.GatheringService;
import com.ofl.global.dto.response.ApiResponse;
import com.ofl.global.security.service.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gatherings")
public class GatheringController {

	private final GatheringService gatheringService;

	
	@PostMapping
	public ResponseEntity<ApiResponse<Long>> create(@RequestBody GatheringRequestDto dto, @AuthenticationPrincipal CustomUserDetails userDetails ){
		Long gatheringId = gatheringService.createGathering(dto, userDetails.getId());
		
		return ResponseEntity.ok(ApiResponse.success(gatheringId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<GatheringResponseDto>> get(@PathVariable Long id){
		GatheringResponseDto response = gatheringService.getGatheringById(id);
		
		return ResponseEntity.ok(ApiResponse.success(response));
	}
	
	@GetMapping()
	public ResponseEntity<ApiResponse<List<GatheringResponseDto>>> getAll(){
		List<GatheringResponseDto> activeList = gatheringService.getActiveGatherings();
		
		return ResponseEntity.ok(ApiResponse.success(activeList));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGathering(@PathVariable("id")Long id){
		gatheringService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
