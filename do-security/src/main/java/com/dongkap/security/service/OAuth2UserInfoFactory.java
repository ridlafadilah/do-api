package com.dongkap.security.service;

import java.util.Map;

import com.dongkap.feign.dto.security.social.FacebookOAuth2UserInfoDto;
import com.dongkap.feign.dto.security.social.GithubOAuth2UserInfoDto;
import com.dongkap.feign.dto.security.social.GoogleOAuth2UserInfoDto;
import com.dongkap.feign.dto.security.social.LinkedinOAuth2UserInfoDto;
import com.dongkap.feign.dto.security.social.OAuth2UserInfoDto;
import com.dongkap.security.common.SocialProvider;
import com.dongkap.security.exception.OAuth2AuthenticationProcessingException;

public class OAuth2UserInfoFactory {
	public static OAuth2UserInfoDto getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
		if (registrationId.equalsIgnoreCase(SocialProvider.GOOGLE.getProviderType())) {
			return new GoogleOAuth2UserInfoDto(attributes);
		} else if (registrationId.equalsIgnoreCase(SocialProvider.FACEBOOK.getProviderType())) {
			return new FacebookOAuth2UserInfoDto(attributes);
		} else if (registrationId.equalsIgnoreCase(SocialProvider.GITHUB.getProviderType())) {
			return new GithubOAuth2UserInfoDto(attributes);
		} else if (registrationId.equalsIgnoreCase(SocialProvider.LINKEDIN.getProviderType())) {
			return new LinkedinOAuth2UserInfoDto(attributes);
		} else if (registrationId.equalsIgnoreCase(SocialProvider.TWITTER.getProviderType())) {
			return new GithubOAuth2UserInfoDto(attributes);
		} else {
			throw new OAuth2AuthenticationProcessingException(
					"Sorry! Login with " + registrationId + " is not supported yet.");
		}
	}
}
