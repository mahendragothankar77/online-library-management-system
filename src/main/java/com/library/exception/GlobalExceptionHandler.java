package com.library.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {

		ErrorResponse error = new ErrorResponse("RESOURCE_NOT_FOUND", HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BookNotAvailableException.class)
	public ResponseEntity<ErrorResponse> handleBookUnavailable(BookNotAvailableException ex,
			HttpServletRequest request) {

		ErrorResponse error = new ErrorResponse("BOOK_NOT_AVAILABLE", HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MembershipExpiredException.class)
	public ResponseEntity<ErrorResponse> handleMembershipExpired(MembershipExpiredException ex,
			HttpServletRequest request) {

		ErrorResponse error = new ErrorResponse("MEMBERSHIP_EXPIRED", HttpStatus.FORBIDDEN.value(), ex.getMessage(),
				request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		String msg = ex.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getField() + ": " + e.getDefaultMessage()).collect(Collectors.joining(", "));

		ErrorResponse error = new ErrorResponse("VALIDATION_FAILED", HttpStatus.BAD_REQUEST.value(), msg,
				request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {

		ErrorResponse error = new ErrorResponse("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value(),
				ex.getMessage(), request.getRequestURI());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
