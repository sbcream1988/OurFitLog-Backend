package com.ofl.domain.participation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofl.domain.participation.service.service.ParticipationService;
import com.ofl.global.dto.response.ApiResponse;
import com.ofl.global.error.CustomException;
import com.ofl.global.error.ErrorCode;
import com.ofl.global.security.service.CustomUserDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/participations")
@Log4j2
public class ParticipationController {

	private final ParticipationService participationService;

	@PostMapping("/{gatheringId}")
	public ResponseEntity<ApiResponse<Long>> create(@PathVariable("gatheringId") Long gatheringId,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		log.info("Request gatheringId:{}, memberId:{}" , gatheringId, userDetails.getId());
		if(userDetails.getId() == null) {
			log.error("memberId가 null입니다");
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}

		Long participationId = participationService.attend(gatheringId, userDetails.getId());

		return ResponseEntity.ok(ApiResponse.success(participationId));
	}

	@DeleteMapping("/{gatheringId}")
	public ResponseEntity<ApiResponse<Void>> cancel(@PathVariable("gatheringId") Long gatheringId,
			@AuthenticationPrincipal CustomUserDetails userDetails) {

		participationService.cancel(gatheringId, userDetails.getId());

		return ResponseEntity.noContent().build();
	}
}
