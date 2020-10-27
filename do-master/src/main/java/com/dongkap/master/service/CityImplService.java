package com.dongkap.master.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dongkap.common.service.CommonService;
import com.dongkap.feign.dto.common.CommonResponseDto;
import com.dongkap.feign.dto.common.FilterDto;
import com.dongkap.feign.dto.master.CityDto;
import com.dongkap.feign.dto.select.SelectDto;
import com.dongkap.feign.dto.select.SelectResponseDto;
import com.dongkap.master.dao.CityRepo;
import com.dongkap.master.dao.specification.CitySpecification;
import com.dongkap.master.entity.CityEntity;

@Service("cityService")
public class CityImplService extends CommonService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CityRepo cityRepo;

	public SelectResponseDto getSelectCity(FilterDto filter) throws Exception {
		Page<CityEntity> city = cityRepo.findAll(CitySpecification.getSelect(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		final SelectResponseDto response = new SelectResponseDto();
		response.setTotalFiltered(Long.valueOf(city.getContent().size()));
		response.setTotalRecord(cityRepo.count(CitySpecification.getSelect(filter.getKeyword())));
		city.getContent().forEach(value -> {
			response.getData().add(new SelectDto(value.getCityName(), value.getCityCode(), !value.isActive(), null));
		});
		return response;
	}

	public CommonResponseDto<CityDto> getDatatableCity(FilterDto filter) throws Exception {
		Page<CityEntity> city = cityRepo.findAll(CitySpecification.getDatatable(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		final CommonResponseDto<CityDto> response = new CommonResponseDto<CityDto>();
		response.setTotalFiltered(Long.valueOf(city.getContent().size()));
		response.setTotalRecord(cityRepo.count(CitySpecification.getDatatable(filter.getKeyword())));
		city.getContent().forEach(value -> {
			CityDto temp = new CityDto();
			temp.setCityCode(value.getCityCode());
			temp.setCityName(value.getCityName());
			temp.setProvinceName(value.getProvince().getProvinceName());
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

}
