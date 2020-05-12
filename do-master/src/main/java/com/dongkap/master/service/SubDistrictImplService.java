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
import com.dongkap.master.dao.SubDistrictRepo;
import com.dongkap.master.dao.specification.SubDistrictSpecification;
import com.dongkap.master.entity.SubDistrictEntity;

@Service("subDistrictService")
public class SubDistrictImplService extends CommonService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SubDistrictRepo subDistrictRepo;

	public SelectResponseDto getSelectSubDistrict(FilterDto filter) throws Exception {
		Page<SubDistrictEntity> subDistrict = subDistrictRepo.findAll(SubDistrictSpecification.getSelect(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		SelectResponseDto response = new SelectResponseDto();
		response.setTotalFiltered(new Long(subDistrict.getContent().size()));
		response.setTotalRecord(subDistrictRepo.count(SubDistrictSpecification.getSelect(filter.getKeyword())));
		subDistrict.getContent().forEach(value -> {
			response.getData().add(new SelectDto(value.getSubDistrictName(), value.getSubDistrictCode(), !value.isActive(), null));
		});
		return response;
	}

}
