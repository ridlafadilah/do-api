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
import com.dongkap.master.service.SubDistrictImplService;

@RestController
@RequestMapping("/api/master")
public class SubDistrictController extends BaseControllerException {

	@Autowired
	private SubDistrictImplService subDistrictService;

	@RequestMapping(value = "/vw/post/select/subdistrict/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SelectResponseDto> getSelectSubDistrict(Authentication authentication,
			@RequestBody(required = true) FilterDto filter) throws Exception {
		return new ResponseEntity<SelectResponseDto>(subDistrictService.getSelectSubDistrict(filter), HttpStatus.OK);
	}
	
}
