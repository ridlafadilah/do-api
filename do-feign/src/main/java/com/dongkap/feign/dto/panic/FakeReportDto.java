package com.dongkap.feign.dto.panic;

import java.util.ArrayList;
import java.util.List;

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
public class FakeReportDto extends BaseAuditDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7550850975117219030L;
	private String fakeCode;
	private String username;
	private String name;
	private String gender;
	private String phoneNumber;
	private Integer age;
	private String idNumber;
	private String month;
	private Integer year;
	private Double latestLatitude;
	private Double latestLongitude;
	private String latestFormattedAddress;
	private String latestProvince;
	private String latestCity;
	private String latestDistrict;
	private String latestFileChecksum;
	private String latestDeviceID;
	private String latestDeviceName;
	private String urgencyCategory;
	private String status;
	private List<FakeDetailDto> fakeDetails = new ArrayList<FakeDetailDto>();

}