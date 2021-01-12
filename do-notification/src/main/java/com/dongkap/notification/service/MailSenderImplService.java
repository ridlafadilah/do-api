package com.dongkap.notification.service;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.dongkap.feign.dto.notification.MailNotificationDto;
import com.dongkap.feign.service.MailSenderService;

@Component("mailSenderService")
public class MailSenderImplService implements MailSenderService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	// TODO toggle comment
	@Autowired
	private JavaMailSender mailSender;
    
    @Autowired
    private TemplateMailService templateMailService;

    @Value("${mail.personal}")
    private String personal;
    
    @Value("${spring.mail.username}")
    private String from;
    
	public void sendMessageWithText(String from, String to, String subject, String content) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			if(from != null)
				this.from = from;
			InternetAddress addressFrom = new InternetAddress(this.from, personal);
			helper.setFrom(addressFrom);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			helper.setPriority(1);
		} catch (MessagingException e) {
			LOGGER.error("[Dongkap] Mail Error : ", e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("[Dongkap] Mail Error : ", e);
		}
		mailSender.send(message);	
	}
    
	public void sendMessageWithTemplate(MailNotificationDto mail, Locale locale) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			if(mail.getFrom() != null)
				this.from = mail.getFrom();
			if(mail.getContentTemplate() != null) {
				Calendar calendar = Calendar.getInstance();
				mail.getContentTemplate().put("yearCopyright", String.valueOf(calendar.get(Calendar.YEAR)));
			}
			InternetAddress addressFrom = new InternetAddress(this.from, personal);
			helper.setFrom(addressFrom);
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getSubject());
			helper.setText(this.templateMailService.getTemplate(mail.getContentTemplate(), mail.getFileNameTemplate(), locale), true);
			helper.setPriority(1);
		} catch (MessagingException e) {
			LOGGER.error("[Dongkap] Mail Error : ", e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("[Dongkap] Mail Error : ", e);
		}
		mailSender.send(message);	
	}
}
