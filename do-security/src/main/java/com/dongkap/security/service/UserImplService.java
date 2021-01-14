package com.dongkap.security.service;

import java.net.URI;

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
import org.springframework.web.client.RestTemplate;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.pattern.PatternGlobal;
import com.dongkap.common.security.AESEncrypt;
import com.dongkap.common.service.CommonService;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.feign.dto.common.CommonResponseDto;
import com.dongkap.feign.dto.common.FilterDto;
import com.dongkap.feign.dto.google.GoogleResponse;
import com.dongkap.feign.dto.security.ProfileDto;
import com.dongkap.feign.dto.security.SignUpDto;
import com.dongkap.security.dao.CorporateRepo;
import com.dongkap.security.dao.RoleRepo;
import com.dongkap.security.dao.UserRepo;
import com.dongkap.security.dao.specification.UserSpecification;
import com.dongkap.security.entity.ContactUserEntity;
import com.dongkap.security.entity.CorporateEntity;
import com.dongkap.security.entity.RoleEntity;
import com.dongkap.security.entity.SettingsEntity;
import com.dongkap.security.entity.UserEntity;

@Service("userService")
public class UserImplService extends CommonService implements UserDetailsService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	private static final String ROLE_END = "ROLE_END";

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private CorporateRepo corporateRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    private RestTemplate restTemplate;
	
	@Value("${do.corporate-id.default}")
	private String corporateId;
	
	@Value("${do.signature.aes.secret-key}")
	private String secretKey;
	
	@Value("${do.recaptcha.secret-key}")
	private String recaptchaSecretKey;
	
	@Value("${do.recaptcha.site-key}")
	private String recaptchaSiteKey;
	
	@Value("${do.mobile.recaptcha.secret-key}")
	private String recaptchaMobileSecretKey;
	
	@Value("${do.mobile.recaptcha.site-key}")
	private String recaptchaMobileSiteKey;

    private static final String RECAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify";
	
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		UserEntity user = userRepo.loadByUsername(username.toLowerCase());
		if (user == null) throw new UsernameNotFoundException("User '" + username + "' not found.");
		return user;
	}

	public CommonResponseDto<ProfileDto> getDatatableUser(FilterDto filter) throws Exception {
		Page<UserEntity> user = userRepo.findAll(UserSpecification.getDatatable(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		final CommonResponseDto<ProfileDto> response = new CommonResponseDto<ProfileDto>();
		response.setTotalFiltered(Long.valueOf(user.getContent().size()));
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
		GoogleResponse googleResponse = this.recaptchaValidation(dto.getRecaptcha(), this.recaptchaSecretKey);
		if(googleResponse.isSuccess()) {
			return signUp(dto, locale);
		} else
			throw new SystemErrorException(ErrorCode.ERR_SCR0013);
	}

	@Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = SystemErrorException.class)
	public ApiBaseResponse doSignUpV2(SignUpDto dto, String locale) throws Exception {
		GoogleResponse googleResponse = this.recaptchaValidation(dto.getRecaptcha(), this.recaptchaMobileSecretKey);
		if(googleResponse.isSuccess()) {
			return signUp(dto, locale);
		} else
			throw new SystemErrorException(ErrorCode.ERR_SCR0013);
	}
	
	private ApiBaseResponse signUp(SignUpDto dto, String locale) throws Exception {
		UserEntity user = this.userRepo.loadByUsernameOrEmail(dto.getUsername().toLowerCase(), dto.getEmail().toLowerCase());
		if(user == null) {
			user = new UserEntity();
			user.setUsername(dto.getUsername());
			user.setEmail(dto.getEmail());
			String password = AESEncrypt.decrypt(this.secretKey, dto.getPassword());
			String confirmPassword = AESEncrypt.decrypt(this.secretKey, dto.getConfirmPassword());
			if (password.matches(PatternGlobal.PASSWORD_MEDIUM.getRegex())) {
				if (password.equals(confirmPassword)) {
					user.setPassword(this.passwordEncoder.encode((String)password));
				} else {
					throw new SystemErrorException(ErrorCode.ERR_SCR0011);
				}
			} else {
				throw new SystemErrorException(ErrorCode.ERR_SCR0005);
			}
	        CorporateEntity corporate = this.corporateRepo.findByCorporateId(this.corporateId);
	        user.getCorporates().add(corporate);
			RoleEntity role = this.roleRepo.findByAuthority(ROLE_END);
			user.getRoles().add(role);
			user.setAuthorityDefault(ROLE_END);
			ContactUserEntity contactUser = new ContactUserEntity();
			contactUser.setName(dto.getFullname());
			contactUser.setUser(user);
			user.setContactUser(contactUser);
			SettingsEntity settings = new SettingsEntity();
			settings.setUser(user);
			user.setSettings(settings);
			user = this.userRepo.saveAndFlush(user);
			return null;
		} else
			throw new SystemErrorException(ErrorCode.ERR_SCR0010);
	}
	
	private GoogleResponse recaptchaValidation(String recaptcha, String recaptchaSecretKey) throws Exception {
		URI verifyUri = URI.create(String.format(RECAPTCHA_URL + "?secret=%s&response=%s", recaptchaSecretKey, recaptcha));
		return this.restTemplate.getForObject(verifyUri, GoogleResponse.class);
	}

}
