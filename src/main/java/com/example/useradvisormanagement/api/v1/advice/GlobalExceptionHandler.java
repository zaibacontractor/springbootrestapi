package com.example.useradvisormanagement.api.v1.advice;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.useradvisormanagement.constants.CommonCode;
import com.example.useradvisormanagement.exception.ResourceNotFoundException;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CommonCode.DATE_TIME_STRIPPED);
	private static final int RANDOM_LOWER = 1000;
	private static final int RANDOM_UPPER = 9000;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundExceptionError(HttpServletRequest request,
			HttpServletResponse response, ResourceNotFoundException ex) {
		// do something with request and response
		generateLogs(request, response, ex, null);

		
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralError(HttpServletRequest request,
			HttpServletResponse response,

			Exception ex) {
		// do something with request and response

		String errorId = getErrorId();
		generateLogs(request, response, ex, errorId);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occured");
	}

	public String getErrorId() {

		return "BRT " + formatter.format(LocalDateTime.now())
				+ (new SecureRandom().nextInt(RANDOM_UPPER) + RANDOM_LOWER);
	}

	private String generateLogs(HttpServletRequest request, HttpServletResponse response, Exception ex,
			String errorId) {

		StringBuilder result = new StringBuilder(
				"ERROR ID IS: " + errorId + " \n");
		result.append("### URL : (" + request.getMethod() + ") " + request.getRequestURI()
				+ (request.getQueryString() != null ? "?" + request.getQueryString() : "") + "\n");

		if (StringUtils.equalsAnyIgnoreCase(request.getMethod(), "POST", "PUT")) {
			try {
				result.append("### POST Data : "
						+ request.getReader().lines().collect(Collectors.joining(System.lineSeparator())) + "\n");
			} catch (IOException e1) {
			}
		}
		result.append("### Exception error : " + ExceptionUtils.getStackTrace(ex) + "\n");
		System.out.println(result);
		//log.error(result.toString());
		return errorId;
	}
}
