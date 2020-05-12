package com.dongkap.common.http;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.dongkap.common.utils.ErrorCode;

@Component
public class ApiErrorResponse {
	
	@Autowired
	private MessageSource messageSource;
	
	@Value("${xa.locale}")
	private String locale;
	
	public String errorDescriptionResponse(ErrorCode errorCode, Locale locale) {
		if(locale == null)
			locale = Locale.forLanguageTag(this.locale);
		return messageSource.getMessage(errorCode.name(), null, locale);
	}
	
	public String errorDescriptionResponse(String errorCode, Locale locale) {
		if(locale == null)
			locale = Locale.forLanguageTag(this.locale);
		return messageSource.getMessage(errorCode, null, locale);
	}
	
	public String errorDescriptionResponse(ErrorCode errorCode, Locale locale, Object[] params) {
		if(locale == null)
			locale = Locale.forLanguageTag(this.locale);
		return messageSource.getMessage(errorCode.name(), params, locale);
	}
	
	public String errorDescriptionResponse(String errorCode, Locale locale, Object[] params) {
		if(locale == null)
			locale = Locale.forLanguageTag(this.locale);
		return messageSource.getMessage(errorCode, params, locale);
	}

	public String getErrorMessage(ErrorCode errorCode, Locale locale) {
		return messageSource.getMessage(errorCode.name(), null, locale);
	}

	public String getErrorMessage(String errorCode, Locale locale) {
		return messageSource.getMessage(errorCode, null, locale);
	}
    
}
