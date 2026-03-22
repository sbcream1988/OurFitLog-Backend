package com.ofl.global.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<ErrorResponse> handleCustomException(CustomException customException){
		ErrorCode errorCode = customException.getErrorCode();
		
		return ResponseEntity.status(errorCode.getHttpStatus())
				.body(ErrorResponse.builder()
						.status(errorCode.getHttpStatus().value())
						.error(errorCode.getHttpStatus().name())
						.code(errorCode.getCode())
						.message(errorCode.getMessage())
						.build());
	}
}
