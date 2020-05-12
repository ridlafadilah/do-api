package com.dongkap.feign.dto.security.social;

import java.util.Map;

public abstract class OAuth2UserInfoDto {
	protected Map<String, Object> attributes;

	public OAuth2UserInfoDto(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public abstract String getId();

	public abstract String getName();

	public abstract String getEmail();

	public abstract String getImageUrl();
}