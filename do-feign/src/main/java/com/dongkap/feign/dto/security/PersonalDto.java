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
	private String idNumber;
	private Integer age;
	private String genderCode;
	private String gender;
	private String placeOfBirth;
	private String dateOfBirth;

}