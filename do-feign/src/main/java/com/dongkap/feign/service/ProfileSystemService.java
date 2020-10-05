package com.dongkap.feign.service;

import org.springframework.security.core.Authentication;

import com.dongkap.feign.dto.security.ProfileDto;

public interface ProfileSystemService {
	
	public ProfileDto getProfileSystemAuth(Authentication authentication, String p_locale) throws Exception;

}
