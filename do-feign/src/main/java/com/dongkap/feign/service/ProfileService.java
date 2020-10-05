package com.dongkap.feign.service;

import java.util.Map;

import org.springframework.security.core.Authentication;

import com.dongkap.feign.dto.security.PersonalInfoDto;

public interface ProfileService {
	
	public void doUpdatePhoto(Map<String, String> url, Authentication authentication, String locale) throws Exception;
	
	public PersonalInfoDto getProfile(Authentication authentication, String p_locale) throws Exception;

}
