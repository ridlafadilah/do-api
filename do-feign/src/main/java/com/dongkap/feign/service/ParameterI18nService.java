package com.dongkap.feign.service;

import java.util.List;
import java.util.Map;

import com.dongkap.feign.dto.master.ParameterI18nDto;
import com.dongkap.feign.dto.master.ParameterRequestDto;

public interface ParameterI18nService {
	
	public List<ParameterI18nDto> getParameterCode(Map<String, Object> filter) throws Exception;
	
	public void postParameterI18n(ParameterRequestDto request, String username) throws Exception;
	
	public ParameterI18nDto getParameter(Map<String, Object> param, String locale) throws Exception;

}
