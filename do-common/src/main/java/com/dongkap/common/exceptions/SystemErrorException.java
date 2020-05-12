package com.dongkap.common.exceptions;

import org.springframework.http.HttpStatus;

import com.dongkap.common.utils.ErrorCode;

public class SystemErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8972880939809113925L;
	private Object[] params;
	private ErrorCode errorCode;
	private HttpStatus status;

	public SystemErrorException() {
		super();
	}

	public SystemErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemErrorException(String message) {
		super(message);
	}

	public SystemErrorException(ErrorCode errorCode) {
		super(errorCode.name());
		this.errorCode = errorCode;
	}


	public SystemErrorException(HttpStatus status) {
		super(status.name());
		this.status = status;
	}

	public SystemErrorException(ErrorCode errorCode, Object[] params) {
		super(errorCode.name());
		this.errorCode = errorCode;
		this.params = params;
	}

	public SystemErrorException(Throwable cause) {
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
