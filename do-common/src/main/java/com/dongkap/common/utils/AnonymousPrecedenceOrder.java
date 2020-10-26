package com.dongkap.common.utils;

import org.springframework.core.Ordered;

public final class AnonymousPrecedenceOrder {

	public static final int SECURITY = Ordered.HIGHEST_PRECEDENCE;
	public static final int PROFILE = -114;
	public static final int MASTER = -115;
	public static final int FINANCE = -116;
	public static final int NOTIFICATION = -117;
	public static final int GENERAL = -118;
	public static final int FILE = -119;
	public static final int REPORT = -120;

}
