package com.dongkap.common.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6956862312103005998L;
	private String oAuth2ErrorCode;

	public CustomOauthException(String msg) {
        super(msg);
    }

	public CustomOauthException(String msg, String oAuth2ErrorCode) {
		super(msg);
		this.oAuth2ErrorCode = oAuth2ErrorCode;
	}
	
	public String getOAuth2ErrorCode() {
		return this.oAuth2ErrorCode;
	}
}