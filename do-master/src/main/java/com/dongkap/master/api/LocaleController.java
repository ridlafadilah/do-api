package com.dongkap.master.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dongkap.common.aspect.ResponseSuccess;
import com.dongkap.common.exceptions.BaseControllerException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.utils.SuccessCode;
import com.dongkap.feign.dto.common.CommonResponseDto;
import com.dongkap.feign.dto.common.FilterDto;
import com.dongkap.feign.dto.master.LocaleDto;
import com.dongkap.feign.dto.select.SelectResponseDto;
import com.dongkap.master.service.LocaleImplService;

@RestController
@RequestMapping("/api/master")
public class LocaleController extends BaseControllerException {

	@Autowired
	private LocaleImplService localeService;

	@RequestMapping(value = "/vw/post/select/locale/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SelectResponseDto> getSelectLocale(Authentication authentication,
			@RequestBody(required = true) FilterDto filter) throws Exception {
		return new ResponseEntity<SelectResponseDto>(localeService.getSelectLocale(filter), HttpStatus.OK);
	}

	@RequestMapping(value = "/vw/get/select/all/locale/v.1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SelectResponseDto> getSelectAllLocale(Authentication authentication) throws Exception {
		return new ResponseEntity<SelectResponseDto>(localeService.getSelectAllLocale(), HttpStatus.OK);
	}

	@RequestMapping(value = "/vw/get/all/locale/v.1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LocaleDto>> getAllLocale(Authentication authentication) throws Exception {
		return new ResponseEntity<List<LocaleDto>>(localeService.getAllLocale(), HttpStatus.OK);
	}

	@RequestMapping(value = "/vw/post/datatable/locale/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponseDto<LocaleDto>> getDatatableLocale(Authentication authentication,
			@RequestBody(required = true) FilterDto filter) throws Exception {
		return new ResponseEntity<CommonResponseDto<LocaleDto>>(this.localeService.getDatatableLocale(filter), HttpStatus.OK);
	}
	
	@ResponseSuccess(SuccessCode.OK_SCR010)
	@RequestMapping(value = "/trx/post/locale/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiBaseResponse> postLocale(Authentication authentication,
			@RequestBody(required = true) LocaleDto data) throws Exception {
		String username = authentication.getName();
		this.localeService.postLocale(data, username);
		return new ResponseEntity<ApiBaseResponse>(new ApiBaseResponse(), HttpStatus.OK);
	}
	
}
