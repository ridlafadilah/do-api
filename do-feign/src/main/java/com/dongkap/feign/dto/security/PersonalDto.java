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
public class PersonalDto extends ProfileDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1742415621743889509L;
	private Integer age;
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