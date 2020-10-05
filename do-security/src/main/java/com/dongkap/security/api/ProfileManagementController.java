package com.dongkap.security.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dongkap.common.exceptions.BaseControllerException;
import com.dongkap.feign.dto.security.PersonalInfoDto;
import com.dongkap.feign.dto.security.ProfileDto;
import com.dongkap.security.service.ProfileImplService;
import com.dongkap.security.service.ProfileSystemImplService;

@RestController
@RequestMapping("/api/security")
public class ProfileManagementController extends BaseControllerException {

	@Autowired
	private ProfileImplService profileService;

	@Autowired
	private ProfileSystemImplService profileSystemService;
	
	@RequestMapping(value = "/vw/auth/profile-other/v.1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonalInfoDto> getProfileOtherAuth(@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale,
			@RequestBody(required = true) Map<String, Object> param) throws Exception {
		return new ResponseEntity<PersonalInfoDto>(profileService.getProfileOtherAuth(param, locale), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vw/auth/profile-system-other/v.1", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfileDto> getProfileSystemAuthOther(@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale,
			@RequestBody(required = true) Map<String, Object> param) throws Exception {
		return new ResponseEntity<ProfileDto>(profileSystemService.getProfileSystemOtherAuth(param, locale), HttpStatus.OK);
	}
	
}
