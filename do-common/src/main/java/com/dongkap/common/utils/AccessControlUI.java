package com.dongkap.common.utils;

public enum AccessControlUI {

	INQ("INQ"),
	GET("GET"),
	ADD("ADD"),
	PUT("PUT"),
	DEL("DEL"),
	APV("APV");

	private final String access;

	AccessControlUI(String access) {
		this.access = access;
	}
	
	public String getAccess() {
		return this.access;
	}
	
}
