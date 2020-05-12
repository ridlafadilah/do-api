package com.dongkap.common.utils;

public final class RibbonContext {

	public static final String APP = "xa";
	public static final String BASE_PATH = "/" + APP + "/api";
	
	public static final String SECURITY = "security";
	public static final String PROFILE = "profile";
	public static final String MASTER = "master";
	public static final String FILE = "file";
	
	public static final String SECURITY_APP = APP + "-" + SECURITY;
	public static final String PROFILE_APP = APP + "-" + PROFILE;
	public static final String MASTER_APP = APP + "-" + MASTER;
	public static final String FILE_APP = APP + "-" + FILE;

	public static final String API_SECURITY = BASE_PATH + "/" + SECURITY;
	public static final String API_PROFILE = BASE_PATH + "/" + PROFILE;
	public static final String API_MASTER = BASE_PATH + "/" + MASTER;
	public static final String API_FILE = BASE_PATH + "/" + FILE;
}
