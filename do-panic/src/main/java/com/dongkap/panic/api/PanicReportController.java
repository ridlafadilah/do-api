package com.dongkap.panic.api;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dongkap.common.aspect.ResponseSuccess;
import com.dongkap.common.exceptions.BaseControllerException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.utils.SuccessCode;
import com.dongkap.feign.dto.common.CommonResponseDto;
import com.dongkap.feign.dto.common.FilterDto;
import com.dongkap.feign.dto.panic.BasePanicReportDto;
import com.dongkap.feign.dto.panic.PanicReportDto;
import com.dongkap.panic.service.PanicReportImplService;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/panic")
public class PanicReportController extends BaseControllerException {

	@Autowired
	private PanicReportImplService panicReportService;

	@Autowired
	private ObjectMapper objectMapper;

    @ResponseSuccess(SuccessCode.OK_DEFAULT)
	@RequestMapping(value = "/trx/auth/panic/v.1", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ApiBaseResponse> doPostPanicReport(Authentication authentication,
			@RequestPart @Valid MultipartFile evidence,
			@RequestParam @Valid String data,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
    	objectMapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, false);
    	data = data.replaceAll("\\\\", ""); 
    	BasePanicReportDto dto = objectMapper.readValue(data, BasePanicReportDto.class);
		return new ResponseEntity<ApiBaseResponse>(this.panicReportService.doPostPanicReport(dto, evidence, authentication, locale), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vw/auth/panic/v.1/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<PanicReportDto> getPanicReport(Authentication authentication,
			@PathVariable(required = true) String code,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		return new ResponseEntity<PanicReportDto>(this.panicReportService.getPanicReport(code, authentication, locale), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vw/auth/panic/v.1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<List<PanicReportDto>> getAllPanicReport(Authentication authentication,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		return new ResponseEntity<List<PanicReportDto>>(this.panicReportService.getAllPanicReport(authentication, locale), HttpStatus.OK);
	}

    @ResponseSuccess(SuccessCode.OK_UPDATED)
	@RequestMapping(value = "/trx/auth/process-panic/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ApiBaseResponse> doProcessPanicReport(Authentication authentication,
			@RequestBody(required = true) Map<String, Object> dto,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		return new ResponseEntity<ApiBaseResponse>(this.panicReportService.doProcessPanicReport(dto, authentication, locale), HttpStatus.OK);
	}

	@RequestMapping(value = "/vw/post/datatable/panic-reports/v.1", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponseDto<PanicReportDto>> getDatatablePanicReport(Authentication authentication,
			@RequestBody(required = true) FilterDto filter,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		return new ResponseEntity<CommonResponseDto<PanicReportDto>>(this.panicReportService.getDatatablePanicReport(filter, locale), HttpStatus.OK);
	}

}
