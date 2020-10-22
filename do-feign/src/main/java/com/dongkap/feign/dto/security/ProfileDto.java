package com.dongkap.feign.dto.security;

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
public class ProfileDto extends BaseAuditDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1742415621743889509L;
	private String username;
	private String name;
	private String email;
	private String phoneNumber;
	private String address;
	private String country;
	private String countryCode;
	private String province;
	private String provinceCode;
	private String city;
	private String cityCode;
	private String district;
	private String districtCode;
	private String subDistrict;
	private String subDistrictCode;
	private String zipcode;
	private String image;
	private String description;

}