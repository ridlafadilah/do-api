package com.dongkap.security.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.security.AESEncrypt;
import com.dongkap.common.utils.ErrorCode;
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

}
