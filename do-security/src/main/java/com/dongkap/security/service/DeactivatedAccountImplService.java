package com.dongkap.security.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.security.AESEncrypt;
import com.dongkap.common.utils.AuthorizationProvider;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.security.dao.UserRepo;
import com.dongkap.security.entity.UserEntity;

@Service("deactivatedAccountService")
public class DeactivatedAccountImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Value("${do.signature.aes.secret-key}")
	private String secretKey;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public ApiBaseResponse doDeactivate(Map<String, String> dto, UserEntity p_user, String p_locale) throws Exception {
		if (p_user.getUsername() != null && dto != null) {
			if(dto.get("provider") != null) {
				if(!dto.get("provider").equals(AuthorizationProvider.local.toString())) {
					return this.deactivatedProvider(dto, p_user, p_locale);
				} else
					throw new SystemErrorException(ErrorCode.ERR_SYS0404);
			} else {
				return this.deactivated(dto, p_user, p_locale);
			}
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}
	
	private ApiBaseResponse deactivated(Map<String, String> dto, UserEntity p_user, String p_locale) throws Exception {
		if(dto.get("password") != null) {
			p_user = this.userRepo.findByUsername(p_user.getUsername());
			if (p_user != null) {
				String password = AESEncrypt.decrypt(this.secretKey, dto.get("password"));
				if (this.passwordEncoder.matches(password, p_user.getPassword())) {
					p_user.setActive(false);
					p_user.setEnabled(false);
					userRepo.save(p_user);
					return null;
				} else {
					throw new SystemErrorException(ErrorCode.ERR_SCR0002);
				}
			} else {
				throw new SystemErrorException(ErrorCode.ERR_SYS0001);
			}
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
		
	}
	
	private ApiBaseResponse deactivatedProvider(Map<String, String> dto, UserEntity p_user, String p_locale) throws Exception {
		if(dto.get("email") != null && dto.get("provider") != null) {
			p_user = this.userRepo.findByUsernameAndEmailAndProvider(p_user.getUsername(), dto.get("email"), dto.get("provider"));
			if (p_user != null) {
				p_user.setActive(false);
				p_user.setEnabled(false);
				userRepo.save(p_user);
				return null;
			} else {
				throw new SystemErrorException(ErrorCode.ERR_SYS0001);
			}
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);		
	}

}
