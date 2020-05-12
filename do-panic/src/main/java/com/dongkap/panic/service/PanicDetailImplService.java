package com.dongkap.panic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dongkap.common.service.CommonService;
import com.dongkap.feign.dto.common.CommonResponseDto;
import com.dongkap.feign.dto.common.FilterDto;
import com.dongkap.feign.dto.panic.DeviceDto;
import com.dongkap.feign.dto.panic.LocationDto;
import com.dongkap.feign.dto.panic.PanicDetailDto;
import com.dongkap.feign.service.FileGenericService;
import com.dongkap.panic.dao.PanicDetailRepo;
import com.dongkap.panic.dao.specification.PanicDetailSpecification;
import com.dongkap.panic.entity.PanicDetailEntity;

@Service("panicDetailService")
public class PanicDetailImplService extends CommonService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PanicDetailRepo panicDetailRepo;

	@Autowired
	@Qualifier("fileEvidenceService")
	private FileGenericService fileEvidenceService;

	public CommonResponseDto<PanicDetailDto> getDatatablePanicDetail(FilterDto filter) throws Exception {
		Page<PanicDetailEntity> param = panicDetailRepo.findAll(PanicDetailSpecification.getDatatable(filter.getKeyword()), page(filter.getOrder(), filter.getOffset(), filter.getLimit()));
		CommonResponseDto<PanicDetailDto> response = new CommonResponseDto<PanicDetailDto>();
		response.setTotalFiltered(new Long(param.getContent().size()));
		response.setTotalRecord(panicDetailRepo.count(PanicDetailSpecification.getDatatable(filter.getKeyword())));
		param.getContent().forEach(value -> {
			try {
				response.getData().add(toObjectDetail(value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return response;
	}
	
	private PanicDetailDto toObjectDetail(PanicDetailEntity panicDetail) throws Exception {
		PanicDetailDto objPanicDetail = new PanicDetailDto();
		objPanicDetail.setFileChecksum(panicDetail.getFileChecksum());
		objPanicDetail.setFileMetadata(fileEvidenceService.getFileInfo(panicDetail.getFileChecksum()));
		if(panicDetail.getLocation() != null) {
			LocationDto responseLocation = new LocationDto();
			responseLocation.setLatitude(panicDetail.getLocation().getCoordinate().getX());
			responseLocation.setLongitude(panicDetail.getLocation().getCoordinate().getY());
			responseLocation.setFormattedAddress(panicDetail.getLocation().getFormattedAddress());
			responseLocation.setProvince(panicDetail.getLocation().getProvince());
			responseLocation.setCity(panicDetail.getLocation().getCity());
			responseLocation.setDistrict(panicDetail.getLocation().getDistrict());
			objPanicDetail.setLocation(responseLocation);
		}
		if(panicDetail.getDevice() != null) {
			DeviceDto responseDevice = new DeviceDto();
			responseDevice.setDeviceID(panicDetail.getDevice().getDeviceID());
			responseDevice.setDeviceName(panicDetail.getDevice().getDeviceName());
			objPanicDetail.setDevice(responseDevice);
		}
		objPanicDetail.setActive(panicDetail.isActive());
		objPanicDetail.setVersion(panicDetail.getVersion());
		objPanicDetail.setCreatedDate(panicDetail.getCreatedDate());
		objPanicDetail.setCreatedBy(panicDetail.getCreatedBy());
		objPanicDetail.setModifiedDate(panicDetail.getModifiedDate());
		objPanicDetail.setModifiedBy(panicDetail.getModifiedBy());
		return objPanicDetail;
	}

}
