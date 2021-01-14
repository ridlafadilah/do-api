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
import com.dongkap.feign.dto.security.SignUpDto;
import com.dongkap.security.service.UserImplService;

@RestController
public class SignUpController extends BaseControllerException {

	@Autowired
	private UserImplService userService;

	@ResponseSuccess(SuccessCode.OK_REGISTERED)
	@RequestMapping(value = "/oauth/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiBaseResponse> doSignUp(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale,
			@RequestBody(required = true) SignUpDto p_dto) throws Exception {
		return new ResponseEntity<ApiBaseResponse>(userService.doSignUp(p_dto, locale), HttpStatus.OK);
	}

	@ResponseSuccess(SuccessCode.OK_REGISTERED)
	@RequestMapping(value = "/oauth/signup/v2", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiBaseResponse> doSignUpV2(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale,
			@RequestBody(required = true) SignUpDto p_dto) throws Exception {
		return new ResponseEntity<ApiBaseResponse>(userService.doSignUpV2(p_dto, locale), HttpStatus.OK);
	}
	
}
