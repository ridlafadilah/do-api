package com.dongkap.feign.dto.security;

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
public class SignUpWithProfileDto extends SignUpDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1086118543560112225L;
	private String phoneNumber;
	private String address;
	private String country;
	private String province;
	private String city;
	private String district;
	private String subDistrict;
	private String zipcode;
	private String image;
	private String description;
	private String idNumber;
	private String genderCode;
	private String gender;
	private String placeOfBirth;
	private String dateOfBirth;

}