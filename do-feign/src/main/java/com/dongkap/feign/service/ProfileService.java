package com.dongkap.feign.service;

import java.util.Map;

import org.springframework.security.core.Authentication;

public interface ProfileService {
	
	public void doUpdatePhoto(Map<String, String> url, Authentication authentication, String locale) throws Exception;

}
