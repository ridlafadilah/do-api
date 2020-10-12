package com.dongkap.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.pattern.PatternGlobal;
import com.dongkap.common.security.AESEncrypt;
import com.dongkap.common.service.CommonService;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.feign.dto.common.CommonResponseDto;
import com.dongkap.feign.dto.common.FilterDto;
import com.dongkap.feign.dto.security.ProfileDto;
import com.dongkap.feign.dto.security.SignUpDto;
import com.dongkap.security.dao.ContactUserRepo;
import com.dongkap.security.dao.UserRepo;
import com.dongkap.security.dao.specification.UserSpecification;
import com.dongkap.security.entity.ContactUserEntity;
import com.dongkap.security.entity.UserEntity;

@Service("userService")
public class UserImplService extends CommonService implements UserDetailsService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ContactUserRepo contactUserRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${do.signature.aes.secret-key}")
	private String secretKey;
	
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		UserEntity user = userRepo.loadByUsername(username.toLowerCase());
		if (user == null) throw new UsernameNotFoundException("User '" + username + "' not found.");
		return user;
	}

	public CommonResponseDto<ProfileDto> getDatatableUser(FilterDto filter) throws Exception {
		Page<UserEntity> user = userRepo.findAll(UserSpecification.getDatatable(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		CommonResponseDto<ProfileDto> response = new CommonResponseDto<ProfileDto>();
		response.setTotalFiltered(new Long(user.getContent().size()));
		response.setTotalRecord(userRepo.count(UserSpecification.getDatatable(filter.getKeyword())));
		user.getContent().forEach(value -> {
			ProfileDto temp = new ProfileDto();
			temp.setUsername(value.getUsername());
			temp.setEmail(value.getEmail());
			temp.setActive(value.isActive());
			temp.setVersion(value.getVersion());
			temp.setCreatedDate(value.getCreatedDate());
			temp.setCreatedBy(value.getCreatedBy());
			temp.setModifiedDate(value.getModifiedDate());
			temp.setModifiedBy(value.getModifiedBy());	
			if(value.getContactUser() != null) {
				temp.setName(value.getContactUser().getName());
				temp.setPhoneNumber(value.getContactUser().getPhoneNumber());
				temp.setAddress(value.getContactUser().getAddress());
				temp.setCountry(value.getContactUser().getCountry());
				temp.setProvince(value.getContactUser().getProvince());
				temp.setCity(value.getContactUser().getCity());
				temp.setDistrict(value.getContactUser().getDistrict());
				temp.setSubDistrict(value.getContactUser().getSubDistrict());
				temp.setZipcode(value.getContactUser().getZipcode());
				temp.setImage(value.getContactUser().getImage());
				temp.setDescription(value.getContactUser().getDescription());
			}
			response.getData().add(temp);
		});
		return response;
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = SystemErrorException.class)
	public ApiBaseResponse doSignUp(SignUpDto dto, String locale) throws Exception {
		UserEntity user = this.userRepo.loadByUsernameOrEmail(dto.getUsername().toLowerCase(), dto.getEmail().toLowerCase());
		if(user == null) {
			user = new UserEntity();
			user.setUsername(dto.getUsername());
			user.setEmail(dto.getEmail());
			String password = AESEncrypt.decrypt(this.secretKey, dto.getPassword());
			String confirmPassword = AESEncrypt.decrypt(this.secretKey, dto.getConfirmPassword());
			if (password.equals(confirmPassword)) {
				if (password.matches(PatternGlobal.PASSWORD_MEDIUM.getRegex())) {
					user.setPassword(this.passwordEncoder.encode((String)password));
				} else {
					throw new SystemErrorException(ErrorCode.ERR_SCR0005);
				}
			} else {
				throw new SystemErrorException(ErrorCode.ERR_SCR0011);
			}
			ContactUserEntity contactUser = new ContactUserEntity();
			contactUser.setName(dto.getFullname());
			contactUser.setUser(user);
			contactUser = this.contactUserRepo.saveAndFlush(contactUser);
			return null;
		} else
			throw new SystemErrorException(ErrorCode.ERR_SCR0010);
	}

}
