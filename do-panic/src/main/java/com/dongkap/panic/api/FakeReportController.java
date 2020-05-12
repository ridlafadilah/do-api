package com.dongkap.panic.api;

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
import com.dongkap.feign.dto.common.CommonResponseDto;
import com.dongkap.feign.dto.common.FilterDto;
import com.dongkap.feign.dto.panic.FakeReportDto;
import com.dongkap.panic.service.FakeReportImplService;

@RestController
@RequestMapping("/api/panic")
public class FakeReportController extends BaseControllerException {

	@Autowired
	private FakeReportImplService fakeReportService;

    @ResponseSuccess(SuccessCode.OK_SCR011)
	@RequestMapping(value = "/trx/auth/fake/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ApiBaseResponse> doFakeReport(Authentication authentication,
			@RequestBody(required = true) Map<String, Object> dto,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		return new ResponseEntity<ApiBaseResponse>(this.fakeReportService.doFakeReport(dto, authentication, locale), HttpStatus.OK);
	}

	@RequestMapping(value = "/vw/post/datatable/fake-reports/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponseDto<FakeReportDto>> getDatatableFakeReport(Authentication authentication,
			@RequestBody(required = true) FilterDto filter,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		return new ResponseEntity<CommonResponseDto<FakeReportDto>>(this.fakeReportService.getDatatableFakeReport(filter, locale), HttpStatus.OK);
	}
	
}
