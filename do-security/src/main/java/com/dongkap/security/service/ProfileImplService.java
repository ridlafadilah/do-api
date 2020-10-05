package com.dongkap.security.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.pattern.PatternGlobal;
import com.dongkap.common.utils.DateUtil;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.feign.dto.security.PersonalInfoDto;
import com.dongkap.feign.service.ParameterI18nService;
import com.dongkap.feign.service.ProfileService;
import com.dongkap.security.dao.ContactUserRepo;
import com.dongkap.security.entity.ContactUserEntity;
import com.dongkap.security.entity.PersonalInfoEntity;
import com.dongkap.security.entity.UserEntity;

@Service("profileService")
public class ProfileImplService implements ProfileService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ContactUserRepo contactUserRepo;
	
	@Autowired
	private ParameterI18nService parameterI18nService;

	@Transactional
	public ApiBaseResponse doUpdateProfile(PersonalInfoDto p_dto, UserEntity p_user, String p_locale) throws Exception {
		if (p_user.getUsername() != null) {
			ContactUserEntity profile = this.contactUserRepo.loadPersonalDataByUsername(p_user.getUsername());
			if (profile != null) {
				profile.setAddress(p_dto.getAddress());
				profile.setCountry(p_dto.getCountry());
				profile.setProvince(p_dto.getProvince());
				profile.setCity(p_dto.getCity());
				profile.setDistrict(p_dto.getDistrict());
				profile.setSubDistrict(p_dto.getSubDistrict());
				profile.setZipcode(p_dto.getZipcode());
				profile.setDescription(p_dto.getDescription());
				if (p_dto.getName() != null)
					profile.setName(p_dto.getName());
				if (p_dto.getEmail() != null) {
					if (p_dto.getEmail().matches(PatternGlobal.EMAIL.getRegex())) {
						p_user.setEmail(p_dto.getEmail());	
					} else
						throw new SystemErrorException(ErrorCode.ERR_SCR0008);
				}
				if (p_dto.getPhoneNumber() != null) {
					if (p_dto.getPhoneNumber().matches(PatternGlobal.PHONE_NUMBER.getRegex())) {
						profile.setPhoneNumber(p_dto.getPhoneNumber());	
					} else
						throw new SystemErrorException(ErrorCode.ERR_SCR0007A);
				}
				profile.setModifiedBy(p_user.getUsername());
				profile.setModifiedDate(new Date());
				PersonalInfoEntity personalInfo = new PersonalInfoEntity();
				personalInfo.setIdNumber(p_dto.getIdNumber());
				personalInfo.setGender(p_dto.getGenderCode());
				personalInfo.setPlaceOfBirth(p_dto.getPlaceOfBirth());
				personalInfo.setDateOfBirth(DateUtil.DATE.parse(p_dto.getDateOfBirth()));
				profile.setPersonalInfo(personalInfo);
				this.contactUserRepo.save(profile);
			}
			return null;
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

	public PersonalInfoDto getProfile(Authentication authentication, String p_locale) throws Exception {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		if (user.getUsername() != null) {
			return getProfile(user.getUsername(), p_locale);
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}
	
	public PersonalInfoDto getProfileOtherAuth(Map<String, Object> param, String p_locale) throws Exception {
		if (!param.isEmpty()) {
			return getProfile(param.get("username").toString(), p_locale);
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}
	
	private PersonalInfoDto getProfile(String p_username, String p_locale) throws Exception {
		if (p_username != null) {
			PersonalInfoDto dto = new PersonalInfoDto();
			ContactUserEntity profile = this.contactUserRepo.findByUser_Username(p_username);
			dto.setUsername(p_username);
			dto.setName(profile.getName());
			dto.setEmail(profile.getUser().getEmail());
			dto.setAddress(profile.getAddress());
			dto.setCountry(profile.getCountry());
			dto.setProvince(profile.getProvince());
			dto.setCity(profile.getCity());
			dto.setDistrict(profile.getDistrict());
			dto.setSubDistrict(profile.getSubDistrict());
			dto.setZipcode(profile.getZipcode());
			dto.setImage(profile.getImage());
			dto.setPhoneNumber(profile.getPhoneNumber());
			dto.setDescription(profile.getDescription());
			if(profile.getPersonalInfo() != null) {
				dto.setIdNumber(profile.getPersonalInfo().getIdNumber());
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("parameterCode", profile.getPersonalInfo().getGender());
				dto.setGenderCode(profile.getPersonalInfo().getGender());
				try {
					dto.setGender(parameterI18nService.getParameter(temp, p_locale).getParameterValue());
				} catch (Exception e) {}
				dto.setPlaceOfBirth(profile.getPersonalInfo().getPlaceOfBirth());	
				dto.setDateOfBirth(DateUtil.DATE.format(profile.getPersonalInfo().getDateOfBirth()));
			}
			return dto;
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

	@Transactional
	@Override
	public void doUpdatePhoto(Map<String, String> url, Authentication authentication, String locale) throws Exception {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		if (user.getUsername() != null && url != null) {
			ContactUserEntity profile = this.contactUserRepo.findByUser_Username(user.getUsername());
			profile.setImage(url.get("url"));
			profile.setModifiedBy(user.getUsername());
			profile.setModifiedDate(new Date());
			this.contactUserRepo.save(profile);
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

}
