package com.dongkap.security.service;

import java.util.Calendar;
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
import com.dongkap.feign.dto.security.PersonalDto;
import com.dongkap.feign.service.ParameterI18nService;
import com.dongkap.feign.service.ProfilePersonalService;
import com.dongkap.security.dao.ContactUserRepo;
import com.dongkap.security.entity.ContactUserEntity;
import com.dongkap.security.entity.PersonalInfoEntity;
import com.dongkap.security.entity.PersonalSupportEntity;
import com.dongkap.security.entity.UserEntity;

@Service("profilePersonalService")
public class ProfilePersonalImplService implements ProfilePersonalService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ContactUserRepo contactUserRepo;
	
	@Autowired
	private ParameterI18nService parameterI18nService;

	@Transactional
	public ApiBaseResponse doUpdateProfilePersonal(PersonalDto p_dto, UserEntity p_user, String p_locale) throws Exception {
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
				personalInfo.setGender(p_dto.getGender());
				personalInfo.setPlaceOfBirth(p_dto.getPlaceOfBirth());
				personalInfo.setDateOfBirth(DateUtil.DATE.parse(p_dto.getDateOfBirth()));
				PersonalSupportEntity personalSupport = new PersonalSupportEntity();
				personalSupport.setReferenceName(p_dto.getReferenceName());
				personalSupport.setReferenceAddress(p_dto.getReferenceAddress());
				if (p_dto.getReferencePhoneNumber() != null) {
					if (p_dto.getReferencePhoneNumber().matches(PatternGlobal.PHONE_NUMBER.getRegex())) {
						personalSupport.setReferencePhoneNumber(p_dto.getReferencePhoneNumber());	
					} else
						throw new SystemErrorException(ErrorCode.ERR_SCR0007A);
				}
				personalSupport.setRelationship(p_dto.getRelationship());
				personalInfo.setPersonalSupport(personalSupport);
				profile.setPersonalInfo(personalInfo);
				this.contactUserRepo.save(profile);
			}
			return null;
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

	public PersonalDto getProfilePersonal(Authentication authentication, String p_locale) throws Exception {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		return this.getProfilePersonal(user, p_locale);
	}
	
	public PersonalDto getProfilePersonal(UserEntity p_user, String p_locale) throws Exception {
		if (p_user.getUsername() != null) {
			PersonalDto dto = new PersonalDto();
			ContactUserEntity profile = this.contactUserRepo.findByUser_Username(p_user.getUsername());
			dto.setUsername(p_user.getUsername());
			dto.setName(profile.getName());
			dto.setEmail(p_user.getEmail());
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
			    Calendar calDateOfBirth = Calendar.getInstance();
			    calDateOfBirth.setTime(profile.getPersonalInfo().getDateOfBirth());
			    Calendar calDateOfNow = Calendar.getInstance();
			    calDateOfNow.setTime(new Date());
			    dto.setAge(calDateOfNow.get(Calendar.YEAR) - calDateOfBirth.get(Calendar.YEAR));
				dto.setIdNumber(profile.getPersonalInfo().getIdNumber());
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("parameterCode", profile.getPersonalInfo().getGender());
				dto.setGenderCode(profile.getPersonalInfo().getGender());
				try {
					dto.setGender(parameterI18nService.getParameter(temp, p_locale).getParameterValue());
				} catch (Exception e) {}
				dto.setPlaceOfBirth(profile.getPersonalInfo().getPlaceOfBirth());	
				dto.setDateOfBirth(DateUtil.DATE.format(profile.getPersonalInfo().getDateOfBirth()));
				if(profile.getPersonalInfo().getPersonalSupport() != null) {
					dto.setReferenceName(profile.getPersonalInfo().getPersonalSupport().getReferenceName());
					dto.setReferenceAddress(profile.getPersonalInfo().getPersonalSupport().getReferenceAddress());
					dto.setReferencePhoneNumber(profile.getPersonalInfo().getPersonalSupport().getReferencePhoneNumber());
					temp.put("parameterCode", profile.getPersonalInfo().getPersonalSupport().getRelationship());
					dto.setRelationship(profile.getPersonalInfo().getPersonalSupport().getRelationship());
					try {
						dto.setRelationship(parameterI18nService.getParameter(temp, p_locale).getParameterValue());
					} catch (Exception e) {}
				}
			}
			return dto;
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}
	
	public PersonalDto getProfilePersonalAuth(Map<String, Object> param, String p_locale) throws Exception {
		if (!param.isEmpty()) {
			PersonalDto dto = new PersonalDto();
			ContactUserEntity profile = this.contactUserRepo.findByUser_Username(param.get("username").toString());
			dto.setUsername(param.get("username").toString());
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
				if(profile.getPersonalInfo().getPersonalSupport() != null) {
					dto.setReferenceName(profile.getPersonalInfo().getPersonalSupport().getReferenceName());
					dto.setReferenceAddress(profile.getPersonalInfo().getPersonalSupport().getReferenceAddress());
					dto.setReferencePhoneNumber(profile.getPersonalInfo().getPersonalSupport().getReferencePhoneNumber());
					temp.put("parameterCode", profile.getPersonalInfo().getPersonalSupport().getRelationship());
					dto.setRelationship(profile.getPersonalInfo().getPersonalSupport().getRelationship());
					try {
						dto.setRelationship(parameterI18nService.getParameter(temp, p_locale).getParameterValue());
					} catch (Exception e) {}
				}
			}
			return dto;
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

}
