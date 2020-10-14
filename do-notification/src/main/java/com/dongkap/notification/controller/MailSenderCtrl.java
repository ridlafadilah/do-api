package com.dongkap.notification.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dongkap.common.exceptions.BaseControllerException;
import com.dongkap.feign.dto.notification.MailNotificationDto;
import com.dongkap.notification.service.MailSenderImplService;
import com.dongkap.notification.service.TemplateMailService;

@RequestMapping("/api/notification")
@RestController
public class MailSenderCtrl extends BaseControllerException {

    @Autowired
    private MailSenderImplService service;

    @Autowired
    private TemplateMailService templateService;

    @RequestMapping(value = "/trx/post/mail/to/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendMail(@RequestBody MailNotificationDto mail) throws Exception {
    	if(mail.getFileNameTemplate() == null)        
    		service.sendMessageWithText(mail.getFrom(), mail.getTo(), mail.getSubject(), mail.getContentString());
    	else
    		service.sendMessageWithText(mail.getFrom(), mail.getTo(), mail.getSubject(), templateService.getTemplate(mail.getContentTemplate(), mail.getFileNameTemplate(), null));
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("info", HttpStatus.OK.value());
		data.put("info_description", "Success");
		return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @RequestMapping(value = "/trx/post/mail/broadcast/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> broadcast(@RequestBody List<MailNotificationDto> mails) throws Exception {
    	for(MailNotificationDto mail: mails) {
        	if(mail.getFileNameTemplate() == null)        
        		service.sendMessageWithText(mail.getFrom(), mail.getTo(), mail.getSubject(), mail.getContentString());
        	else
        		service.sendMessageWithText(mail.getFrom(), mail.getTo(), mail.getSubject(), templateService.getTemplate(mail.getContentTemplate(), mail.getFileNameTemplate(), null));	
    	}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("info", HttpStatus.OK.value());
		data.put("info_description", "Success");
		return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
