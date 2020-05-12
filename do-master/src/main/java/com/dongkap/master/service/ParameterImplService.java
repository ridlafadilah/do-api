package com.dongkap.master.service;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dongkap.common.service.CommonService;
import com.dongkap.feign.dto.common.CommonResponseDto;
import com.dongkap.feign.dto.common.FilterDto;
import com.dongkap.feign.dto.master.ParameterDto;
import com.dongkap.feign.dto.select.SelectDto;
import com.dongkap.feign.dto.select.SelectResponseDto;
import com.dongkap.master.dao.ParameterRepo;
import com.dongkap.master.dao.specification.ParameterSpecification;
import com.dongkap.master.entity.ParameterEntity;

@Service("parameterService")
public class ParameterImplService extends CommonService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ParameterRepo parameterRepo;

	@Value("${xa.locale}")
	private String locale;

	public CommonResponseDto<ParameterDto> getDatatableParameter(FilterDto filter) throws Exception {
		Page<ParameterEntity> param = parameterRepo.findAll(ParameterSpecification.getDatatable(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		CommonResponseDto<ParameterDto> response = new CommonResponseDto<ParameterDto>();
		response.setTotalFiltered(new Long(param.getContent().size()));
		response.setTotalRecord(parameterRepo.count(ParameterSpecification.getDatatable(filter.getKeyword())));
		param.getContent().forEach(value -> {
			ParameterDto temp = new ParameterDto();
			temp.setParameterCode(value.getParameterCode());
			temp.setParameterGroupCode(value.getParameterGroup().getParameterGroupCode());
			temp.setParameterGroupName(value.getParameterGroup().getParameterGroupName());
			temp.setActive(value.isActive());
			temp.setVersion(value.getVersion());
			temp.setCreatedDate(value.getCreatedDate());
			temp.setCreatedBy(value.getCreatedBy());
			temp.setModifiedDate(value.getModifiedDate());
			temp.setModifiedBy(value.getModifiedBy());
			response.getData().add(temp);
		});
		return response;
	}

	public SelectResponseDto getSelect(FilterDto filter, String locale) throws Exception {
	    	Locale i18n = Locale.forLanguageTag(locale);
	    	if(i18n.getDisplayLanguage().isEmpty()) {
	    		locale = this.locale;
	    	}
	    	filter.getKeyword().put("localeCode", locale);
		Page<ParameterEntity> parameter = parameterRepo.findAll(ParameterSpecification.getSelect(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		SelectResponseDto response = new SelectResponseDto();
		response.setTotalFiltered(new Long(parameter.getContent().size()));
		response.setTotalRecord(parameterRepo.count(ParameterSpecification.getSelect(filter.getKeyword())));
		parameter.getContent().forEach(value -> {
			value.getParameterI18n().forEach(data -> {
				response.getData().add(new SelectDto(data.getParameterValue(), value.getParameterCode(), !value.isActive(), null));
			});
		});
		return response;
	}

}
