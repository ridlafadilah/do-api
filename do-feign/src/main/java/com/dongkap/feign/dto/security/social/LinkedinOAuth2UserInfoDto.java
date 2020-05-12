package com.dongkap.feign.dto.security.social;

import java.util.Map;

public class LinkedinOAuth2UserInfoDto extends OAuth2UserInfoDto {
	
	public LinkedinOAuth2UserInfoDto(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		return (String) attributes.get("id");
	}

	@Override
	public String getName() {
		return ((String) attributes.get("localizedFirstName")).concat(" ").concat((String) attributes.get("localizedLastName"));
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("emailAddress");
	}

	@Override
	public String getImageUrl() {
		return (String) attributes.get("pictureUrl");
	}

}
