package com.dongkap.common.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.http.ApiErrorResponse;
import com.dongkap.common.utils.ErrorCode;

public class BaseControllerException {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected ApiErrorResponse errorResponse;
	
	protected void rollback() {}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiBaseResponse> handleException(HttpServletRequest request, Exception exception) throws Exception {
		if (exception instanceof FeignAuthException) {
			throw exception;
		}
		LOGGER.error(exception.getMessage(), exception);
		
		Locale locale = Locale.getDefault();
		String acceptLanguage = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
		if(acceptLanguage != null)
			locale = Locale.forLanguageTag(acceptLanguage);
		Map<String, String> respStatusMessage = new HashMap<String, String>();
		respStatusMessage.put(ErrorCode.ERR_SYS0500.name(), errorResponse.errorDescriptionResponse(ErrorCode.ERR_SYS0500, locale));
		ApiBaseResponse baseResponse = new ApiBaseResponse();
		baseResponse.setRespStatusCode(ErrorCode.ERR_SYS0500.name());
		baseResponse.setRespStatusMessage(respStatusMessage);
		return new ResponseEntity<ApiBaseResponse>(baseResponse,
				ErrorCode.ERR_SYS0500.getStatus());
	}
	
	@ExceptionHandler(MissingServletRequestPartException.class)
	public ResponseEntity<ApiBaseResponse> handleMissingServletRequestPartException(HttpServletRequest request, MissingServletRequestPartException exception) {
		LOGGER.error(exception.getMessage(), exception);
		
		Locale locale = Locale.getDefault();
		String acceptLanguage = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
		if(acceptLanguage != null)
			locale = Locale.forLanguageTag(acceptLanguage);
		Map<String, String> respStatusMessage = new HashMap<String, String>();
		respStatusMessage.put(ErrorCode.ERR_SYS0404.name(), errorResponse.errorDescriptionResponse(ErrorCode.ERR_SYS0404, locale));
		ApiBaseResponse baseResponse = new ApiBaseResponse();
		baseResponse.setRespStatusCode(ErrorCode.ERR_SYS0404.name());
		baseResponse.setRespStatusMessage(respStatusMessage);
		return new ResponseEntity<ApiBaseResponse>(baseResponse,
				ErrorCode.ERR_SYS0404.getStatus());
	}
	
	@ExceptionHandler(NoSuchAlgorithmException.class)
	public ResponseEntity<ApiBaseResponse> handleEncryptException(HttpServletRequest request, NoSuchAlgorithmException exception) {
		stackTrace(exception);
		
		Locale locale = Locale.getDefault();
		String acceptLanguage = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
		if(acceptLanguage != null)
			locale = Locale.forLanguageTag(acceptLanguage);
		Map<String, String> respStatusMessage = new HashMap<String, String>();
		respStatusMessage.put(ErrorCode.ERR_SCR0004.name(), errorResponse.errorDescriptionResponse(ErrorCode.ERR_SCR0004, locale));
		ApiBaseResponse baseResponse = new ApiBaseResponse();
		baseResponse.setRespStatusCode(ErrorCode.ERR_SCR0004.name());
		baseResponse.setRespStatusMessage(respStatusMessage);
		return new ResponseEntity<ApiBaseResponse>(baseResponse,
				ErrorCode.ERR_SCR0004.getStatus());
	}

	@ExceptionHandler(FeignThrowException.class)
	public ResponseEntity<ApiBaseResponse> handleFeignThrowException(HttpServletRequest request, Exception exception) throws Exception {
		FeignThrowException e = (FeignThrowException) exception;
		return new ResponseEntity<ApiBaseResponse>((ApiBaseResponse) e.getResponse(), e.getStatus());
	}
	
	@ExceptionHandler(SystemErrorException.class)
	public ResponseEntity<ApiBaseResponse> handleSystemException(HttpServletRequest request, SystemErrorException exception) {
		Locale locale = Locale.getDefault();
		String acceptLanguage = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
		if(acceptLanguage != null)
			locale = Locale.forLanguageTag(acceptLanguage);	
		String errorCode = null;
		String errorDescription = null;
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		if (exception.getErrorCode() != null) {
			if (exception.getErrorCode().equals(ErrorCode.ERR_SYS0500))
				LOGGER.error(exception.getMessage(), exception);
			status = exception.getErrorCode().getStatus();
			errorCode = exception.getErrorCode().name();
			if(exception.getParams() != null)
				errorDescription = errorResponse.errorDescriptionResponse(errorCode, locale, exception.getParams());	
			else
				errorDescription = errorResponse.errorDescriptionResponse(errorCode, locale, exception.getParams());
		} else if (exception.getStatus() != null) {
			errorCode = exception.getStatus().name();
			errorDescription = exception.getStatus().getReasonPhrase();
		}
		Map<String, String> respStatusMessage = new HashMap<String, String>();
		respStatusMessage.put(errorCode, errorDescription);
		ApiBaseResponse baseResponse = new ApiBaseResponse();
		baseResponse.setRespStatusCode(errorCode);
		baseResponse.setRespStatusMessage(respStatusMessage);
		return new ResponseEntity<ApiBaseResponse>(baseResponse, status);
	}

	private String stackTrace(Exception exception) {
		StringWriter errors = new StringWriter();
		exception.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}

}
