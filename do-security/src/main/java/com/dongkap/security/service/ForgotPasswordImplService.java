package com.dongkap.security.service;

import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.pattern.PatternGlobal;
import com.dongkap.common.security.AESEncrypt;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.feign.dto.security.ForgotPasswordDto;
import com.dongkap.security.dao.UserRepo;
import com.dongkap.security.entity.UserEntity;

import net.bytebuddy.utility.RandomString;

@Service("forgotPassword")
public class ForgotPasswordImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Value("${do.signature.aes.secret-key}")
	private String secretKey;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ApiBaseResponse requestForgotPassword(Map<String, String> p_dto, String p_locale) throws Exception {
		if(p_dto.get("email") != null) {
			UserEntity userEntity = userRepo.loadByUser(p_dto.get("email").toLowerCase());
			if(userEntity != null) {
				userEntity.setVerificationCode(new RandomString(8).nextString());
				Locale locale = Locale.getDefault();
				if(p_locale != null)
					locale = Locale.forLanguageTag(p_locale);
				System.err.println(locale.toLanguageTag());
				this.userRepo.saveAndFlush(userEntity);
				return null;
			} else
				throw new SystemErrorException(ErrorCode.ERR_SCR0012);
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

	public ApiBaseResponse forgotPassword(ForgotPasswordDto p_dto, String p_locale) throws Exception {
		if(p_dto.getVerificationId() != null && p_dto.getVerificationCode() != null) {
			UserEntity userEntity = userRepo.loadByIdAndVerificationCode(p_dto.getVerificationId(), p_dto.getVerificationCode());
			if(userEntity != null) {
				String newPassword = AESEncrypt.decrypt(this.secretKey, p_dto.getNewPassword());
				String confirmPassword = AESEncrypt.decrypt(this.secretKey, p_dto.getConfirmPassword());
				if (newPassword.matches(PatternGlobal.PASSWORD_MEDIUM.getRegex())) {
					if (newPassword.equals(confirmPassword)) {
						userEntity.setPassword(this.passwordEncoder.encode((String)newPassword));
						userEntity.setVerificationCode(null);
						userRepo.saveAndFlush(userEntity);
						return null;
					} else {
						throw new SystemErrorException(ErrorCode.ERR_SCR0003);
					}
				} else {
					throw new SystemErrorException(ErrorCode.ERR_SCR0005);
				}
			} else
				throw new SystemErrorException(ErrorCode.ERR_SCR0001);
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

}
