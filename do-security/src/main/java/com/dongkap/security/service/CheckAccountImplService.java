package com.dongkap.security.service;

import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.security.AESEncrypt;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.common.utils.SuccessCode;
import com.dongkap.feign.service.CheckAccountService;
import com.dongkap.security.dao.UserRepo;
import com.dongkap.security.entity.UserEntity;

@Service("checkAccountService")
public class CheckAccountImplService implements CheckAccountService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Value("${do.signature.aes.secret-key}")
	private String secretKey;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MessageSource messageSource;

	public ApiBaseResponse doCheckPassword(Map<String, Object> dto, Authentication authentication, String p_locale) throws Exception {
		if (dto != null) {
			UserEntity p_user = this.userRepo.findByUsername(authentication.getName());
			String password = AESEncrypt.decrypt(this.secretKey, dto.get("password").toString());
			if (this.passwordEncoder.matches(password, p_user.getPassword())) {
				return new ApiBaseResponse();
			} else {
				throw new SystemErrorException(ErrorCode.ERR_SCR0002);
			}			
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

	public ApiBaseResponse checkUserByUsenamerOrEmail(String p_user, String p_locale) throws Exception {
		if(p_user != null) {
			UserEntity userEntity = userRepo.loadByUser(p_user.toLowerCase());
			Locale locale = Locale.getDefault();
			ApiBaseResponse response = new ApiBaseResponse();
			if(p_locale != null)
				locale = Locale.forLanguageTag(p_locale);
			if (userEntity != null) {
				response.setRespStatusCode(ErrorCode.ERR_SYS0302.name());
				response.getRespStatusMessage().put(ErrorCode.ERR_SYS0302.name(), messageSource.getMessage(ErrorCode.ERR_SYS0302.name(), null, locale));
			} else {
				response.setRespStatusCode(SuccessCode.OK_SCR012.name());
				response.getRespStatusMessage().put(SuccessCode.OK_SCR012.name(), messageSource.getMessage(SuccessCode.OK_SCR012.name(), null, locale));
			}
			return response;	
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

}
