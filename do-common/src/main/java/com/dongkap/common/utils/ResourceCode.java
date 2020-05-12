package com.dongkap.common.utils;

public enum ResourceCode {

	PROFILE("profile"),
	SECURITY("security"),
	MASTER("master"),
	PANIC("panic"),
	NOTIFICATION("notification"),
	GENERAL("general"),
	FILE("file"),
	REPORT("report");
	
	private final String resourceId;

	ResourceCode(String resourceId) {
		this.resourceId = resourceId;
	}
	
	public String getResourceId() {
		return this.resourceId;
	}
	
}
