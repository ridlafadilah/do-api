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
import com.dongkap.feign.dto.security.ForgotPasswordDto;
import com.dongkap.feign.dto.security.RequestForgotPasswordDto;
import com.dongkap.security.service.ForgotPasswordImplService;

@RestController
public class ForgotPasswordController extends BaseControllerException {

	@Autowired
	private ForgotPasswordImplService forgotPassword;

	@ResponseSuccess(SuccessCode.OK_REQUEST_FORGOT_PASSWORD)
	@RequestMapping(value = "/oauth/request-forgot-password", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiBaseResponse> requestForgotPassword(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale,
			@RequestBody(required = true) RequestForgotPasswordDto p_dto) throws Exception {
		return new ResponseEntity<ApiBaseResponse>(forgotPassword.requestForgotPassword(p_dto, locale), HttpStatus.OK);
	}

	@ResponseSuccess(SuccessCode.OK_FORGOT_PASSWORD)
	@RequestMapping(value = "/oauth/forgot-password", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiBaseResponse> forgotPassword(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale,
			@RequestBody(required = true) ForgotPasswordDto p_dto) throws Exception {
		return new ResponseEntity<ApiBaseResponse>(forgotPassword.forgotPassword(p_dto, locale), HttpStatus.OK);
	}
	
}
