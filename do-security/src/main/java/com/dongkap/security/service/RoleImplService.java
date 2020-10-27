package com.dongkap.security.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.service.CommonService;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.feign.dto.common.CommonResponseDto;
import com.dongkap.feign.dto.common.FilterDto;
import com.dongkap.feign.dto.security.RoleDto;
import com.dongkap.feign.dto.select.SelectDto;
import com.dongkap.feign.dto.select.SelectResponseDto;
import com.dongkap.security.dao.RoleRepo;
import com.dongkap.security.dao.specification.RoleSpecification;
import com.dongkap.security.entity.RoleEntity;

@Service("roleService")
public class RoleImplService extends CommonService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleRepo roleRepo;

	public SelectResponseDto getSelectRole(FilterDto filter) throws Exception {
		Page<RoleEntity> role = roleRepo.findAll(RoleSpecification.getSelect(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		final SelectResponseDto response = new SelectResponseDto();
		response.setTotalFiltered(Long.valueOf(role.getContent().size()));
		response.setTotalRecord(roleRepo.count(RoleSpecification.getSelect(filter.getKeyword())));
		role.getContent().forEach(value -> {
			response.getData().add(new SelectDto(value.getDescription(), value.getAuthority(), !value.isActive(), null));
		});
		return response;
	}

	public SelectResponseDto getSelectAllLocale() throws Exception {
		List<RoleEntity> role = roleRepo.findAll();
		final SelectResponseDto response = new SelectResponseDto();
		response.setTotalFiltered(Long.valueOf(role.size()));
		response.setTotalRecord(Long.valueOf(role.size()));
		role.forEach(value -> {
			response.getData().add(new SelectDto(value.getDescription(), value.getAuthority(), !value.isActive(), null));
		});
		return response;
	}

	public List<RoleDto> getAllLocale() throws Exception {
		List<RoleEntity> role = roleRepo.findAll();
		final List<RoleDto> response = new ArrayList<RoleDto>();
		role.forEach(value -> {
			RoleDto temp = new RoleDto();
			temp.setAuthority(value.getAuthority());
			temp.setDescription(value.getDescription());
			temp.setActive(value.isActive());
			temp.setVersion(value.getVersion());
			temp.setCreatedBy(value.getCreatedBy());
			temp.setCreatedDate(value.getCreatedDate());
			temp.setModifiedBy(value.getModifiedBy());
			temp.setModifiedDate(value.getModifiedDate());
			response.add(temp);
		});
		return response;
	}

	public CommonResponseDto<RoleDto> getDatatableLocale(FilterDto filter) throws Exception {
		Page<RoleEntity> role = roleRepo.findAll(RoleSpecification.getDatatable(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		final CommonResponseDto<RoleDto> response = new CommonResponseDto<RoleDto>();
		response.setTotalFiltered(Long.valueOf(role.getContent().size()));
		response.setTotalRecord(roleRepo.count(RoleSpecification.getDatatable(filter.getKeyword())));
		role.getContent().forEach(value -> {
			RoleDto temp = new RoleDto();
			temp.setAuthority(value.getAuthority());
			temp.setDescription(value.getDescription());
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
	
	@Transactional
	public void postRole(RoleDto request, String username) throws Exception {
		if (request.getAuthority() != null && request.getDescription() != null) {
			RoleEntity role = roleRepo.findByAuthority(request.getAuthority());
			if (role == null) {
				role = new RoleEntity();
				role.setAuthority(request.getAuthority());
				role.setCreatedBy(username);
				role.setCreatedDate(new Date());
			} else {
				role.setModifiedBy(username);
				role.setModifiedDate(new Date());				
			}
			role.setDescription(request.getDescription());
			role = roleRepo.saveAndFlush(role);
		} else {
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
		}
	}

}
