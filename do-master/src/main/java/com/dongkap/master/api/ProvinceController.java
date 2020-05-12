package com.dongkap.master.api;

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
import com.dongkap.feign.dto.common.FilterDto;
import com.dongkap.feign.dto.select.SelectResponseDto;
import com.dongkap.master.service.ProvinceImplService;

@RestController
@RequestMapping("/api/master")
public class ProvinceController extends BaseControllerException {

	@Autowired
	private ProvinceImplService provinceService;

	@RequestMapping(value = "/vw/post/select/province/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SelectResponseDto> getSelectProvince(Authentication authentication,
			@RequestBody(required = true) FilterDto filter) throws Exception {
		return new ResponseEntity<SelectResponseDto>(provinceService.getSelectProvince(filter), HttpStatus.OK);
	}
	
}
