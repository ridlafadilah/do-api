package com.dongkap.security.utils;

import java.util.Map;

import com.dongkap.common.utils.AuthorizationProvider;
import com.dongkap.feign.dto.security.social.FacebookOAuth2UserInfoDto;
import com.dongkap.feign.dto.security.social.GithubOAuth2UserInfoDto;
import com.dongkap.feign.dto.security.social.GoogleOAuth2UserInfoDto;
import com.dongkap.feign.dto.security.social.OAuth2UserInfoDto;
import com.dongkap.security.exception.OAuth2AuthenticationProcessingException;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfoDto getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthorizationProvider.google.toString())) {
            return new GoogleOAuth2UserInfoDto(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthorizationProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfoDto(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthorizationProvider.github.toString())) {
            return new GithubOAuth2UserInfoDto(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }

}
