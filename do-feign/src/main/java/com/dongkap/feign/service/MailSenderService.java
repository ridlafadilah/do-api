package com.dongkap.feign.service;

import java.util.Locale;

import com.dongkap.feign.dto.notification.MailNotificationDto;

public interface MailSenderService {
	
	public void sendMessageWithText(String from, String to, String subject, String content) throws Exception;
	
	public void sendMessageWithTemplate(MailNotificationDto mail, Locale locale) throws Exception;

}
