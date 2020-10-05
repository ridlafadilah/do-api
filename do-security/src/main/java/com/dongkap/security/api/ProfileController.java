package com.dongkap.security.api;

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
import com.dongkap.feign.dto.security.PersonalInfoDto;
import com.dongkap.feign.dto.security.ProfileDto;
import com.dongkap.security.entity.UserEntity;
import com.dongkap.security.service.ProfileImplService;
import com.dongkap.security.service.ProfileSystemImplService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController extends BaseControllerException {

	@Autowired
	private ProfileImplService profileService;

	@Autowired
	private ProfileSystemImplService profileSystemService;
	
	@ResponseSuccess(SuccessCode.OK_SCR004)
	@RequestMapping(value = "/trx/post/profile/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiBaseResponse> putProfile(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale,
			@RequestBody(required = true) PersonalInfoDto p_dto) throws Exception {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		return new ResponseEntity<ApiBaseResponse>(profileService.doUpdateProfile(p_dto, user, locale), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vw/get/profile/v.1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonalInfoDto> getProfile(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		return new ResponseEntity<PersonalInfoDto>(profileService.getProfile(authentication, locale), HttpStatus.OK);
	}
	
	@ResponseSuccess(SuccessCode.OK_SCR004)
	@RequestMapping(value = "/trx/auth/profile-system/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiBaseResponse> putProfileSystem(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale,
			@RequestBody(required = true) ProfileDto p_dto) throws Exception {
		UserEntity user = (UserEntity) authentication.getPrincipal();
		return new ResponseEntity<ApiBaseResponse>(profileSystemService.doUpdateProfileSystem(p_dto, user, locale), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vw/auth/profile-system/v.1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfileDto> getProfileSystemAuth(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		return new ResponseEntity<ProfileDto>(profileSystemService.getProfileSystemAuth(authentication, locale), HttpStatus.OK);
	}
	
}
