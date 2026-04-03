package com.ofl.domain.participation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofl.domain.participation.service.service.ParticipationService;
import com.ofl.global.dto.ApiResponse;
import com.ofl.global.security.service.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/participations")
public class ParticipationController {

	private final ParticipationService participationService;

	@PostMapping("/{gatheringId}")
	public ResponseEntity<ApiResponse<Long>> create(@PathVariable Long gatheringId,
			@AuthenticationPrincipal CustomUserDetails userDetails) {

		Long participationId = participationService.attend(gatheringId, userDetails.getMember());

		return ResponseEntity.ok(ApiResponse.success(participationId));
	}

	@DeleteMapping("/{gatheringId}")
	public ResponseEntity<ApiResponse<Void>> cancel(@PathVariable Long gatheringId,
			@AuthenticationPrincipal CustomUserDetails userDetails) {

		participationService.cancel(gatheringId, userDetails.getMember());

		return ResponseEntity.noContent().build();
	}
}
