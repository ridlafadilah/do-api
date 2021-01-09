package com.dongkap.security.service;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.pattern.PatternGlobal;
import com.dongkap.common.security.AESEncrypt;
import com.dongkap.common.utils.AuthorizationProvider;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.common.utils.RandomString;
import com.dongkap.common.utils.SuccessCode;
import com.dongkap.feign.dto.notification.MailNotificationDto;
import com.dongkap.feign.dto.security.ForgotPasswordDto;
import com.dongkap.feign.dto.security.RequestForgotPasswordDto;
import com.dongkap.feign.service.MailSenderService;
import com.dongkap.security.dao.UserRepo;
import com.dongkap.security.entity.UserEntity;

@Service("forgotPassword")
public class ForgotPasswordImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Value("${do.signature.aes.secret-key}")
	private String secretKey;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MailSenderService mailSenderService;
	
	@Autowired
	private MessageSource messageSource;

	public ApiBaseResponse requestForgotPassword(RequestForgotPasswordDto p_dto, String p_locale) throws Exception {
		if(p_dto.getMobile()) {
			return requestForgotPasswordMobile(p_dto, p_locale);			
		} else {
			return requestForgotPasswordWeb(p_dto, p_locale);
		}
	}

	public ApiBaseResponse verificationForgotPassword(ForgotPasswordDto p_dto, String p_locale) throws Exception {
		if(p_dto.getVerificationId() != null && p_dto.getVerificationCode() != null) {
			UserEntity userEntity = userRepo.loadByIdAndVerificationCode(p_dto.getVerificationId(), p_dto.getVerificationCode());
			if(userEntity != null) {
				return null;
			} else
				throw new SystemErrorException(ErrorCode.ERR_SYS0002);
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

	public ApiBaseResponse forgotPassword(ForgotPasswordDto p_dto, String p_locale) throws Exception {
		if(p_dto.getVerificationId() != null && p_dto.getVerificationCode() != null) {
			UserEntity userEntity = userRepo.loadByIdAndVerificationCode(p_dto.getVerificationId(), p_dto.getVerificationCode());
			if(userEntity != null) {
				if(!(new Date().after(userEntity.getVerificationExpired()))) {
					String newPassword = AESEncrypt.decrypt(this.secretKey, p_dto.getNewPassword());
					String confirmPassword = AESEncrypt.decrypt(this.secretKey, p_dto.getConfirmPassword());
					if (newPassword.matches(PatternGlobal.PASSWORD_MEDIUM.getRegex())) {
						if (newPassword.equals(confirmPassword)) {
							userEntity.setPassword(this.passwordEncoder.encode((String)newPassword));
							userEntity.setVerificationCode(null);
							userEntity.setVerificationExpired(null);
							userRepo.saveAndFlush(userEntity);
							return null;
						} else {
							throw new SystemErrorException(ErrorCode.ERR_SCR0003);
						}
					} else {
						throw new SystemErrorException(ErrorCode.ERR_SCR0005);
					}
				} else
					throw new SystemErrorException(ErrorCode.ERR_SYS0002);
			} else
				throw new SystemErrorException(ErrorCode.ERR_SYS0002);
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}
	
	private ApiBaseResponse requestForgotPasswordMobile(RequestForgotPasswordDto p_dto, String p_locale) throws Exception {
		if(p_dto.getEmail() != null) {
			UserEntity userEntity = userRepo.loadByUser(p_dto.getEmail().toLowerCase());
			if(userEntity != null) {
				if(userEntity.getProvider().equals(AuthorizationProvider.local.toString())) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					cal.add(Calendar.DATE, 1);
					userEntity.setVerificationExpired(cal.getTime());
					userEntity.setVerificationCode(new RandomString(6, new SecureRandom(), RandomString.digits).nextString());
					Locale locale = Locale.getDefault();
					if(p_locale != null)
						locale = Locale.forLanguageTag(p_locale);
					userEntity = this.userRepo.saveAndFlush(userEntity);
					String template = "forgot-password-mobile_"+locale.getLanguage()+".ftl";
					if(locale == Locale.US)
						template = "forgot-password-mobile.ftl";
					Map<String, Object> content = new HashMap<String, Object>();
					content.put("name", userEntity.getContactUser().getName());
					content.put("verificationCode", userEntity.getVerificationCode());
					MailNotificationDto mail = new MailNotificationDto();
					mail.setTo(userEntity.getEmail());
					mail.setSubject(messageSource.getMessage("subject.mail.forgot-password", null, locale));
					mail.setContentTemplate(content);
					mail.setFileNameTemplate(template);
					this.mailSenderService.sendMessageWithTemplate(mail, locale);
					ApiBaseResponse response = new ApiBaseResponse();
					response.setRespStatusCode(SuccessCode.OK_FORGOT_PASSWORD.name());
					response.getRespStatusMessage().put(response.getRespStatusCode(), userEntity.getId());
					return response;					
				} else
					throw new SystemErrorException(ErrorCode.ERR_SYS0401);
			} else
				throw new SystemErrorException(ErrorCode.ERR_SCR0012);
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);		
	}
	
	private ApiBaseResponse requestForgotPasswordWeb(RequestForgotPasswordDto p_dto, String p_locale) throws Exception {
		if(p_dto.getEmail() != null && p_dto.getUrlForgotPassword() != null) {
			UserEntity userEntity = userRepo.loadByUser(p_dto.getEmail().toLowerCase());
			if(userEntity != null) {
				if(userEntity.getProvider().equals(AuthorizationProvider.local.toString())) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					cal.add(Calendar.DATE, 1);
					userEntity.setVerificationExpired(cal.getTime());
					userEntity.setVerificationCode(new RandomString(8).nextString());
					Locale locale = Locale.getDefault();
					if(p_locale != null)
						locale = Locale.forLanguageTag(p_locale);
					userEntity = this.userRepo.saveAndFlush(userEntity);
					String template = "forgot-password_"+locale.getLanguage()+".ftl";
					if(locale == Locale.US)
						template = "forgot-password.ftl";
					Map<String, Object> content = new HashMap<String, Object>();
					content.put("name", userEntity.getContactUser().getName());
					content.put("urlForgotPassword", p_dto.getUrlForgotPassword()+"/"+userEntity.getId()+"/"+userEntity.getVerificationCode());
					MailNotificationDto mail = new MailNotificationDto();
					mail.setTo(userEntity.getEmail());
					mail.setSubject(messageSource.getMessage("subject.mail.forgot-password", null, locale));
					mail.setContentTemplate(content);
					mail.setFileNameTemplate(template);
					this.mailSenderService.sendMessageWithTemplate(mail, locale);
					return null;					
				} else
					throw new SystemErrorException(ErrorCode.ERR_SYS0401);
			} else
				throw new SystemErrorException(ErrorCode.ERR_SCR0012);
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

}
