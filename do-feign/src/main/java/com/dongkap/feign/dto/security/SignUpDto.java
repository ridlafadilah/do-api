package com.dongkap.feign.dto.security;

import java.io.Serializable;

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
public class SignUpDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1742415621743889509L;
	private String name;
	private String email;
	private String password;
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
	private String referenceName;
	private String referenceAddress;
	private String referencePhoneNumber;
	private String relationshipCode;
	private String relationship;

}