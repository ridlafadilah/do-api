package com.dongkap.notification.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSenderService {

	// TODO toggle comment
	//@Autowired
	private JavaMailSender mailSender;

    @Value("${mail.personal}")
    private String personal;
    
    @Value("${spring.mail.username}")
    private String from;
    
	public void sendMessageWithText(String from, String to, String subject, String content) {
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
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mailSender.send(message);	
	}
}
