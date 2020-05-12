package com.dongkap.common.exceptions;

import org.springframework.http.HttpStatus;

import com.dongkap.common.utils.ErrorCode;

public class FeignThrowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8972880939809113925L;
	private Object[] params;
	private ErrorCode errorCode;
	private HttpStatus status;
	private Object response;

	public FeignThrowException() {
		super();
	}

	public FeignThrowException(String message, Throwable cause) {
		super(message, cause);
	}

	public FeignThrowException(String message) {
		super(message);
	}

	public FeignThrowException(ErrorCode errorCode) {
		super(errorCode.name());
		this.errorCode = errorCode;
	}

	public FeignThrowException(HttpStatus status) {
		super(status.name());
		this.status = status;
	}

	public FeignThrowException(ErrorCode errorCode, Object[] params) {
		super(errorCode.name());
		this.errorCode = errorCode;
		this.params = params;
	}

	public FeignThrowException(Object response, HttpStatus status) {
		super(response.toString());
		this.response = response;
		this.status = status;
	}

	public FeignThrowException(Object response) {
		super(response.toString());
		this.response = response;
	}

	public FeignThrowException(Throwable cause) {
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

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

}
