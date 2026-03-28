package com.ofl.global.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	
	// 400
	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "COMMON-001", "잘못된 요청 파라미터입니다"),
	
	// 401
	UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "AUTH-001", "로그인이 필요한 서비스입니다"),
	
	// 403
	ACCESS_DENIED(HttpStatus.FORBIDDEN, "AUTH-002", "해당 작업에 대한 권한이 없습니다"),
	
	// 404 추후 업데이트되는 엔티티에 맞춰 수정
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER-001", "존재하지 않는 사용자입니다"),
	LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "LOCATION-001", "해당 장소 정보가 없습니다"),
	
	// 500
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER-001","서버 내부 오류가 발생했습니다");
	
	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
