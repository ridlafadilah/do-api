package com.dongkap.panic.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dongkap.common.exceptions.BaseControllerException;
import com.dongkap.feign.dto.common.CommonResponseDto;
import com.dongkap.feign.dto.common.FilterDto;
import com.dongkap.feign.dto.panic.PanicDetailDto;
import com.dongkap.panic.service.PanicDetailImplService;

@RestController
@RequestMapping("/api/panic")
public class PanicDetailController extends BaseControllerException {

	@Autowired
	private PanicDetailImplService panicDetailService;

	@RequestMapping(value = "/vw/post/datatable/panic-detail/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponseDto<PanicDetailDto>> getDatatablePanicDetail(Authentication authentication,
			@RequestBody(required = true) FilterDto filter) throws Exception {
		return new ResponseEntity<CommonResponseDto<PanicDetailDto>>(this.panicDetailService.getDatatablePanicDetail(filter), HttpStatus.OK);
	}
	
}
