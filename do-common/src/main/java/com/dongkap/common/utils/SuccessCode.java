package com.dongkap.common.utils;

import org.springframework.http.HttpStatus;

public enum SuccessCode {

	OK_DEFAULT(HttpStatus.OK),
	OK_INSERTED(HttpStatus.OK),
	OK_UPDATED(HttpStatus.OK),
	OK_DELETED(HttpStatus.OK),
	OK_LOGOUT(HttpStatus.OK),
	OK_SCR001(HttpStatus.OK),
	OK_REGISTERED(HttpStatus.OK),
	OK_SCR002(HttpStatus.OK),
	OK_SCR003(HttpStatus.OK),
	OK_SCR004(HttpStatus.OK),
	OK_SCR005(HttpStatus.OK),
	OK_SCR006(HttpStatus.OK),
	OK_SCR007(HttpStatus.CREATED),
	OK_SCR008(HttpStatus.OK),
	OK_SCR009(HttpStatus.OK),
	OK_SCR010(HttpStatus.OK),
	OK_SCR011(HttpStatus.OK),
	OK_SCR012(HttpStatus.OK);

	private final HttpStatus status;

	SuccessCode(HttpStatus status) {
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
	
}
