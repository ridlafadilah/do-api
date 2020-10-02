package com.dongkap.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.pattern.PatternGlobal;
import com.dongkap.common.security.AESEncrypt;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.feign.dto.security.ChangePasswordDto;
import com.dongkap.security.dao.UserRepo;
import com.dongkap.security.entity.UserEntity;

@Service("changePasswordService")
public class ChangePasswordImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Value("${do.signature.aes.secret-key}")
	private String secretKey;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public ApiBaseResponse doChangePassword(ChangePasswordDto p_dto, UserEntity p_user, String p_locale) throws Exception {
		if (p_user.getUsername() != null) {
			p_user = this.userRepo.findByUsername(p_user.getUsername());
			String password = AESEncrypt.decrypt(this.secretKey, p_dto.getPassword());
			String newPassword = AESEncrypt.decrypt(this.secretKey, p_dto.getNewPassword());
			String confirmPassword = AESEncrypt.decrypt(this.secretKey, p_dto.getConfirmPassword());
			if (this.passwordEncoder.matches(password, p_user.getPassword())) {
				if (!newPassword.equals(password)) {
					if (newPassword.equals(confirmPassword)) {
						if (newPassword.matches(PatternGlobal.PASSWORD_MEDIUM.getRegex())) {
							p_user.setPassword(this.passwordEncoder.encode((String)newPassword));
							userRepo.save(p_user);
							return null;
						} else {
							throw new SystemErrorException(ErrorCode.ERR_SCR0005);
						}
					} else {
						throw new SystemErrorException(ErrorCode.ERR_SCR0003);
					}
				} else {
					throw new SystemErrorException(ErrorCode.ERR_SCR0006);
				}
			} else {
				throw new SystemErrorException(ErrorCode.ERR_SCR0002);
			}
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

}
