package com.dongkap.master.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dongkap.common.service.CommonService;
import com.dongkap.feign.dto.common.FilterDto;
import com.dongkap.feign.dto.select.SelectDto;
import com.dongkap.feign.dto.select.SelectResponseDto;
import com.dongkap.master.dao.CountryRepo;
import com.dongkap.master.dao.specification.CountrySpecification;
import com.dongkap.master.entity.CountryEntity;

@Service("countryService")
public class CountryImplService extends CommonService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CountryRepo countryRepo;

	public SelectResponseDto getSelectCountry(FilterDto filter) throws Exception {
		Page<CountryEntity> country = countryRepo.findAll(CountrySpecification.getSelect(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		final SelectResponseDto response = new SelectResponseDto();
		response.setTotalFiltered(Long.valueOf(country.getContent().size()));
		response.setTotalRecord(countryRepo.count(CountrySpecification.getSelect(filter.getKeyword())));
		country.getContent().forEach(value -> {
			response.getData().add(new SelectDto(value.getCountryName(), value.getCountryCode(), !value.isActive(), null));
		});
		return response;
	}

}
