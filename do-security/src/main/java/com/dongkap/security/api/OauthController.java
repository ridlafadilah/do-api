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

import com.dongkap.common.exceptions.BaseControllerException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.security.service.CheckAccountImplService;

@RestController
public class OauthController extends BaseControllerException {

	@Autowired	
	private CheckAccountImplService checkAccountService;

	@RequestMapping(value = "/oauth/check-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiBaseResponse> checkUser(Authentication authentication,
			@RequestBody(required = true) Map<String, String> p_dto,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		ApiBaseResponse response = this.checkAccountService.checkUserByUsenamerOrEmail(p_dto.get("user"), locale);
		if(response.getRespStatusCode() == ErrorCode.ERR_SYS0302.name()) {
			return new ResponseEntity<ApiBaseResponse>(response, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<ApiBaseResponse>(response, HttpStatus.OK);	
		}
	}
}
