package com.dongkap.security.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dongkap.common.aspect.ResponseSuccess;
import com.dongkap.common.exceptions.BaseControllerException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.utils.SuccessCode;
import com.dongkap.feign.dto.security.PersonalDto;
import com.dongkap.feign.dto.security.ProfileDto;
import com.dongkap.security.entity.UserEntity;
import com.dongkap.security.service.ProfileBaseImplService;
import com.dongkap.security.service.ProfilePersonalImplService;

@RestController
public class ProfileController extends BaseControllerException {

	@Autowired
	private ProfileBaseImplService profileBaseService;

	@Autowired
	private ProfilePersonalImplService profilePersonalService;
	
	@ResponseSuccess(SuccessCode.OK_SCR004)
	@RequestMapping(value = "/api/profile/trx/auth/profile/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiBaseResponse> putBaseProfile(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale,
			@RequestBody(required = true) ProfileDto p_dto) throws Exception {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		return new ResponseEntity<ApiBaseResponse>(profileBaseService.doUpdateProfileBase(p_dto, user, locale), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/profile/vw/get/profile/v.1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfileDto> getBaseProfile(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		return new ResponseEntity<ProfileDto>(profileBaseService.getProfileBase(user, locale), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/security/trx/auth/profile/v.1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfileDto> getBaseProfileAuth(@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale,
			@RequestBody(required = true) Map<String, Object> param) throws Exception {
		return new ResponseEntity<ProfileDto>(profileBaseService.getBaseProfileAuth(param, locale), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/profile/vw/get/profile-personal/v.1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonalDto> getProfilePersonal(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		return new ResponseEntity<PersonalDto>(profilePersonalService.getProfilePersonal(user, locale), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/security/trx/auth/profile-personal/v.1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonalDto> getProfilePersonalAuth(@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale,
			@RequestBody(required = true) Map<String, Object> param) throws Exception {
		return new ResponseEntity<PersonalDto>(profilePersonalService.getProfilePersonalAuth(param, locale), HttpStatus.OK);
	}
	
}
