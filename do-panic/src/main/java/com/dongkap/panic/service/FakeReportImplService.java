package com.dongkap.panic.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.service.CommonService;
import com.dongkap.common.utils.DateUtil;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.feign.dto.common.CommonResponseDto;
import com.dongkap.feign.dto.common.FilterDto;
import com.dongkap.feign.dto.panic.FakeReportDto;
import com.dongkap.feign.service.CheckAccountService;
import com.dongkap.feign.service.ParameterI18nService;
import com.dongkap.panic.dao.FakeDetailRepo;
import com.dongkap.panic.dao.FakeReportRepo;
import com.dongkap.panic.dao.PanicReportRepo;
import com.dongkap.panic.dao.specification.FakeReportSpecification;
import com.dongkap.panic.entity.FakeDetailEntity;
import com.dongkap.panic.entity.FakeReportEntity;
import com.dongkap.panic.entity.PanicReportEntity;

@Service("fakeReportService")
public class FakeReportImplService extends CommonService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PanicReportRepo panicReportRepo;

	@Autowired
	private FakeReportRepo fakeReportRepo;

	@Autowired
	private FakeDetailRepo fakeDetailRepo;
	
	@Autowired
	private CheckAccountService checkAccountService;
	
	@Autowired
	private ParameterI18nService parameterI18nService;

	@Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = SystemErrorException.class)
	public ApiBaseResponse doFakeReport(Map<String, Object> dto, Authentication authentication, String p_locale) throws Exception {
		this.checkAccountService.doCheckPassword(dto, authentication, p_locale);
		if (dto != null) {
			PanicReportEntity panic = panicReportRepo.findById(dto.get("panicCode").toString()).orElse(null);
			if(panic != null) {
				panic.setActive(false);
				panic = panicReportRepo.saveAndFlush(panic);
				final FakeReportEntity fake = new FakeReportEntity();
				fake.setFakeCode(panic.getPanicCode());
				fake.setUsername(panic.getUsername());
				fake.setName(panic.getName());
				fake.setGender(panic.getGender());
				fake.setAge(panic.getAge());
				fake.setPhoneNumber(panic.getPhoneNumber());
				fake.setIdNumber(panic.getIdNumber());
				fake.setMonth(panic.getMonth());
				fake.setYear(panic.getYear());
				fake.setLatestCoordinate(panic.getLatestCoordinate());
				fake.setLatestFormattedAddress(panic.getLatestFormattedAddress());
				fake.setLatestProvince(panic.getLatestProvince());
				fake.setLatestCity(panic.getLatestCity());
				fake.setLatestDistrict(panic.getLatestDistrict());		
				fake.setLatestFileChecksum(panic.getLatestFileChecksum());
				fake.setLatestDeviceID(panic.getLatestDeviceID());
				fake.setLatestDeviceName(panic.getLatestDeviceName());
				fakeReportRepo.saveAndFlush(fake);
				Set<FakeDetailEntity> fakeDetails = new HashSet<FakeDetailEntity>();
				panic.getPanicDetails().forEach(panicDetail -> {
					FakeDetailEntity fakeDetail = new FakeDetailEntity();
					fakeDetail.setFileChecksum(panicDetail.getFileChecksum());
					fakeDetail.setLocation(panicDetail.getLocation());
					fakeDetail.setDevice(panicDetail.getDevice());
					fakeDetail.setFakeReport(fake);
					fakeDetails.add(fakeDetail);
				});
				fakeDetailRepo.saveAll(fakeDetails);
				return null;
			} else
				throw new SystemErrorException(ErrorCode.ERR_SYS0404);
		} else
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
	}

	public CommonResponseDto<FakeReportDto> getDatatableFakeReport(FilterDto filter, String locale) throws Exception {
		Page<FakeReportEntity> param = fakeReportRepo.findAll(FakeReportSpecification.getDatatable(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		CommonResponseDto<FakeReportDto> response = new CommonResponseDto<FakeReportDto>();
		response.setTotalFiltered(new Long(param.getContent().size()));
		response.setTotalRecord(fakeReportRepo.count(FakeReportSpecification.getDatatable(filter.getKeyword())));
		param.getContent().forEach(value -> {
			try {
				response.getData().add(toObject(value, locale));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return response;
	}
	
	private FakeReportDto toObject(FakeReportEntity panic, String p_locale) throws Exception {
		FakeReportDto response = new FakeReportDto();
		response.setFakeCode(panic.getFakeCode());
		response.setUsername(panic.getUsername());
		response.setName(panic.getName());
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("parameterCode", panic.getGender());
		response.setGender(parameterI18nService.getParameter(temp, p_locale).getParameterValue());
		response.setAge(panic.getAge());
		response.setPhoneNumber(panic.getPhoneNumber());
		response.setIdNumber(panic.getIdNumber());
		response.setMonth(DateUtil.getMonthName(p_locale, panic.getMonth()));
		response.setYear(panic.getYear());
		response.setLatestLatitude(panic.getLatestCoordinate().getX());
		response.setLatestLongitude(panic.getLatestCoordinate().getY());
		response.setLatestFormattedAddress(panic.getLatestFormattedAddress());
		response.setLatestProvince(panic.getLatestProvince());
		response.setLatestCity(panic.getLatestCity());
		response.setLatestDistrict(panic.getLatestDistrict());
		response.setLatestFileChecksum(panic.getLatestFileChecksum());
		response.setLatestDeviceID(panic.getLatestDeviceID());
		response.setLatestDeviceName(panic.getLatestDeviceName());
		response.setActive(panic.isActive());
		response.setVersion(panic.getVersion());
		response.setCreatedDate(panic.getCreatedDate());
		response.setCreatedBy(panic.getCreatedBy());
		response.setModifiedDate(panic.getModifiedDate());
		response.setModifiedBy(panic.getModifiedBy());
		return response;
	}

}
