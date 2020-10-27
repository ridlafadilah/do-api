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
import com.dongkap.master.dao.DistrictRepo;
import com.dongkap.master.dao.specification.DistrictSpecification;
import com.dongkap.master.entity.DistrictEntity;

@Service("districtService")
public class DistrictImplService extends CommonService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DistrictRepo districtRepo;

	public SelectResponseDto getSelectDistrict(FilterDto filter) throws Exception {
		Page<DistrictEntity> district = districtRepo.findAll(DistrictSpecification.getSelect(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		final SelectResponseDto response = new SelectResponseDto();
		response.setTotalFiltered(Long.valueOf(district.getContent().size()));
		response.setTotalRecord(districtRepo.count(DistrictSpecification.getSelect(filter.getKeyword())));
		district.getContent().forEach(value -> {
			response.getData().add(new SelectDto(value.getDistrictName(), value.getDistrictCode(), !value.isActive(), null));
		});
		return response;
	}

}
