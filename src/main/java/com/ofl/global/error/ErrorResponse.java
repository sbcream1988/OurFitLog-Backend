package com.ofl.global.error;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

	private final LocalDateTime timeStamp = LocalDateTime.now();
	
	private final int status;
	
	private final String error;
	
	private final String code;
	
	private final String message;
}
