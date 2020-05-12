package com.dongkap.common.exceptions;

import org.springframework.http.HttpStatus;

import com.dongkap.common.utils.ErrorCode;

public class FeignAuthException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8972880939809113925L;
	private Object[] params;
	private ErrorCode errorCode;
	private HttpStatus status;

	public FeignAuthException() {
		super();
	}

	public FeignAuthException(String message, Throwable cause) {
		super(message, cause);
	}

	public FeignAuthException(String message) {
		super(message);
	}

	public FeignAuthException(ErrorCode errorCode) {
		super(errorCode.name());
		this.errorCode = errorCode;
	}

	public FeignAuthException(HttpStatus status) {
		super(status.name());
		this.status = status;
	}

	public FeignAuthException(ErrorCode errorCode, Object[] params) {
		super(errorCode.name());
		this.errorCode = errorCode;
		this.params = params;
	}

	public FeignAuthException(Throwable cause) {
		super(cause);
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public Object[] getParams() {
		return params;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
