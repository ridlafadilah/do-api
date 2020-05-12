package com.dongkap.feign.dto.panic;

import com.dongkap.feign.dto.common.BaseAuditDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class BasePanicReportDto extends BaseAuditDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1580501181371364781L;
	private Double latestLatitude;
	private Double latestLongitude;
	private String latestFormattedAddress;
	private String latestProvince;
	private String latestCity;
	private String latestDistrict;
	private String latestFileChecksum;
	private String latestDeviceID;
	private String latestDeviceName;

}